package ar.edu.unju.fi.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;


public class CollectionAlumno {

	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	/**
	 * Devolver usuarios para mostrarlos
	 */
	
	public static List<Alumno> getAlumnos() {
		if (alumnos.isEmpty()) {
			alumnos.add(new Alumno("42123543","Ezequiel","Fernandez","equi@gmail.com","3885494890", LocalDate.of(12, 3, 2001), "La Plata, Buenos Aires", "2112"));
			alumnos.add(new Alumno("40112983","Cristian","Medina","cristian36@gmail.com","3885194360", LocalDate.of(1, 9, 2003), "La Boca, Buenos Aires", "3612"));
			alumnos.add(new Alumno("44101649","Kevin","Zenon","kevin22Boca@gmail.com","9115494780", LocalDate.of(22, 2, 2002), "San Salvador de Jujuy, Jujuy", "2245"));
			alumnos.add(new Alumno("35103643","Edinson","Cavani","matador@gmail.com","3835794892", LocalDate.of(15, 03, 1990), "Salto, Uruguay", "1034"));
		}
		return alumnos;
	}
	
	
	/**
	 * Crea un nuevo alumno en la lista
	 * @param alumno
	 */
	public void saveAlumno(Alumno alumno) {
		alumnos.add(alumno);
	}
	

	/**
	 * Busca un alumno en la lista segun su dni y lo elimina
	 * @param dni
	 */
	public void deleteAlumno(String dni) {
		Iterator iterator = alumnos.iterator();
		while(iterator.hasNext()) {
			Alumno alumno = (Alumno) iterator.next();
			if (alumno.getDni().equals(dni)) {
				iterator.remove();
			}
		}
	}
	
	
	/**
	 * Actualiza los datos del alumno si este se encuentra en la lista
	 * @param nuevoAlumno
	 */
	public void editAlumno(Alumno nuevoAlumno) {
		for (Alumno alumno: alumnos) {
			if (alumno.getDni().equals(nuevoAlumno.getDni())) {
				alumno.setNombre(nuevoAlumno.getNombre());
				alumno.setApellido(nuevoAlumno.getApellido());
				alumno.setEmail(nuevoAlumno.getEmail());
				alumno.setFechaNacimiento(nuevoAlumno.getFechaNacimiento());
			}
		}
	}
	
	/**
	 * Busca un alumno
	 */
	
	public Alumno getAlumno(String dni) {
		Predicate<Alumno> filterAlumno = alumno -> alumno.getDni().equals(dni);
		Optional<Alumno> alumno = alumnos.stream().filter(filterAlumno).findFirst();
		if (alumno.isPresent()) {
			return alumno.get();
		} else return null;
	}
	
}
