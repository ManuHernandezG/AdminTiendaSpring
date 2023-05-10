package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.RolService;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.UsuarioService;

@Controller
public class ClientesController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;

    @GetMapping("/clientes")
    public String goClientes(Model model){
        model.addAttribute("clientes", usuarioService.findAllByRol(rolService.findByDescripcion("cliente")));
        return "clientes";
    }
}
