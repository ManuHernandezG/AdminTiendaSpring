package curso.java.tienda.tiendamanuelhernandezgomez.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Enumerated(EnumType.STRING)
	private StatusType estado;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
	List<Detalle> detalles = new ArrayList<Detalle>();
	
	public enum StatusType {

		PE,PC,E,C
		
	}
}
