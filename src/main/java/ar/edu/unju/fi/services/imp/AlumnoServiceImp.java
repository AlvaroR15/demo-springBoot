package ar.edu.unju.fi.services.imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.collections.CollectionAlumno;
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.services.IAlumnoService;

@Service
public class AlumnoServiceImp implements IAlumnoService {
	@Autowired
	private AlumnoMapper alumnoMapper;

	@Override
	public List<AlumnoDTO> findAll() {
		List<AlumnoDTO> alumnosDTO = alumnoMapper.toAlumnoDTO(CollectionAlumno.getAlumnos());
		return alumnosDTO;
	}

	@Override
	public AlumnoDTO findById(String dni) {
		AlumnoDTO alumnoDTO = alumnoMapper.toAlumnoDTO(CollectionAlumno.getAlumno(dni));
		return alumnoDTO;
	}

	@Override
	public boolean save(AlumnoDTO alumnoDTO) {
		boolean respuesta = CollectionAlumno.saveAlumno(alumnoMapper.toAlumno(alumnoDTO));
		return respuesta;
	}

	@Override
	public void deleteById(String dni) {
		CollectionAlumno.deleteAlumno(dni);

	}

	@Override
	public void edit(AlumnoDTO alumnoDTO) throws Exception {
		CollectionAlumno.editAlumno(alumnoMapper.toAlumno(alumnoDTO));
	}
}
