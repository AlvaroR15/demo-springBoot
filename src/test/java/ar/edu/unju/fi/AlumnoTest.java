package ar.edu.unju.fi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.repository.MateriaRepository;

@SpringBootTest
class AlumnoTest {
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Test
	public void createAlumnoTest() {
		Carrera carrera = carreraRepository.findById(5).get();
		List<Materia> materias = new ArrayList<>();
		materias.add(materiaRepository.findById(1).get());
		materias.add(materiaRepository.findById(2).get());

		Alumno alumno = new Alumno("46441976","Lionel","Messi","leo10@gmail.com","388-6812321",LocalDate.of(1986, 06, 24),"B° Alto La Viña", "LU-991", carrera,materias);
		Alumno alumnoGuardado = alumnoRepository.save(alumno);
		assertTrue(alumnoGuardado instanceof Alumno);
	}
	
	
	
}
