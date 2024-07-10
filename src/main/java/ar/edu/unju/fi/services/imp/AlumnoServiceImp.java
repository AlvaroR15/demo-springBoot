package ar.edu.unju.fi.services.imp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger logger = LoggerFactory.getLogger(AlumnoServiceImp.class);
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private AlumnoMapper alumnoMapper;
	
	@Autowired
	private MateriaRepository materiaRepository;

	 @Override
	    public List<AlumnoDTO> getAlumnos() {
	        logger.info("Buscando a los alumnos...");
	        return alumnoMapper.toAlumnoDTOs(alumnoRepository.findAll());
	    }

	    @Override
	    public AlumnoDTO getAlumnoById(Integer id) {
	        logger.info("Buscando un alumno por su ID: ", id);
	        Optional<Alumno> alumno = alumnoRepository.findById(id);
	        return alumno.map(alumnoMapper::toAlumnoDTO).orElse(null);
	    }

	    @Override
	    public boolean saveAlumno(AlumnoDTO alumnoDTO) {
	        logger.info("Guradando alumno: ", alumnoDTO);
	        Alumno alumno = alumnoMapper.toAlumno(alumnoDTO);
	        alumnoRepository.save(alumno);
	        return true;
	    }

	    @Override
	    public void deleteAlumno(Integer id) {
	        logger.info("Borrando alumno con ID: {}", id);
	        alumnoRepository.deleteById(id);
	    }

	    @Override
	    public void editAlumno(AlumnoDTO alumnoDTO) throws Exception {
	        logger.info("Editando alumno: ", alumnoDTO);
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
	            logger.info("Alumno editado correctamente: ", alumnoToEdit);
	        } else {
	            throw new Exception("El alumno con el DNI: " + alumnoDTO.getDni() + " no fue encontrado.");
	        }
	    }

	    @Override
	    public List<AlumnoDTO> getAlumnosPorCarrera(Integer carreraCodigo) {
	        logger.info("Buscando alumnos por carrera con código: {}", carreraCodigo);
	        List<Alumno> alumnos = alumnoRepository.findAlumnosByCarreraCodigo(carreraCodigo);
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
	        logger.info("Inscribiendo alumno en materia con ID {}", alumnoId, materiaId);
	        Alumno alumno = alumnoRepository.findById(alumnoId)
	                .orElseThrow(() -> new Exception("Alumno no encontrado con ID: " + alumnoId));
	        Materia materia = materiaRepository.findById(materiaId)
	                .orElseThrow(() -> new Exception("Materia no encontrada con ID: " + materiaId));
	        alumno.getMaterias().add(materia);
	        alumnoRepository.save(alumno);
	        logger.info("Alumno inscrito correctamente: {}", alumno);
	    }

	    @Override
	    public List<Alumno> getAlumnosPorMateria(Integer materiaId) {
	        logger.info("buscando alumnos por materia con ID: {}", materiaId);
	        return alumnoRepository.findByMateriasCodigo(materiaId);
	    }
	
}
