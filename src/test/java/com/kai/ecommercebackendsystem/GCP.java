package com.kai.ecommercebackendsystem;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GCP {

    public static void main(String[] args) throws IOException {
        // The ID of your GCP project
        String projectId = "ecommercebackendsystem";

        // The ID of your GCS bucket
        String bucketName = "ecommerce_backend_system";

        // The ID of your GCS object
        String objectName = "003.png";

        // The path to your file to upload
        String filePath = "C:\\Users\\j2000\\Desktop\\FileCloud\\38f22470-b14b-4e74-b22e-47229aeb79e4.jpg";

        String jsonPath = "src/main/resources/static/ecommercebackendsystem-9b564b456a27.json";

        // 初始化 Storage 服務，使用服務帳戶憑證進行身份驗證
        Storage storage = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(jsonPath)))
                .build()
                .getService();

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // 設定條件以避免競爭狀況和資料損毀
        Storage.BlobWriteOption precondition;
        if (storage.get(bucketName, objectName) == null) {
            precondition = Storage.BlobWriteOption.doesNotExist();
        } else {
            precondition = Storage.BlobWriteOption.generationMatch(storage.get(bucketName, objectName).getGeneration());
        }

        // 使用 try-with-resources 自動關閉 InputStream
        try (InputStream file = new FileInputStream(filePath)) {
            storage.createFrom(blobInfo, file, precondition);
            System.out.printf("https://storage.cloud.google.com/%s/%s%n", bucketName, objectName);
        } catch (IOException e) {
            System.err.println("Failed to upload file: " + e.getMessage());
            throw e; // 根據需要重新拋出例外
        }
    }
}
