package ar.edu.unju.fi.repository;
import ar.edu.unju.fi.model.Alumno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    List<Alumno> getCarreraById(Integer codigo);

    List<Alumno> findByMateriasCodigo(Integer materiaCodigo);

}