package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Configuracion;
import curso.java.tienda.tiendamanuelhernandezgomez.domain.Pedido;
import curso.java.tienda.tiendamanuelhernandezgomez.domain.Pedido.StatusType;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.PedidoRepository;
@Service
public class PedidoService {

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ConfiguracionService configuracionService;

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public boolean cancel(int id) {
        Pedido old= pedidoRepository.getReferenceById(id);
        old.setEstado(StatusType.C);
        if (pedidoRepository.save(old)!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean enviar(int id) {
        Pedido old= pedidoRepository.getReferenceById(id);
        old.setEstado(StatusType.E);
        old.setNumfactura(generarNumFactura());
        configuracionService.updateFactura();
        if (pedidoRepository.save(old)!=null){
            // emailService.sendEmail("manuhg13@gmail.com", "Tu pedido se ha enviado", "El pedido " + old.getNumfactura() + "");
            return true;
        }else{
            return false;
        }
    }

    public String generarNumFactura(){
        Date fecha = new Date(); // supongamos que tienes un objeto Date con la fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fechaString = formatter.format(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        Configuracion conf =configuracionService.get("factura");
        String numFactura="THC-" + fechaString +"-" +  conf.getValor();
        return numFactura;
    }
}
