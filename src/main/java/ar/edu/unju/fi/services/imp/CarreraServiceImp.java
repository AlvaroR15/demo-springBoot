package ar.edu.unju.fi.services.imp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.services.ICarreraService;

@Service
public class CarreraServiceImp implements ICarreraService {
    
    private static final Logger logger = LoggerFactory.getLogger(CarreraServiceImp.class);

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private CarreraMapper carreraMapper;
    
    
    @Override
    public List<CarreraDTO> getCarreras() {
        logger.info("Buscando todas las carreras disponibles...");
        return carreraMapper.toCarreraDTOs(carreraRepository.findByEstadoTrue());
    }
    
    @Override
    public CarreraDTO getCarreraById(Integer id) {
        logger.info("Buscando carrera por ID: {}", id);
        Optional<Carrera> carrera = carreraRepository.findById(id);
        return carrera.map(carreraMapper::toCarreraDTO).orElse(null);
    }

    @Override
	public boolean saveCarrera(CarreraDTO carreraDTO) {
    	logger.info("Guardando una nueva carrera");
		Carrera carrera = carreraMapper.toCarrera(carreraDTO);
		carrera.setEstado(true);
		carreraRepository.save(carrera);
		return true;
	}


    @Override
    public void editCarrera(CarreraDTO carreraDTO) throws Exception {
        logger.info("Editando carrera: {}", carreraDTO);
        Optional<Carrera> existingCarreraOpt = carreraRepository.findById(carreraDTO.getCodigo());
        if (existingCarreraOpt.isPresent()) {
            Carrera existingCarrera = existingCarreraOpt.get();
            // Actualizar los campos necesarios
            existingCarrera.setNombre(carreraDTO.getNombre());
            existingCarrera.setCantidadDeAnios(carreraDTO.getCantidadDeAnios());
            existingCarrera.setEstado(carreraDTO.isEstado());
            // Guardar la carrera actualizada
            carreraRepository.save(existingCarrera);
            logger.info("Carrera editada correctamente: {}", existingCarrera);
        } else {
            throw new Exception("Carrera no encontrada con el ID: " + carreraDTO.getCodigo());
        }
    }

    @Override
    public void deleteCarrera(Integer id) {
        logger.info("Inactivando carrera con ID: {}", id);
        
        Optional<Carrera> optionalCarrera = carreraRepository.findById(id);
        
        if (optionalCarrera.isPresent()) {
            Carrera carrera = optionalCarrera.get();
            carrera.setEstado(false);
            
            carreraRepository.save(carrera);
            
            logger.info("Carrera inactivada correctamente: {}", carrera);
        } else {
            logger.warn("No se encontró ninguna carrera con ID: {}", id);
        }
    }
}
