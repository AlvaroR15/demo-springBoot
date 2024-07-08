package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@Entity
@Table (name = "alumnos")
public class Alumno {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El DNI no puede estar vacío")
    @Size(min = 7, max = 8, message = "El DNI debe tener entre 7 y 8 caracteres")
    @Column(name = "dni")
    private String dni;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name ="nombre")
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

    @NotNull(message = "La fecha de nacimiento no puede estar vacía")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El domicilio no puede estar vacío")
    @Column(name = "domicilio")
    private String domicilio;

    @NotBlank(message = "El LU no puede estar vacío")
    @Size(min = 4, max = 10, message = "El LU debe tener entre 4 y 10 caracteres")
    @Column(name = "lu")
    private String lu; 
	

    @ManyToOne
    @JoinColumn(name = "carrera_id", referencedColumnName = "codigo")
    private Carrera carrera;

    @ManyToMany
    @JoinTable(
        name = "alumno_materia",
        joinColumns = @JoinColumn(name = "alumno_id"),
        inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<Materia> materias;

	public Alumno(String dni, String nombre, String apellido, String email, String telefono, LocalDate fechaNacimiento,
			String domicilio, String lu, Carrera carrera, List<Materia> materias) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.lu = lu;
		this.carrera = carrera;
		this.materias = materias;
	}
	
    
    
	
}
	