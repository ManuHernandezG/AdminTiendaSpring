package curso.java.tienda.tiendamanuelhernandezgomez.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl detailsServiceImpl;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
    //    http.csrf().disable()
    //     .authorizeRequests(a ->
    //         a
    //         .antMatchers("/", "/home","/index","/detalle/**","/contacto","/carrito/**","/registro").permitAll()
    //         .antMatchers("/perfil/**").hasRole("USER")
    //         .antMatchers("/admin/**").hasRole("ADMIN")
    //     )
    //     .formLogin(formLogin ->
    //         formLogin
    //         .loginPage("/login")
    //         .permitAll()
    //     )
    //     .logout(logout ->
    //         logout
    //         .invalidateHttpSession(true)
    //         .clearAuthentication(true)
    //         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    //         .permitAll()
    //     )
    //     .headers(headers->
    //         headers
    //         .frameOptions()
    //         .sameOrigin()
    //     )
    //     .authenticationManager(authManager(http));

        // http.csrf().disable().authorizeRequests()
        //     .antMatchers("/", "/home","/index","/detalle/**","/contacto","/carrito/**","/registro").permitAll()
        //     .antMatchers("/perfil/**").hasRole("USER")
        //     .antMatchers("/admin/**").hasRole("ADMIN")
        //     .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home")
        //     .and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
        //     .and().headers().frameOptions().sameOrigin();

        http.authorizeRequests()
            .antMatchers("/", "/dashboard","/index","/pedidos/**").hasAnyRole("EMPLOYER","ADMIN")
            .antMatchers("/productos/**","/clientes/**","/empleados/**").hasRole("ADMIN")
            .antMatchers("/productos", "/productos/update","/productos/new","/clientes/update", "/clientes/new").hasRole("EMPLOYER")
            .and().formLogin()
            // .loginPage("/login").permitAll().defaultSuccessUrl("/dashboard")
            .and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
            .and().headers().frameOptions().sameOrigin();
        return http.build();
       
    }

    @Bean 
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/js/**", "/style/**","/img/**");
    }

    @Bean 
    public AuthenticationManager authManager(HttpSecurity http)throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());

        return authenticationManagerBuilder.build();
    }
    // /*~~(Migrate manually based on https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)~~>*/@Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.authenticationProvider(authenticationProvider());
            
    // }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(detailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}
