package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table (name = "carreras")
public class Carrera {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "codigo")
	private Integer codigo;
	
	@Column (name = "nombre")
	private String nombre;
	
	@Column (name = "cantidadDeAnios")
	private Byte cantidadDeAnios;
	
	@Column (name = "estado")
	private String estado;
	
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alumno> alumnos;
	
	 @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Materia> materias;
	
}