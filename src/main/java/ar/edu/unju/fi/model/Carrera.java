package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Carrera {
	private Integer codigo;
	private String nombre;
	private Byte cantidadDeAnios;
	private String estado;

	
}
