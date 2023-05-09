package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Usuario;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private  UsuarioRepository userRepository;
    @Autowired
    private  UserDetailsServiceImpl userDetailsService;
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private  AuthenticationManager authenticationManager;

    public void save(Usuario user) {
        user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
        userRepository.save(user);
    }

    public void login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);

        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
            logger.debug(String.format("User %s logged in successfully!", username));
        }else{
            logger.error(String.format("Error with %s authentication!", username));
        }
    }
}
