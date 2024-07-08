package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table (name = "materias")
public class Materia {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@NotBlank(message = "El código de la materia no puede estar vacío")
    @Size(max = 50, message = "El código de la materia no puede tener más de 50 caracteres")
    @Column(name = "codigo")
    private String codigo;

    @NotBlank(message = "El nombre de la materia no puede estar vacío")
    @Size(max = 100, message = "El nombre de la materia no puede tener más de 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El curso de la materia no puede estar vacío")
    @Size(max = 50, message = "El curso de la materia no puede tener más de 50 caracteres")
    @Column(name = "curso")
    private String curso;

    @NotNull(message = "La cantidad de horas no puede estar vacía")
    @Column(name = "cantidadHoras")
    private Float cantidadHoras;

    @NotBlank(message = "La modalidad de la materia no puede estar vacía")
    @Size(max = 50, message = "La modalidad de la materia no puede tener más de 50 caracteres")
    @Column(name = "modalidad")
    private String modalidad;
	
	
	@ManyToMany(mappedBy = "materias")
    private List<Alumno> alumnos;

    @OneToOne
    @JoinColumn(name = "docente_id", referencedColumnName = "id")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "carrera_id", referencedColumnName = "codigo")
    private Carrera carrera;

	public Materia(String codigo, String nombre, String curso, float cantidadHoras, String modalidad,
			List<Alumno> alumnos, Docente docente, Carrera carrera) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.curso = curso;
		this.cantidadHoras = cantidadHoras;
		this.modalidad = modalidad;
		this.alumnos = alumnos;
		this.docente = docente;
		this.carrera = carrera;
	}
    
    
    
}