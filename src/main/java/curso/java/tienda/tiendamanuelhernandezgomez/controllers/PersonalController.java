package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Usuario;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.RolService;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.UsuarioService;

@Controller
public class PersonalController {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/personal")
    public String goPersonal(Model model){
        model.addAttribute("personal", usuarioService.findPersonal());

        return "personal";
    }

    @GetMapping("/personal/update/{id}")
    public String formPersonal(@PathVariable int id,Model model){
        model.addAttribute("empleado", usuarioService.findById(id));
        model.addAttribute("roles",rolService.findAll());
        return "updatePersonal";
    }

    @PostMapping("/personal/update")
    public String updatePersonal(@ModelAttribute Usuario empleado, Model model){
        if (usuarioService.updateUsuario(empleado)){
            return "redirect:/personal";
        }else{
            return "/personal/update/"+ empleado.getId();
        }
    }

    @GetMapping("/personal/delete/{id}")
    public String deleteEmpleado(@PathVariable int id){
        usuarioService.deleteUser(id);

        return "redirect:/personal";
    }

    @GetMapping("/personal/activate/{id}")
    public String activateEmpleado(@PathVariable int id){
        usuarioService.activateUser(id);

        return "redirect:/personal";
    }

    @GetMapping("/personal/new")
    public String formEmpleado(Model model){
        return "registroEmpleado";
    }

}
