package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Usuario;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.UsuarioService;

@Controller
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/perfil")
    public String profile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=(User) auth.getPrincipal();

        model.addAttribute("usuario", usuarioService.findByEmail(user.getUsername()));

        return "profile";
    }

    @PostMapping("/perfil")
    public String doUpdate(@Valid @ModelAttribute Usuario user,@RequestParam String pass2, BindingResult errores){
        if(!errores.hasErrors() && user.getClave().equals(pass2)){
            usuarioService.updateUsuario(user);
            return "dashboard";
        }else{
            return "/perfil";
        }
    }
}
