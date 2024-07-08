package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor

@Entity
@Table (name = "carreras")
public class Carrera {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "codigo")
	private Integer codigo;
	
	@NotBlank(message = "El nombre de la carrera no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre de la carrera debe tener entre {min} y {max} caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotNull(message = "La cantidad de años no puede ser nula")
    @Column(name = "cantidadDeAnios")
    private Byte cantidadDeAnios;

    @NotBlank(message = "El estado no puede estar vacío")
    @Column(name = "estado")
    private String estado;
	
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alumno> alumnos;
	
	 @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Materia> materias;

	public Carrera(String nombre, Byte cantidadDeAnios, String estado, List<Alumno> alumnos, List<Materia> materias) {
		this.nombre = nombre;
		this.cantidadDeAnios = cantidadDeAnios;
		this.estado = estado;
		this.alumnos = alumnos;
		this.materias = materias;
	}
	 
	 
	
}