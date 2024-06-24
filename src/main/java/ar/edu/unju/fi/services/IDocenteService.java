package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.dto.DocenteDTO;

public interface IDocenteService {
	List<DocenteDTO> findAll();
	
	DocenteDTO findById(String legajo);
	
	boolean save(DocenteDTO docenteDTO);
	
	void deleteById(String legajo);
	
	void edit(DocenteDTO docenteDTO) throws Exception;
}
