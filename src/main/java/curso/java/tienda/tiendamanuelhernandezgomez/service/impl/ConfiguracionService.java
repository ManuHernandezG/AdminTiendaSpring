package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.repository.ConfiguracionRepository;

@Service
public class ConfiguracionService {
    
    @Autowired
    private ConfiguracionRepository configuracionRepository;

    public String get(String clave){
        return configuracionRepository.getByClave(clave);
    }
}
