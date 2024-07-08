package ar.edu.unju.fi.services.imp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.services.IAlumnoService;
import jakarta.transaction.Transactional;

@Service
public class AlumnoServiceImp implements IAlumnoService {
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private AlumnoMapper alumnoMapper;
	
	@Autowired
	private MateriaRepository materiaRepository;

	@Override
	public List<AlumnoDTO> getAlumnos() {
		return alumnoMapper.toAlumnoDTOs(alumnoRepository.findAll());
	}

	@Override
	public AlumnoDTO getAlumnoById(Integer id) {
		Optional<Alumno> alumno = alumnoRepository.findById(id);
		return alumno.map(alumnoMapper::toAlumnoDTO).orElse(null);
	}

	@Override
	public boolean saveAlumno(AlumnoDTO alumnoDTO) {
		Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
		alumnoRepository.save(alumno);
		return true;
	}

	@Override
	public void deleteAlumno(Integer id) {
		alumnoRepository.deleteById(id);
	}

	@Override
	public void editAlumno(AlumnoDTO alumnoDTO) throws Exception {
		Optional<Alumno> alumnoEncontrado = alumnoRepository.findById(alumnoDTO.getId());
		if (alumnoEncontrado.isPresent()) {
	        Alumno alumnoToEdit = alumnoEncontrado.get();
	        alumnoToEdit.setNombre(alumnoDTO.getNombre());
	        alumnoToEdit.setApellido(alumnoDTO.getApellido());
	        alumnoToEdit.setEmail(alumnoDTO.getEmail());
	        alumnoToEdit.setTelefono(alumnoDTO.getTelefono());
	        alumnoToEdit.setFechaNacimiento(alumnoDTO.getFechaNacimiento());
	        alumnoToEdit.setDomicilio(alumnoDTO.getDomicilio());
	        alumnoRepository.save(alumnoToEdit);
	    } else {
	        throw new Exception("El alumno con el DNI: " + alumnoDTO.getDni() + " no fue encontrado.");
	    }
	}
	
	public List<AlumnoDTO> getAlumnosPorCarrera(Integer carreraId) {
        List<Alumno> alumnos = alumnoRepository.getCarreraById(carreraId);
        return alumnos.stream()
                      .map(alumno -> new AlumnoDTO(
                          alumno.getId(), alumno.getDni(), alumno.getNombre(), alumno.getApellido(), 
                          alumno.getEmail(), alumno.getTelefono(), alumno.getFechaNacimiento(), 
                          alumno.getDomicilio(), alumno.getLu()))
                      .collect(Collectors.toList());
    }

	
	@Override
    @Transactional
    public void inscribirAlumnoEnMateria(Integer alumnoId, Integer materiaId) throws Exception {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new Exception("Alumno no encontrado con ID: " + alumnoId));
        Materia materia = materiaRepository.findById(materiaId)
                .orElseThrow(() -> new Exception("Materia no encontrada con ID: " + materiaId));
        alumno.getMaterias().add(materia);
        alumnoRepository.save(alumno);
    }
	
	
	  @Override
	    public List<Alumno> getAlumnosPorMateria(Integer materiaId) {
	        return alumnoRepository.findByMateriasId(materiaId);
	    }
	
	
}
