package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

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
        Configuracion conf=configuracionRepository.getByClave("factura");
        int num=Integer.parseInt(conf.getValor());
        num++;
        conf.setValor(String.valueOf(num));
        configuracionRepository.save(conf);
    }
}
