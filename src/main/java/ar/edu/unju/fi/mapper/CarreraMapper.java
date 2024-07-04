package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapper {
	CarreraDTO toCarreraDTO(Carrera carrera);
	
	@InheritInverseConfiguration
	Carrera toCarrera(CarreraDTO carreraDTO);
	
	List<CarreraDTO> toCarreraDTOs(List<Carrera> carreras);
	
	List<Carrera> toCarrera(List<CarreraDTO> carrerasDTO);
	
}
