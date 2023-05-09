package curso.java.tienda.tiendamanuelhernandezgomez.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table
public class Producto {
    
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
	private Categoria categoria;

    private String nombre;
	
	private String descripcion;
	
	private double precio;
	
	private double impuesto;
	
	private int stock;
	
	private String imagen;
	
	private boolean baja;
}
