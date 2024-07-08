package ar.edu.unju.fi.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.services.ICarreraService;

@Service
public class CarreraServiceImp implements ICarreraService {
	
	@Autowired
	private CarreraRepository carreraRepository;

	@Autowired
	private CarreraMapper carreraMapper;
	
	
	@Override
	public List<CarreraDTO> getCarreras() {
		List<CarreraDTO> carrerasDTO = carreraMapper.toCarreraDTOs(carreraRepository.findAll());
		return carrerasDTO;
	}

	@Override
	public CarreraDTO getCarreraById(Integer id) {
		Optional<Carrera> carrera = carreraRepository.findById(id);
		return carrera.map(carreraMapper::toCarreraDTO).orElse(null);
	}

	@Override
	public boolean saveCarrera(CarreraDTO carreraDTO) {
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		carrera.setEstado("Activa");
		carreraRepository.save(carrera);
		return true;
	}

	@Override
	public void editCarrera(CarreraDTO carreraDTO) throws Exception {
	    Optional<Carrera> existingCarreraOpt = carreraRepository.findById(carreraDTO.getCodigo());
	    if (existingCarreraOpt.isPresent()) {
	        Carrera existingCarrera = existingCarreraOpt.get();
	        // Actualizar los campos necesarios
	        existingCarrera.setNombre(carreraDTO.getNombre());
	        existingCarrera.setCantidadDeAnios(carreraDTO.getCantidadDeAnios());
	        existingCarrera.setEstado(carreraDTO.getEstado());
	        // Guardar la carrera actualizada
	        carreraRepository.save(existingCarrera);
	    } else {
	        throw new Exception("Carrera no encontrada con el ID: " + carreraDTO.getCodigo());
	    }
	}

	@Override
	public void deleteCarrera(Integer id) {
		carreraRepository.deleteById(id);
	}
	


}
