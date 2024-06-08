package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

public class CollectionMateria {
	
	
	

	public static List<Materia> materias = new ArrayList<Materia>();
	
	public static List<Materia> getMaterias() {
		CollectionCarrera.getCarreras();
		CollectionDocente.getDocentes();
		if (materias.isEmpty()) {
		            materias.add(new Materia("A11", "Programacion Visual", "12", (byte) 6, "Virtual", CollectionDocente.getDocente("1"), CollectionCarrera.getCarrera(1)));
		            materias.add(new Materia("A22", "Ingles Avanzado", "1", (byte) 4, "Presencial", CollectionDocente.getDocente("2"), CollectionCarrera.getCarrera(2)));
		}
		System.out.println(materias);
		return materias;
	}
	
	
	public static void editMateria(Materia nuevaMateria) {
		for (Materia materia: materias) {
			if (materia.getCodigo().equals(nuevaMateria.getCodigo())) {
				materia.setNombre(nuevaMateria.getNombre());
				materia.setCurso(nuevaMateria.getCurso());
				materia.setCantidadHoras(nuevaMateria.getCantidadHoras());
				materia.setModalidad(nuevaMateria.getModalidad());
				materia.setDocente(nuevaMateria.getDocente());
				materia.setCarrera(nuevaMateria.getCarrera());
			}
		}
	}
	
	public static void saveMateria(Materia nuevaMateria) {
		materias.add(nuevaMateria);
	}
	
	
	/**
	 * Borra una materia
	 * @param codigo
	 */
	public static void deleteMateria(String codigo) {
		Iterator iterator = materias.iterator();
		while(iterator.hasNext()) {
			Materia materia = (Materia)iterator.next();
			if (materia.getCodigo().equals(codigo)) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * Buscar una materia
	 */
	
	public static Materia getMateria(String codigo) { 
		Predicate<Materia> filterMateria = materia -> materia.getCodigo().equals(codigo);
		Optional<Materia> materia = materias.stream().filter(filterMateria).findFirst();
		if (materia.isPresent()) {
			return materia.get();
		} else {
			return null;
		}
		
	}
	
}
