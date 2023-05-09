package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Producto;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.ProductoRepository;

@Service
public class ProductoServiceImpl{

    @Autowired
    private ProductoRepository productoRepository;

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

    public boolean save(Producto prod){
        if(productoRepository.save(prod)!=null){
            return true;
        }else{
            return false;
        }
    }
    
}
