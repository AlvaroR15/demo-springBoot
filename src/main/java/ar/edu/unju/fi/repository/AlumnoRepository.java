package ar.edu.unju.fi.repository;
import ar.edu.unju.fi.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlumnoRepository extends JpaRepository<Alumno, String> {

}