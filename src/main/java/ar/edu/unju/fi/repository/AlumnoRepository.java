package ar.edu.unju.fi.repository;
import ar.edu.unju.fi.model.Alumno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	@Query("SELECT a FROM Alumno a WHERE a.carrera.codigo = :carreraCodigo")
    List<Alumno> findAlumnosByCarreraCodigo(@Param("carreraCodigo") Integer carreraCodigo);
	
    List<Alumno> findByMateriasCodigo(Integer materiaCodigo);

}