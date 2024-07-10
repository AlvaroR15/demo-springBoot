package ar.edu.unju.fi.services.imp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.services.IMateriaService;

@Service
public class MateriaServiceImp implements IMateriaService {
    
    private static final Logger logger = LoggerFactory.getLogger(MateriaServiceImp.class);
    
    @Autowired
    private MateriaRepository materiaRepository;
    
    @Autowired
    private MateriaMapper materiaMapper;
    
    @Autowired
    private DocenteMapper docenteMapper;
    
    @Autowired
    private CarreraMapper carreraMapper;

    @Override
    public List<MateriaDTO> getMaterias() {
        logger.info("Buscando todas las materias...");
        return materiaMapper.toMateriaDTOs(materiaRepository.findAll());
    }

    @Override
    public MateriaDTO getMateriaById(Integer id) {
        logger.info("Buscando materia por ID: {}", id);
        Optional<Materia> materia = materiaRepository.findById(id);
        return materia.map(materiaMapper::toMateriaDTO).orElse(null);
    }

    @Override
    public boolean saveMateria(MateriaDTO materiaDTO) {
        logger.info("Guardando materia: {}", materiaDTO);
        Materia materia = materiaMapper.toMateria(materiaDTO);
        materiaRepository.save(materia);
        return true;
    }

    @Override
    public void deleteMateria(Integer id) {
        logger.warn("Borrando materia con ID: {}", id);
        materiaRepository.deleteById(id);
    }

    @Override
    public void editMateria(MateriaDTO materiaDTO) throws Exception {
        logger.info("Editando materia: {}", materiaDTO);
        Optional<Materia> materiaEncontrada = materiaRepository.findById(materiaDTO.getCodigo());
        if (materiaEncontrada.isPresent()) {
            Materia materiaToEdit = materiaEncontrada.get();
            materiaToEdit.setNombre(materiaDTO.getNombre());
            materiaToEdit.setCurso(materiaDTO.getCurso());
            materiaToEdit.setModalidad(materiaDTO.getModalidad());
            materiaToEdit.setCantidadHoras(materiaDTO.getCantidadHoras());
            materiaToEdit.setDocente(docenteMapper.toDocente(materiaDTO.getDocente()));
            materiaToEdit.setCarrera(carreraMapper.toCarrera(materiaDTO.getCarrera()));
            materiaRepository.save(materiaToEdit);
            logger.info("Materia editada correctamente: {}");
        } else {
        	logger.error("Error al editar");
            throw new Exception("La materia no fue encontrada.");
        }
    }
}
