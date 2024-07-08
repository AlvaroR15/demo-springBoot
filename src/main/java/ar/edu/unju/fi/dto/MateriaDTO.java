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
public class MateriaDTO {
	private Integer id;

    @NotBlank(message = "El código de la materia no puede estar vacío")
    @Size(max = 50, message = "El código de la materia no puede tener más de 50 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre de la materia no puede estar vacío")
    @Size(max = 100, message = "El nombre de la materia no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "El curso de la materia no puede estar vacío")
    @Size(max = 50, message = "El curso de la materia no puede tener más de 50 caracteres")
    private String curso;

    @NotNull(message = "La cantidad de horas no puede estar vacía")
    private Float cantidadHoras;

    @NotBlank(message = "La modalidad de la materia no puede estar vacía")
    @Size(max = 50, message = "La modalidad de la materia no puede tener más de 50 caracteres")
    private String modalidad;

    private DocenteDTO docente; 
    private CarreraDTO carrera;

}