package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/clientes/update/{id}")
    public String goUpdateCliente(@PathVariable int id,Model model){
        model.addAttribute("cliente", usuarioService.findById(id));
        
        return "updateCliente";
    }

    @GetMapping("/clientes/delete/{id}")
    public String deleteCliente(@PathVariable int id){
        usuarioService.deleteUser(id);

        return "redirect:/clientes";
    }

    @GetMapping("/clientes/activate/{id}")
    public String activateCliente(@PathVariable int id){
        usuarioService.activateUser(id);

        return "redirect:/clientes";
    }
}
