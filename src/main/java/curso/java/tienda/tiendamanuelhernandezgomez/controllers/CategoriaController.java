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
    
    /**
     * @param model Estructura que proveera de las categorias que hay en BBDD
     * @return Vista general de las categorías
     */
    @GetMapping("/categorias")
    public String showCategorias(Model model){
        
        model.addAttribute("categorias",categoriaService.findAll());

        return "categorias";
    }

    /**
     * @return Vista del formulario de las categorías 
     */
    @GetMapping("/categorias/new")
    public String newCategoria(){
        return "formCategorias";
    }

    /**
     * @param cat Entidad Categoria recogida por parte del formulario
     * @return En caso positivo: vista general de las categorías
     * @return En caso negativo: vista del formulario de las categorías
     * 
     */
    @PostMapping("/categorias/new")
    public String formCategorias(@ModelAttribute Categoria cat){
        if (categoriaService.save(cat)){
            return "redirect:/categorias";
        }else{
            return "/categorias/new";
        }
    }

    /**
     * @param id Id de la Categoria que se tiene que representar en e
     * @param model
     * @return
     */
    @GetMapping("/categorias/update/{id}")
    public String goUpdateCat(@PathVariable int id, Model model){
        model.addAttribute("categoria", categoriaService.findById(id));
   
        return "updateCategoria";
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
