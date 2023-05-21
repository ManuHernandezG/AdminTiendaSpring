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

    public void saveImagen(MultipartFile file, String bucketName, String fileName) {
        try {
            // Carga el archivo de credenciales necesarias para autenticarse con Google Cloud Storage
            File archivo = new File("src/main/resources/json/credenciales.json");
    
            // Crea un flujo de entrada para leer el archivo de credenciales
            InputStream input = new FileInputStream(archivo);
    
            // Obtiene las credenciales de Google desde el flujo de entrada
            GoogleCredentials credentials = GoogleCredentials.fromStream(input);
    
            // Configura las opciones de almacenamiento con las credenciales
            StorageOptions options = StorageOptions.newBuilder().setCredentials(credentials).build();
    
            // Crea una instancia del servicio de almacenamiento
            Storage storage = (Storage) options.getService();
    
            // Crea una identificación única para el archivo en el bucket especificado
            BlobId blobId = BlobId.of(bucketName, fileName);
    
            // Crea la información del blob con la identificación única
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    
            // Crea el blob en Google Cloud Storage y guarda los bytes del archivo en él
            Blob blob = storage.create(blobInfo, file.getBytes());
        } catch (IOException e) {
            // Maneja cualquier excepción de E/S que ocurra durante la operación
            e.printStackTrace();
        }
    }
    
}
