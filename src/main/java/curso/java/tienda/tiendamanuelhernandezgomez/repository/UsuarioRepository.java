package curso.java.tienda.tiendamanuelhernandezgomez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Rol;
import curso.java.tienda.tiendamanuelhernandezgomez.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    public Usuario findByEmail(String Email);
    public List<Usuario> findByRol(Rol rol);
}
