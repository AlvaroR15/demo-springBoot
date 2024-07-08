package ar.edu.unju.fi.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import lombok.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materias")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String nombre;
    private String curso;
    private float cantidadHoras;
    private String modalidad;
    private String estado;

    @OneToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    @ManyToMany
    @JoinTable(
        name = "materia_alumno",
        joinColumns = @JoinColumn(name = "materia_id"),
        inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
    private Set<Alumno> alumnos = new HashSet<>();
    
    public Materia(String codigo, String nombre, String curso, float cantidadHoras, String modalidad, Docente docente, Carrera carrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.curso = curso;
        this.cantidadHoras = cantidadHoras;
        this.modalidad = modalidad;
        this.docente = docente;
        this.carrera = carrera;
    }
}