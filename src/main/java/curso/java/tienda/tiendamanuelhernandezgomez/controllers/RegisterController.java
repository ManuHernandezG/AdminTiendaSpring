package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Usuario;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.UsuarioService;

@Controller
public class RegisterController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/registro")
    public String registro(Model model){
        return "register";
    }

    @PostMapping("registro")
    public String doRegistro(@ModelAttribute Usuario user){
        usuarioService.save(user);
        return "redirect:/dashboard";
    }
}
