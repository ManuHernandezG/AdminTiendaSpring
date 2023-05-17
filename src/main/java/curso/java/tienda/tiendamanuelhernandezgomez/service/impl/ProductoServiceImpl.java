package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Imagen;
import curso.java.tienda.tiendamanuelhernandezgomez.domain.Producto;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.ProductoRepository;

@Service
public class ProductoServiceImpl{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ImagenService imagenService;

    public List<Producto> getAll(){
        return productoRepository.findAll();
    }

    public Producto getById(Integer id){
        return productoRepository.getReferenceById(id);
    }

    public Boolean updateProd(Producto prod){
        Producto old= productoRepository.getReferenceById(prod.getId());
        old.setNombre(prod.getNombre());
        old.setDescripcion(prod.getDescripcion());
        old.setPrecio(prod.getPrecio());
        old.setStock(prod.getStock());
        imagenService.save(new Imagen(0, old, "https://storage.googleapis.com/spring-app-tch/"));
        if(productoRepository.save(old)!=null){
            return true;
        }else{
            return false;
        }
    }

    public Boolean deleteProd(Integer id){
        Producto old= productoRepository.getReferenceById(id);
        old.setBaja(true);
        if(productoRepository.save(old)!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean save(Producto prod,String originalFilename){
        Producto producto=productoRepository.save(prod);
        if(producto!=null){
            imagenService.save(new Imagen(0, producto, "https://storage.googleapis.com/spring-app-tch/" + originalFilename));
            return true;
        }else{
            return false;
        }
    }

    public boolean updateProd(Producto prod, String originalFilename) {
        Producto old= productoRepository.getReferenceById(prod.getId());
        old.setNombre(prod.getNombre());
        old.setDescripcion(prod.getDescripcion());
        old.setPrecio(prod.getPrecio());
        old.setStock(prod.getStock());
        imagenService.save(new Imagen(0, old, "https://storage.googleapis.com/spring-app-tch/" + originalFilename));
        if(productoRepository.save(old)!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean save(Producto prod) {
        Producto producto=productoRepository.save(prod);
        if(producto!=null){
            return true;
        }else{
            return false;
        }
    }
    
}
