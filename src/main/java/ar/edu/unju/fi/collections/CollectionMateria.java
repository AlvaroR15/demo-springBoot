package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

public class CollectionMateria {
/**
 * 	private String codigo;
	private String nombre;
	private String curso;
	private float cantidadHoras;
	private String modalidad;
	private Docente docente;
	private Carrera carrera;
	
	
*/

	public static List<Materia> materias = new ArrayList<Materia>();
	
	
	public List<Materia> getMaterias() {
		if (materias.isEmpty()) {
			Docente docente1 = new Docente("111","Marcelo","Ibarra","perez10@gmail.com","388602932");
			Docente docente2 = new Docente("222","Maria","Cornell","mssCornell@gmail.com","388606955");
			Docente docente3 = new Docente("333","Ariel","Vega","arVega@gmail.com","3885523329");
			
			Carrera carrera1 = new Carrera(1, "Ing. Informatica", (byte)5, "Activa");
			Carrera carrera2 = new Carrera(1, "Profesorado de Ingles", (byte)4, "Activa");
			Carrera carrera3 = new Carrera(1, "Analista Programador Universitario", (byte)3, "Activa");
			
			materias.add(new Materia("A11","Programacion Visual", "12", 6, "Virtual",docente3,carrera3));
			materias.add(new Materia("A22","Ingles Avanzado", "1", 4, "Presencial",docente2,carrera2));
			materias.add(new Materia("A33","Estructura de datos", "10", 6, "Virtual",docente1,carrera1));
		}
		return materias;
	}
	
	
	public void editMateria(Materia nuevaMateria) {
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
	
	
	public void deleteMateria(String codigo) {
		Iterator iterator = materias.iterator();
		while(iterator.hasNext()) {
			Materia materia = (Materia)iterator.next();
			if (materia.getCodigo().equals(codigo)) {
				iterator.remove();
			}
		}
	}
	
	
}
