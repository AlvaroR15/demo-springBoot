package ar.edu.unju.fi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.fi.model.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
	List<Carrera> findByEstadoTrue();
}
