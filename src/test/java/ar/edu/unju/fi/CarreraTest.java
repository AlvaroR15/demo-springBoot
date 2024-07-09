package ar.edu.unju.fi;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;

@SpringBootTest
class CarreraTest {
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Test
	public void createCarreraTest() {
		Carrera carrera = new Carrera();
		carrera.setNombre("Tecnicatura en Mecanización Agrícola");
		carrera.setCantidadDeAnios((byte)2);
		carrera.setEstado(true);
		carreraRepository.save(carrera);
	}
	
	
	
}
