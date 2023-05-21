package curso.java.tienda.tiendamanuelhernandezgomez.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl detailsServiceImpl;

    /**
     * Bean que define el encriptador que vamos a usar a lo largo de la app
     * @return Encriptador 
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Bean principal de protección de rutas de SpringSecurity 
     * @param http Seguridad general de la app
     * @return Seguridad construida a traves de los filtros
     * @throws Exception
     */
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            // Configuración de las reglas de autorización en la aplicación web
            http.authorizeRequests()
                .antMatchers("/config").permitAll() // Se permite acceso a "/config" a todos los usuarios
                .antMatchers("/", "/dashboard", "/index", "/pedidos/**", "/categorias/**").authenticated() // Requiere autenticación para estas URLs
                //.hasAnyRole("EMPLOYER","ADMIN") // Comentario: línea comentada, no tiene efecto en la configuración
                .antMatchers("/productos/**", "/clientes/**", "/empleados/*", "/config").hasRole("ADMIN") // Requiere el rol "ADMIN" para acceder a estas URLs
                .antMatchers("/productos", "/productos/update", "/productos/new", "/clientes/update", "/clientes/new").hasRole("EMPLOYER"); // Requiere el rol "EMPLOYER" para acceder a estas URLs
        
            // Configuración del formulario de inicio de sesión
            http.formLogin();
        
            // Configuración del cierre de sesión
            http.logout()
                .invalidateHttpSession(true) // Invalida la sesión actual
                .clearAuthentication(true) // Elimina la autenticación
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Se especifica el patrón de solicitud de cierre de sesión
                .permitAll(); // Se permite acceso a todos los usuarios para cerrar sesión
        
            // Configuración de las cabeceras y opciones de seguridad
            http.headers().frameOptions().sameOrigin(); // Permite el acceso al marco (frame) desde el mismo origen
        
            // Configuración de CORS (Cross-Origin Resource Sharing)
            http.cors();
        
            // Devuelve el objeto SecurityFilterChain construido a partir de la configuración realizada
            return http.build();
        }
        
       

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration= new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList(HttpMethod.POST.name()));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/config/actualizar", configuration);
        return source;
    }

    /**
     * Bean que define las rutas que deben ser ignoradas 
     * @return rutas ignoradas
     */
    @Bean 
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/js/**", "/css/**","/img/**","/i18n/**");
    }

    /**
     * @param http Seguridad de la app
     * @return Servicio que controlará los roles, accesos y autenticacion de nuestra app
     * @throws Exception
     */
    @Bean 
    public AuthenticationManager authManager(HttpSecurity http)throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());

        return authenticationManagerBuilder.build();
    }

    /**
     * Bean que provee al authManager de un servicio de autenticacion contra la BBDD
     * @return DaoAuthenticationProvider que proveerá al servicio principal
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(detailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}
