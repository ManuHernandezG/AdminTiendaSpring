package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Configuracion;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.ConfiguracionRepository;

@Service
public class ConfiguracionService {
    
    @Autowired
    private ConfiguracionRepository configuracionRepository;

    public Configuracion get(String clave){
        return configuracionRepository.getByClave(clave);
    }

    public void updateFactura() {
        Configuracion conf=configuracionRepository.getReferenceById(1);
        int num=Integer.parseInt(conf.getValor());
        num++;
        conf.setValor(String.valueOf(num));
        configuracionRepository.save(conf);
    }

    public List<Configuracion> findAll(){
        return configuracionRepository.findAll();
    }

    public void updateTabla(Configuracion conf) {
        Configuracion old=configuracionRepository.getByClave(conf.getClave());
        old.setClave(conf.getClave());
        old.setValor(conf.getValor());
        configuracionRepository.save(old);

    }
}
