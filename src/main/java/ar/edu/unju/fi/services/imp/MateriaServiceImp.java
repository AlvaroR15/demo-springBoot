package ar.edu.unju.fi.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.services.IMateriaService;

@Service
public class MateriaServiceImp implements IMateriaService  {
	
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
		return materiaMapper.toMateriaDTOs(materiaRepository.findAll());
	}

	@Override
	public MateriaDTO getMateriaById(Integer id) {
		Optional<Materia> materia = materiaRepository.findById(id);
		return materia.map(materiaMapper::toMateriaDTO).orElse(null);
	}

	@Override
	public boolean saveMateria(MateriaDTO materiaDTO) {
		Materia materia = materiaMapper.toMateria(materiaDTO);
		materiaRepository.save(materia);
		return true;
	}

	@Override
	public void deleteMateria(Integer id) {
		materiaRepository.deleteById(id);
	}

	@Override
	public void editMateria(MateriaDTO materiaDTO) throws Exception {
	    Optional<Materia> materiaEncontrada = materiaRepository.findById(materiaDTO.getId());
	    if (materiaEncontrada.isPresent()) {
	        Materia materiaToEdit = materiaEncontrada.get();
	        materiaToEdit.setCodigo(materiaDTO.getCodigo());
	        materiaToEdit.setNombre(materiaDTO.getNombre());
	        materiaToEdit.setCurso(materiaDTO.getCurso());
	        materiaToEdit.setModalidad(materiaDTO.getModalidad());
	        materiaToEdit.setCantidadHoras(materiaDTO.getCantidadHoras());
	        materiaToEdit.setDocente(docenteMapper.toDocente(materiaDTO.getDocente()));
	        materiaToEdit.setCarrera(carreraMapper.toCarrera(materiaDTO.getCarrera()));
	        materiaRepository.save(materiaToEdit);
	    } else {
	        throw new Exception("La materia con el código: " + materiaDTO.getCodigo() + " no fue encontrada.");
	    }
	}

	


}
