package ar.edu.unju.fi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
class MateriaTest {
	
	@Autowired
	private MateriaRepository materiaRepository;
	@Autowired
	private CarreraRepository carreraRepository;
	
	
	
	@Test
	public void crearMateriaTest() {
		Carrera carrera = carreraRepository.findById(2).get();
		Materia materia = new Materia();
		materia.setNombre("Zoologia Alimentaria");
		materia.setCantidadHoras((float)6);
		materia.setCurso("10");
		materia.setModalidad("Presencial");
		materia.setCarrera(carrera);
		materiaRepository.save(materia);
	}
	
	
	
}
