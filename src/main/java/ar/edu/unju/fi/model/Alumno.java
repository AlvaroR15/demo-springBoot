package ar.edu.unju.fi.model;

import java.time.LocalDate;
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
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String domicilio;
    private String lu;

    @ManyToMany(mappedBy = "alumnos")
    private Set<Materia> materias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;
}