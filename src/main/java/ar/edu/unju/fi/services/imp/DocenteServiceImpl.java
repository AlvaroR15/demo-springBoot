package ar.edu.unju.fi.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.services.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService {
	
	@Autowired
	private DocenteMapper docenteMapper;
	
	@Autowired 
	private DocenteRepository docenteRepository;
	
	@Override
	public List<DocenteDTO> getDocentes() {
		return docenteMapper.toDocenteDTOs(docenteRepository.findAll());
	}

	@Override
	public DocenteDTO getDocenteById(Integer id) {
		Optional<Docente> docente = docenteRepository.findById(id);
		return docente.map(docenteMapper::toDocenteDTO).orElse(null);
	}

	@Override
	public boolean saveDocente(DocenteDTO docenteDTO) {
		Docente docente = docenteMapper.toDocente(docenteDTO);
		docenteRepository.save(docente);
		return true;
	}

	@Override
	public void deleteDocente(Integer id) {
		docenteRepository.deleteById(id);
	}

	@Override
	public void editDocente(DocenteDTO docenteDTO) throws Exception {
		Optional<Docente> docenteEncontrado = docenteRepository.findById(docenteDTO.getId());
		System.out.println("ACTUALIZANDO.......");
		if (docenteEncontrado.isPresent()) {
			Docente docenteToEdit = docenteEncontrado.get();
			docenteToEdit.setNombre(docenteDTO.getNombre());
			docenteToEdit.setApellido(docenteDTO.getApellido());
			docenteToEdit.setEmail(docenteDTO.getEmail());
			docenteToEdit.setTelefono(docenteDTO.getTelefono());
			docenteRepository.save(docenteToEdit);
		} else {
			throw new Exception("El docente con el legajo: "+docenteDTO.getLegajo()+" no fue encontrado.");
		}
	}

}
