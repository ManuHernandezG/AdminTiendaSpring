package curso.java.tienda.tiendamanuelhernandezgomez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Configuracion;

@Repository
public interface ConfiguracionRepository extends JpaRepository<Configuracion,Integer>{
    
    Configuracion getByClave(String clave);
}
