package ar.edu.unju.fi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.repository.MateriaRepository;

@SpringBootTest
class CarreraTest {
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Test
	public void createCarreraTest() {
		Carrera carrera = new Carrera();
		carrera.setNombre("Tecnico/a en Mecanización Agrícola");
		carrera.setCantidadDeAnios((byte)2);
		carrera.setEstado("Activa");
		carreraRepository.save(carrera);
	}
	
	
	
}
