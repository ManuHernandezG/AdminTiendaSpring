package curso.java.tienda.tiendamanuelhernandezgomez.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table
public class Configuracion {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String clave;
	
	private String valor;
}
