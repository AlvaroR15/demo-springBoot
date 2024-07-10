package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

public interface IAlumnoService {
	public List<AlumnoDTO> getAlumnos();
	
	public AlumnoDTO getAlumnoById(Integer id);
	
	public boolean saveAlumno(AlumnoDTO alumnoDTO);
	
	public void deleteAlumno(Integer id);
	
	public void editAlumno(AlumnoDTO alumnoDTO) throws Exception;

	void inscribirAlumnoEnMateria(Integer alumnoId, Integer materiaId) throws Exception;

	public List<AlumnoDTO> getAlumnosPorCarrera(Integer carreraId);
	
    List<AlumnoDTO> getAlumnosPorMateria(Integer materiaId);

	
}
