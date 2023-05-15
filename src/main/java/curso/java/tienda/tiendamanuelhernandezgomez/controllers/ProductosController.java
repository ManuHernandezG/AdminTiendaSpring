package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Producto;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.CategoriaService;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.ProductoServiceImpl;

@Controller
public class ProductosController {
    
    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @Autowired 
    private CategoriaService categoriaService;

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
        model.addAttribute("categorias", categoriaService.findAll());
        return "newProducto";
    }

    @PostMapping("/productos/new")
    public String newProd(@ModelAttribute Producto prod,@RequestParam("file") MultipartFile file) throws IOException{
        String ruta = "src/main/resources/static/img/" + file.getOriginalFilename();
        prod.setImagen(file.getOriginalFilename());
        Files.copy(file.getInputStream(), Paths.get(ruta));
        if (productoServiceImpl.save(prod)) {
            return "redirect:/productos";
        }else{
            return "/productos/new";
        }
    }
}
