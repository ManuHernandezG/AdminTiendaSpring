package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import curso.java.tienda.tiendamanuelhernandezgomez.domain.Configuracion;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.ConfiguracionService;

@Controller
public class IndexController {
    
    @Autowired
    private ConfiguracionService configuracionService;

    /**
     * @param model
     * @return
     */
    @GetMapping({"/","/index","/dashboard"})
    public String init(Model model){
       List<Configuracion> confs = configuracionService.findAll();
        List<Integer> visitas= new ArrayList<>();
        List<Integer> ventas= new ArrayList<>();
        for (Configuracion c: confs) {
            if(c.getClave().equals("visitas") ){
                visitas.add(Integer.parseInt(c.getValor()));
            }else if(c.getClave().equals("factura")){
                ventas.add(Integer.parseInt(c.getValor()));
            }
        };
        model.addAttribute("ventas", ventas);
        model.addAttribute("visitas", visitas);

        return "dashboard";
    }
}
