package curso.java.tienda.tiendamanuelhernandezgomez.controllers;

import java.io.IOException;
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

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Imagen;
import curso.java.tienda.tiendamanuelhernandezgomez.domain.Producto;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.CategoriaService;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.GCSStorageService;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.ImagenService;
import curso.java.tienda.tiendamanuelhernandezgomez.service.impl.ProductoServiceImpl;

@Controller
public class ProductosController {
    
    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @Autowired 
    private CategoriaService categoriaService;

    @Autowired
    private ImagenService imagenService;

    @Autowired
    private GCSStorageService storageService;

    @GetMapping("/productos")
    public String productosInit(Model model){

        model.addAttribute("catalogo",productoServiceImpl.getAll());
        return "productos";
    }

    @GetMapping("/productos/update/{id}")
    public String updateProducto(@PathVariable int id,Model model){
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("producto", productoServiceImpl.getById(id));

        return "updateProd";
    }

    @GetMapping("/productos/delete/{id}")
    public String deleteProducto(@PathVariable int id,Model model){
        productoServiceImpl.deleteProd(id);

        return "redirect:/productos";
    }
    @GetMapping("/productos/activate/{id}")
    public String activateProducto(@PathVariable int id,Model model){
        productoServiceImpl.activateProd(id);

        return "redirect:/productos";
    }

    @PostMapping("/productos/update")
    public String postUpdate(@ModelAttribute Producto prod,@RequestParam(name = "file",required = false) MultipartFile file) throws IOException{
        if (file!=null && !file.isEmpty()){
            // String ruta = "src/main/resources/static/img/" + file.getOriginalFilename();
            storageService.saveImagen(file, "spring-app-tch", file.getOriginalFilename());
            // Files.copy(file.getInputStream(), Paths.get(ruta));
            if(productoServiceImpl.updateProd(prod,file.getOriginalFilename())){
                // imagenService.save(new Imagen(0, prod, "https://storage.googleapis.com/spring-app-tch/" + file.getOriginalFilename()));
                return "redirect:/productos";
            }else{
                return "/productos/update/"+ prod.getId();
            }
        }else{
            if(productoServiceImpl.updateProd(prod)){
                // imagenService.save(new Imagen(0, prod, "https://storage.googleapis.com/spring-app-tch/" + file.getOriginalFilename()));
                return "redirect:/productos";
            }else{
                return "/productos/update/"+ prod.getId();
            }
        }
    }

    @GetMapping("/productos/new")
    public String formProduct(Model model){
        model.addAttribute("categorias", categoriaService.findAll());
        return "newProducto";
    }

    @PostMapping("/productos/new")
    public String newProd(@ModelAttribute Producto prod,@RequestParam("file") MultipartFile file) throws IOException{
        if (file!=null){
            // String ruta = "src/main/resources/static/img/" + file.getOriginalFilename();
            storageService.saveImagen(file, "spring-app-tch", file.getOriginalFilename());
            // Files.copy(file.getInputStream(), Paths.get(ruta));
            if(productoServiceImpl.save(prod,file.getOriginalFilename())){
                // imagenService.save(new Imagen(0, prod, "https://storage.googleapis.com/spring-app-tch/" + file.getOriginalFilename()));
                return "redirect:/productos";
            }else{
                return "/productos/update/"+ prod.getId();
            }
        }else{
            if(productoServiceImpl.save(prod)){
                // imagenService.save(new Imagen(0, prod, "https://storage.googleapis.com/spring-app-tch/" + file.getOriginalFilename()));
                return "redirect:/productos";
            }else{
                return "/productos/update/"+ prod.getId();
            }
        }
    }
}
