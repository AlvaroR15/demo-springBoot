package ar.edu.unju.fi.dto;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {
		private String dni;
		private String nombre;
		private String apellido;
		private String email;
		private String telefono;
		private LocalDate fechaNacimiento;
		private String domicilio;
}		
