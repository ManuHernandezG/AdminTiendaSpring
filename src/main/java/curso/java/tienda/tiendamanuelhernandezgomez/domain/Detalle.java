package curso.java.tienda.tiendamanuelhernandezgomez.domain;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table
public class Detalle {
    
    @Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Pedido pedido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Producto producto;
	
	private int unidades;
	
	private double preciounidad;
	
	private double impuesto;
	
	private double total;
}
