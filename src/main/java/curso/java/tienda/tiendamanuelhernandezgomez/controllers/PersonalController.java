package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.UsuarioService;

@Controller
public class PersonalController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/personal")
    public String goPersonal(Model model){
        

        return "personal";
    }

}
