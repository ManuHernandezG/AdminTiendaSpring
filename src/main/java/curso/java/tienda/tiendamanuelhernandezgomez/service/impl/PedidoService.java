package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Pedido;
import curso.java.tienda.tiendamanuelhernandezgomez.domain.Pedido.StatusType;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.PedidoRepository;
@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

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

    public boolean env(int id) {
        Pedido old= pedidoRepository.getReferenceById(id);
        old.setEstado(StatusType.E);
        if (pedidoRepository.save(old)!=null){
            return true;
        }else{
            return false;
        }
    }

}
