package ar.edu.unju.fi.repository;
import ar.edu.unju.fi.model.Alumno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    List<Alumno> getCarreraById(Integer carreraId);
    List<Alumno> findByMateriasId(Integer materiaId);

}