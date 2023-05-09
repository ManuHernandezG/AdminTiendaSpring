package curso.java.tienda.tiendamanuelhernandezgomez.domain;

import java.sql.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table
public class Pedido {

    @Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	
	private Date fecha;
	
	private String metodopago;
	
	private String numfactura;
	
	private double total;
}
