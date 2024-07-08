package ar.edu.unju.fi.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import lombok.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String legajo;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    
    public Docente(String legajo, String nombre, String apellido, String email, String telefono) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
}