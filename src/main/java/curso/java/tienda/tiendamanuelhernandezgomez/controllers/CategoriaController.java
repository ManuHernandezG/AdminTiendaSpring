package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Categoria;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.CategoriaService;

@Controller
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/categorias")
    public String showCategorias(Model model){
        
        model.addAttribute("categorias",categoriaService.findAll());

        return "categorias";
    }

    @GetMapping("/categorias/new")
    public String newCategoria(Model model){
        return "formCategorias";
    }

    @PostMapping("/categorias/new")
    public String formCategorias(@ModelAttribute Categoria cat){
        if (categoriaService.save(cat)){
            return "redirect:/categorias";
        }else{
            return "/categorias/new";
        }
    }

    @GetMapping("/categorias/update/{id}")
    public String goUpdateCat(@PathVariable int id, Model model){
        model.addAttribute("categoria", categoriaService.findById(id));
   
        return "formCategorias";
    }

    @PostMapping("/categorias/update")
    public String updateCat(@ModelAttribute Categoria cat){
        if (categoriaService.update(cat)){
            return "redirect:/categorias";
        }else{
            return "/categorias/update";
        }
    }

}
