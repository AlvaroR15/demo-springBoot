package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTO {
	private Integer id;
	
	@NotBlank(message = "El legajo no puede estar vacío")
    @Size(min = 4, max = 10, message = "El legajo debe tener entre 4 y 10 caracteres")
    private String legajo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 6, max = 15, message = "El teléfono debe tener entre 6 y 15 caracteres")
    private String telefono;
}
