package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Imagen;
import curso.java.tienda.tiendamanuelhernandezgomez.domain.Producto;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.ImagenRepository;

@Service
public class ImagenService {
    
    @Autowired
    private ImagenRepository imagenRepository;

    public void save(Imagen img){
        imagenRepository.save(img);
    }

    public List<Imagen> findByProducto(Producto prod){
        return imagenRepository.findByProducto(prod);
    }

    
}
