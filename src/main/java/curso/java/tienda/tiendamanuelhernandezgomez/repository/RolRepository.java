package curso.java.tienda.tiendamanuelhernandezgomez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    
    public Rol findByDescripcion(String desc);
}
