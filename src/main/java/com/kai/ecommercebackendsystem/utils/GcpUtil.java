package com.kai.ecommercebackendsystem.utils;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GcpUtil {

    private static final String PROJECT_ID = "ecommercebackendsystem";
    private static final String BUCKET_NAME = "ecommerce_backend_system";
    private static final String SECRET_ID = "service-account-key";

    public static String uploadFile(String objectName, InputStream file) throws IOException {

        /* 從 Secret Manager 中獲取服務帳戶憑證 */
        String credentials = getSecretPayload();

        /* 初始化 Storage 服務，使用服務帳戶憑證進行身份驗證 */
        Storage storage = StorageOptions.newBuilder()
                .setProjectId(PROJECT_ID)
                .setCredentials(ServiceAccountCredentials.fromStream(new ByteArrayInputStream(credentials.getBytes())))
                .build()
                .getService();

        BlobId blobId = BlobId.of(BUCKET_NAME, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        /* 設定條件以避免競爭狀況和資料損毀 */
        Storage.BlobWriteOption precondition;
        if (storage.get(BUCKET_NAME, objectName) == null) {
            precondition = Storage.BlobWriteOption.doesNotExist();
        } else {
            precondition = Storage.BlobWriteOption.generationMatch(storage.get(BUCKET_NAME, objectName).getGeneration());
        }

        storage.createFrom(blobInfo, file, precondition);

        return String.format("https://storage.cloud.google.com/%s/%s", BUCKET_NAME, objectName);
    }

    /* 從 Secret Manager 中獲取 Secret 的內容 */
    private static String getSecretPayload() throws IOException {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()){
            String secretName = String.format("projects/%s/secrets/%s", PROJECT_ID, SECRET_ID);
            AccessSecretVersionResponse response = client.accessSecretVersion(secretName + "/versions/latest");
            return response.getPayload().getData().toStringUtf8();
        }
    }
}
