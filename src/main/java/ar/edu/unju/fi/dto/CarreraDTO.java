package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO {
	
		
		private Integer codigo;

	    @NotBlank(message = "El nombre de la carrera no puede estar vacío")
	    @Size(min = 3, max = 50, message = "El nombre de la carrera debe tener entre {min} y {max} caracteres")
	    private String nombre;

	    @NotNull(message = "La cantidad de años no puede ser nula")
	    private Byte cantidadDeAnios;

	    private boolean estado;
}
