package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Rol;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.RolRepository;

@Service
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;

    public Rol findByDescripcion(String desc){
        return rolRepository.findByDescripcion(desc);
    }

    public List<Rol> findAll(){
        return rolRepository.findAll();
    }

}
