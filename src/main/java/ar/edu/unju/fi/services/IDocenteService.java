package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.dto.DocenteDTO;

public interface IDocenteService {
	List<DocenteDTO> getDocentes();
	
	DocenteDTO getDocenteById(Integer id);
	
	boolean saveDocente(DocenteDTO docenteDTO);
	
	void deleteDocente(Integer id);
	
	void editDocente(DocenteDTO docenteDTO) throws Exception;
}
