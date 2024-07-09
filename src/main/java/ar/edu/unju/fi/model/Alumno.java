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
	
    @Column(name = "dni")
    private String dni;

    @Column(name ="nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "domicilio")
    private String domicilio;

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
	