package curso.java.tienda.tiendamanuelhernandezgomez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import curso.java.tienda.tiendamanuelhernandezgomez.domain.Pedido;
import curso.java.tienda.tiendamanuelhernandezgomez.repository.PedidoRepository;

public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

}
