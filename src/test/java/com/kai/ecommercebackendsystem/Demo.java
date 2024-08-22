package com.kai.ecommercebackendsystem;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;

public class Demo {

    public static void main(String[] args) throws Exception {
        quickstart();
    }

    public static void quickstart() throws Exception {
        String projectId = "ecommercebackendsystem";
        String secretId = "service-account-key";
        quickstart(projectId, secretId);
    }

    public static void quickstart(String projectId, String secretId) throws Exception {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {

            String secretName = String.format("projects/%s/secrets/%s", projectId, secretId);

            AccessSecretVersionResponse response = client.accessSecretVersion(secretName + "/versions/latest");


            String data = response.getPayload().getData().toStringUtf8();
            System.out.printf("Plaintext: %s\n", data);
        }
    }
}