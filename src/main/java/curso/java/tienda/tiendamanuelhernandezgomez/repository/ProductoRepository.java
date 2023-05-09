package curso.java.tienda.tiendamanuelhernandezgomez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}