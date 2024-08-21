package com.kai.ecommercebackendsystem.utils;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GcpUtil {

    private static final String PROJECT_ID = "ecommercebackendsystem";
    private static final String BUCKET_NAME = "ecommerce_backend_system";
    private static final String JSON_PATH = "C:\\Users\\j2000\\Desktop\\FileCloud\\ecommercebackendsystem-9b564b456a27.json";

    public static String uploadFile(String objectName, InputStream file) throws IOException {

        // 初始化 Storage 服務，使用服務帳戶憑證進行身份驗證

        Storage storage = StorageOptions.newBuilder()
                .setProjectId(PROJECT_ID)
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(JSON_PATH)))
                .build()
                .getService();

        BlobId blobId = BlobId.of(BUCKET_NAME, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // 設定條件以避免競爭狀況和資料損毀
        Storage.BlobWriteOption precondition;
    if (storage.get(BUCKET_NAME, objectName) == null) {
            precondition = Storage.BlobWriteOption.doesNotExist();
        } else {
            precondition = Storage.BlobWriteOption.generationMatch(storage.get(BUCKET_NAME, objectName).getGeneration());
        }

        storage.createFrom(blobInfo, file, precondition);

        return String.format("https://storage.cloud.google.com/%s/%s", BUCKET_NAME, objectName);
    }
}
