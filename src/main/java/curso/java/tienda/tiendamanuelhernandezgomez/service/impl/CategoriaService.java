package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Categoria;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public boolean save(Categoria cat){
        if(categoriaRepository.save(cat)!=null){
            return true;
        }else{
            return false;
        }
    }

    public Categoria findById(int id){
        return categoriaRepository.getReferenceById(id);
    }

    public boolean update(Categoria cat){
        Categoria old=categoriaRepository.getReferenceById(cat.getId());
        old.setDescripcion(cat.getDescripcion());
        if(categoriaRepository.save(old)!=null){
            return true;
        }else{
            return false;
        }
    }

    public Categoria findByDescripcion(String desc){
        return categoriaRepository.findByDescripcion(desc);
    }
}
