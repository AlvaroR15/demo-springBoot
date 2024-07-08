package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.dto.MateriaDTO;

public interface IMateriaService {
	public List<MateriaDTO> getMaterias();
	
	public MateriaDTO getMateriaById(Integer id);
	
	public boolean saveMateria(MateriaDTO materiaDTO);
	
	public void deleteMateria(Integer id);
	
	public void editMateria(MateriaDTO materiaDTO) throws Exception;
}
