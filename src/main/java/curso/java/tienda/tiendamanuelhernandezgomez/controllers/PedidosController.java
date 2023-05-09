package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.PedidoService;

@Controller
public class PedidosController {

    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping("/pedidos")
    public String goPedidos(Model model){
        model.addAttribute("lista", pedidoService.findAll());
        return "pedidos";
    }
}
