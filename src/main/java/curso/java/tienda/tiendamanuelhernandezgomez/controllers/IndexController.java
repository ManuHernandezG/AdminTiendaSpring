package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    

    @GetMapping({"/","/index","/dashboard"})
    public String init(Model model){
        
        return "dashboard";
    }
}
