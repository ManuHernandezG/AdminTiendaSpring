package curso.java.tienda.tiendamanuelhernandezgomez.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
	private List<Imagen> imagenes=new ArrayList<Imagen>();

	private boolean baja;

   
}
