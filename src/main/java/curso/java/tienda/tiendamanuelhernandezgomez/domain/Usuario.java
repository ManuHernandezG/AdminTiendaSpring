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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table
public class Usuario {

    @Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    private Rol rol;

    @NotBlank(message = "Debes rellenar este campo es obligatorio")
    @NotEmpty(message = "Debes rellenar el campo email es obligatorio")
    @Email(message = "Debe tener el formato email")
    @Column(name = "email",unique = true)
    private String email;
    
    @Size(min = 8, message = "Debe tener al menos 8 caracteres")
    @Column(name = "clave")
    private String clave;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "baja")
    private boolean baja;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    List<Direccion> direcciones = new ArrayList<Direccion>();

}
