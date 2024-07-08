package ar.edu.unju.fi.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.services.ICarreraService;

public class CarreraServiceImp implements ICarreraService {

	@Autowired
	private CarreraMapper carreraMapper;
	
	
	@Override
	public List<CarreraDTO> getCarreras() {
		List<CarreraDTO> carrerasDTO = carreraMapper.toCarreraDTOs(CollectionCarrera.getCarreras());
		return carrerasDTO;
	}

	@Override
	public CarreraDTO findById(Integer id) {
		return carreraMapper.toCarreraDTO(CollectionCarrera.getCarrera(id));
	}

	@Override
	public boolean saveCarrera(CarreraDTO carreraDTO) {
		boolean respuesta = CollectionCarrera.saveCarrera(carreraMapper.toCarrera(carreraDTO));
		return respuesta;
	}

	@Override
	public void editCarrera(CarreraDTO carreraDTO, Integer id) throws Exception {
	    Carrera carreraEncontrada = CollectionCarrera.getCarrera(id);
	    if (carreraEncontrada != null) {
	        carreraEncontrada.setNombre(carreraDTO.getNombre());
	        carreraEncontrada.setCantidadDeAnios(carreraDTO.getCantidadDeAnios());
	        carreraEncontrada.setEstado(carreraDTO.getEstado());
	        CollectionCarrera.editCarrera(carreraEncontrada);
	    } else {
	        throw new Exception("Carrera no encontrada con ID: " + id);
	    }
	}

	@Override
	public void deleteCarrera(Integer id) {
		CollectionCarrera.deleteCarrera(id);
	}


}
