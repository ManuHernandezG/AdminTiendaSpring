package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class GCSStorageService {

    @Autowired
    private ImagenService imagenService;

    public void saveImagen(MultipartFile file, String bucketName,String fileName){
        try {

            File archivo= new File("src/main/resources/json/credenciales.json");

            InputStream input= new FileInputStream(archivo);
            GoogleCredentials credentials= GoogleCredentials.fromStream(input);
            StorageOptions options=StorageOptions.newBuilder().setCredentials(credentials).build();

            Storage storage=(Storage) options.getService();
        
            BlobId blobId=BlobId.of(bucketName, fileName);

            BlobInfo blobInfo=BlobInfo.newBuilder(blobId).build();

            Blob blob = storage.create(blobInfo, file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
