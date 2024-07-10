package ar.edu.unju.fi.services.imp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.services.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {
    
    private static final Logger logger = LoggerFactory.getLogger(DocenteServiceImpl.class);
    
    @Autowired
    private DocenteMapper docenteMapper;
    
    @Autowired 
    private DocenteRepository docenteRepository;
    
    @Override
    public List<DocenteDTO> getDocentes() {
        logger.info("Buscando todos los docentes...");
        return docenteMapper.toDocenteDTOs(docenteRepository.findAll());
    }

    @Override
    public DocenteDTO getDocenteById(Integer id) {
        logger.info("Buscando docente por ID: {}", id);
        Optional<Docente> docente = docenteRepository.findById(id);
        return docente.map(docenteMapper::toDocenteDTO).orElse(null);
    }

    @Override
    public boolean saveDocente(DocenteDTO docenteDTO) {
        Docente docente = docenteMapper.toDocente(docenteDTO);
        docenteRepository.save(docente);
        logger.info("Docente guardado correctamente");
        return true;
    }

    @Override
    public void deleteDocente(Integer id) {
        logger.warn("Borrando docente con ID: {}", id);
        docenteRepository.deleteById(id);
    }

    @Override
    public void editDocente(DocenteDTO docenteDTO) throws Exception {
        logger.info("Editando docente: {}", docenteDTO);
        Optional<Docente> docenteEncontrado = docenteRepository.findById(docenteDTO.getId());
        if (docenteEncontrado.isPresent()) {
            Docente docenteToEdit = docenteEncontrado.get();
            docenteToEdit.setNombre(docenteDTO.getNombre());
            docenteToEdit.setApellido(docenteDTO.getApellido());
            docenteToEdit.setEmail(docenteDTO.getEmail());
            docenteToEdit.setTelefono(docenteDTO.getTelefono());
            docenteRepository.save(docenteToEdit);
            logger.info("Docente editado correctamente");
        } else {
        	logger.error("Error al editar docente");
            throw new Exception("El docente con el legajo: " + docenteDTO.getLegajo() + " no fue encontrado.");
        }
    }

}
