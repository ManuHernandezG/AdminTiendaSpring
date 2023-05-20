package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Configuracion;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.ConfiguracionService;

@Controller
public class ConfigController {
    @Autowired
    private ConfiguracionService configuracionService;

    @GetMapping("/config")
    public String goConfigShop(Model model){
        model.addAttribute("configuraciones", configuracionService.findAll());
        return "config";
    }

    @PostMapping("/config")
    public String updateConfigShop(@ModelAttribute Configuracion conf){
        configuracionService.updateTabla(conf);

        return "redirect:/config";
    }

    @PostMapping("/config/new")
    public String newConfig(@ModelAttribute Configuracion conf){
        configuracionService.save(conf);

        return "config";
    }
}
