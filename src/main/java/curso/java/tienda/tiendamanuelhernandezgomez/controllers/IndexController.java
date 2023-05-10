package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    

    @GetMapping({"/","/index","/dashboard"})
    public String init(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        System.out.println("Hola");
        return "dashboard";
    }
}
