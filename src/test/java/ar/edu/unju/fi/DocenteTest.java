package ar.edu.unju.fi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.repository.MateriaRepository;

@SpringBootTest
class DocenteTest {
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Test
	public void crearDocenteTest() {
		Docente docente = new Docente("LEG1002","Rodolfo","Aguado","profAguado@fca.unju.edu.ar","388-6453245", materiaRepository.findById(3).get());
		Docente docenteGuardado = docenteRepository.save(docente);
		assertTrue(docenteGuardado instanceof Docente);
	}
	
	
	
}
