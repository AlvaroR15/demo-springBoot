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
		Carrera carrera = carreraRepository.findById(3).get();
		Materia materia = new Materia();
		materia.setNombre("Diseño Experimental");
		//materia.setCantidadHoras(4);
		materia.setCurso("26");
		materia.setCodigo("LB1");
		materia.setModalidad("Virtual");
		materia.setCarrera(carrera);
		materiaRepository.save(materia);
	}
	
	
	
}
