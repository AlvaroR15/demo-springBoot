package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@Entity
@Table (name = "docentes")
public class Docente {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El legajo no puede estar vacío")
    @Size(min = 4, max = 10, message = "El legajo debe tener entre 4 y 10 caracteres")
    @Column(name = "legajo")
    private String legajo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    @Column(name = "apellido")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 6, max = 15, message = "El teléfono debe tener entre 6 y 15 caracteres")
    @Column(name = "telefono")
    private String telefono;
	
	
	@OneToOne(mappedBy = "docente")
    private Materia materia;


	public Docente(String legajo, String nombre, String apellido, String email, String telefono, Materia materia) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.materia = materia;
	}
	
	
	
}
