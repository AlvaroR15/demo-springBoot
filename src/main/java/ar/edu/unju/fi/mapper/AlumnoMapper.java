package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlumnoMapper {
    AlumnoDTO toAlumnoDTO(Alumno alumno);

    @InheritInverseConfiguration
    Alumno toAlumno(AlumnoDTO alumnoDTO);

    List<AlumnoDTO> toMateriaDTOs(List<Alumno> alumnos);

    List<Alumno> toMaterias(List<AlumnoDTO> AlumnoDTOs);
}