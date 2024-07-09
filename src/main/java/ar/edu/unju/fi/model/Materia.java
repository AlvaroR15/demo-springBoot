package ar.edu.unju.fi.model;

import java.util.List;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "materias")
public class Materia {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "codigo")
	private Integer codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "curso")
    private String curso;

    @Column(name = "cantidadHoras")
    private Float cantidadHoras;

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

	
    
    
    
}