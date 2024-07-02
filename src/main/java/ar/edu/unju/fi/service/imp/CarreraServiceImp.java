package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

public class CarreraServiceImp implements CarreraService {

	@Autowired
	private CarreraRepository carreraRepository;
	
	@Autowired
	private Carrera carrera;
	
	@Override
	public List<Carrera> getCarreras() {
		return carreraRepository.findAll();
	}

	@Override
	public Carrera findById(Integer id) {
		return carreraRepository.findById(id).get();
	}

	@Override
	public void saveCarrera(Carrera carrera) {
		carreraRepository.save(carrera);
	}

	@Override
	public void editCarrera(Carrera carrera, Integer id) {
		Carrera carreraEncontrada = carreraRepository.findById(id).get();
		if(carreraEncontrada != null) {
			carreraRepository.save(carreraEncontrada);
		}
		
	}

	@Override
	public void deleteCarrera(Integer id) {
		carreraRepository.deleteById(id);
	}

}
