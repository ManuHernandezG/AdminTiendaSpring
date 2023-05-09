package curso.java.tienda.tiendamanuelhernandezgomez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion,Integer>{
    
}
