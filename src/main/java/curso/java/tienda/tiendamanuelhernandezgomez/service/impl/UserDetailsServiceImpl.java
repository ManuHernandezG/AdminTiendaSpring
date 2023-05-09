package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Usuario;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario!=null) {
            Set<GrantedAuthority> authorities= new HashSet<>();
            if (usuario.getRol().getDescripcion().equals("admin")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }else if (usuario.getRol().getDescripcion().equals("empleado")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYER"));
            }
            LOGGER.debug(String.format("Usuario con el nombre: %s y contraseña %s creado", usuario.getEmail(),usuario.getClave()));
            return new org.springframework.security.core.userdetails.User(usuario.getEmail(),usuario.getClave(),authorities);
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado " + email);
        }
    }
}
