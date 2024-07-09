package ar.edu.unju.fi.dto;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

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
@AllArgsConstructor
public class AlumnoDTO {
    private Integer id;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Size(min = 7, max = 8, message = "El DNI debe tener entre 7 y 8 caracteres")
    private String dni;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 6, max = 15, message = "El teléfono debe tener entre 6 y 15 caracteres")
    private String telefono;

    @NotNull(message = "La fecha de nacimiento no puede estar vacía")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El domicilio no puede estar vacío")
    private String domicilio;

    @NotBlank(message = "El LU no puede estar vacío")
    @Size(min = 4, max = 10, message = "El LU debe tener entre 4 y 10 caracteres")
    private String lu;
}

