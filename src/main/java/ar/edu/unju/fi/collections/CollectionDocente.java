package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import ar.edu.unju.fi.model.Docente;

public class CollectionDocente {
	/*	private String legajo;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	 * 
	 * */
	
	public static List<Docente> docentes = new ArrayList<Docente>();
	
	/**
	 * Devuelve todos los docentes en lista
	 */
	
	public List<Docente> getDocentes() {
		if (docentes.isEmpty()) {
			docentes.add(new Docente("1","Carolina","Apaza","caro@gmail.com","388509124"));
			docentes.add(new Docente("2","Ariel","Vega","vega@gmail.com","3885030651"));
			docentes.add(new Docente("3","Gustavo","Sosa","gvSosa@gmail.com","383559912"));
			docentes.add(new Docente("4","Marcelo","Ibarra","perez@gmail.com","387509983"));
		}
		return docentes;
	}
	
	
	/**
	 * Edita 
	 */
	
	public void editDocente(Docente nuevoDocente) {
		for(Docente docente: docentes) {
			if (docente.getLegajo().equals(nuevoDocente.getLegajo())) {
				docente.setNombre(nuevoDocente.getNombre());
				docente.setApellido(nuevoDocente.getApellido());
				docente.setEmail(nuevoDocente.getEmail());
				docente.setTelefono(nuevoDocente.getTelefono());
			}
		}
	}
	
	/**
	 * Elimina un docente si lo encuentra en lista
	 */
	
	public void deleteDocente(String legajo) {
		Iterator iterator = docentes.iterator();
		while(iterator.hasNext()) {
			Docente docente = (Docente) iterator.next();
			if (docente.getLegajo().equals(legajo)) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * Busca un docente
	 */
	
	public Docente getDocente(String legajo) {
		Predicate<Docente> filterDocente = docente -> docente.getLegajo().equals(legajo);
		Optional<Docente> docente = docentes.stream().filter(filterDocente).findFirst();
		if(docente.isPresent()) {
			return docente.get();
		} else return null;
	}
}
