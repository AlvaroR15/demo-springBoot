package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MateriaMapper {
    MateriaDTO toMateriaDTO(Materia materia);

    @InheritInverseConfiguration
    Materia toMateria(MateriaDTO materiaDTO);

    List<MateriaDTO> toMateriaDTOs(List<Materia> materias);

    List<Materia> toMaterias(List<MateriaDTO> materiaDTOs);
}