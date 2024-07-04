package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;

public interface IAlumnoService {
	List<AlumnoDTO> findAll();
	
	AlumnoDTO findById(String dni);
	
	boolean save(AlumnoDTO alumnoDTO);
	
	void deleteById(String dni);
	
	void edit(AlumnoDTO alumnoDTO) throws Exception;
}
