package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Producto;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.ProductoServiceImpl;

@Controller
public class ProductosController {
    
    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @GetMapping("/productos")
    public String productosInit(Model model){

        model.addAttribute("catalogo",productoServiceImpl.getAll());
        return "productos";
    }

    @GetMapping("/productos/update/{id}")
    public String updateProducto(@PathVariable int id,Model model){
        model.addAttribute("producto", productoServiceImpl.getById(id));

        return "updateProd";
    }

    @GetMapping("/productos/delete/{id}")
    public String deleteProducto(@PathVariable int id,Model model){
        productoServiceImpl.deleteProd(id);

        return "redirect:/productos";
    }

    @PostMapping("/productos/update")
    public String postUpdate(@ModelAttribute Producto prod){
        if(productoServiceImpl.updateProd(prod)){
            return "redirect:/productos";
        }else{
            return "/productos/update/"+ prod.getId();
        }
    }

    @GetMapping("/productos/new")
    public String formProduct(Model model){
        return "newProducto";
    }

    @PostMapping("/productos/new")
    public String newProd(@ModelAttribute Producto prod){
        if (productoServiceImpl.save(prod)) {
            return "redirect:/productos";
        }else{
            return "/productos/new";
        }
    }
}
