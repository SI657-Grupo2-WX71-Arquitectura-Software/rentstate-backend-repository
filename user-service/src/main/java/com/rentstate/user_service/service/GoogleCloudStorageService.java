package com.rentstate.user_service.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class GoogleCloudStorageService {

    private final String bucketName = "rentstate-img";
    private final Storage storage;

    public GoogleCloudStorageService() throws IOException {
        String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        if (credentialsPath == null || credentialsPath.isEmpty()) {
            throw new IOException("La variable de entorno GOOGLE_APPLICATION_CREDENTIALS no está configurada.");
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath));
        storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }

    public String uploadProfilePicture(MultipartFile file) throws IOException {
        String fileName = "profile-pictures/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getInputStream());
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }
}
