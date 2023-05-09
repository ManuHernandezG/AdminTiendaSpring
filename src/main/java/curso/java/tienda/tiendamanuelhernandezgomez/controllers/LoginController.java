package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
    
    @GetMapping("/login")
    public String login(Model model){

        
        return "login";
    }
}
