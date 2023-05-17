package curso.java.tienda.tiendamanuelhernandezgomez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Imagen;
import curso.java.tienda.tiendamanuelhernandezgomez.domain.Producto;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer>{
    
    List<Imagen> findByProducto(Producto prod);
}
