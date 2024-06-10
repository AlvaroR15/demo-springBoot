package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unju.fi.model.Carrera;

public class CollectionCarrera {

	private static List<Carrera> carreras = new ArrayList<Carrera>();
	
	
	
	

	public static List<Carrera> getCarreras() {
		if(carreras.isEmpty()) {
			carreras.add(new Carrera(1,"Ingeniería Agronómica", (byte)5, "Disponible"));
			carreras.add(new Carrera(2,"Licenciatura en Bromatología", (byte)4, "Disponible"));
			carreras.add(new Carrera(3,"Licenciatura en Cs. Biológicas", (byte)4, "Disponible"));
			carreras.add(new Carrera(4,"Tecnicatura Universitaria en Producción Lechera", (byte)3, "Disponible"));
			carreras.add(new Carrera(5,"Tecnicatura Universitaria Forestal", (byte)3, "Disponible"));

		}
		return carreras;
	}
	
	
	/**
	 * Crea una carrera
	 * @param carrera
	 */
	public static boolean saveCarrera(Carrera carrera) {
		return carreras.add(carrera) ? true : false;
	}
	
	
	/**
	 * Actualiza la carrera
	 * @param nuevaCarrera
	 * @throws Exception 
	 */
	public static void editCarrera(Carrera nuevaCarrera) throws Exception {
		boolean encontrado = false;
		try {
		for (Carrera carrera: carreras) {
			if (carrera.getCodigo() == nuevaCarrera.getCodigo()) {
				carrera.setNombre(nuevaCarrera.getNombre());
				carrera.setCantidadDeAnios(nuevaCarrera.getCantidadDeAnios());
				carrera.setEstado(nuevaCarrera.getEstado());
				encontrado = true;
			}
		}
		if(!encontrado) throw new Exception("La carrera con el codigo: " + nuevaCarrera.getCodigo() + " no fue encontrada");
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		
	}
	
	
	/**
	 * Elimina la carrera 
	 */
	
	public static void deleteCarrera(Integer codigo) {
		Iterator iterator = carreras.iterator();
		while(iterator.hasNext()) {
			Carrera carrera = (Carrera) iterator.next();
			if (carrera.getCodigo() == codigo) {
				iterator.remove();
			}
		}
	}
	
	
	/**
	 * Buscar una carrera
	 */
	
	public static Carrera getCarrera(Integer codigo) {
		Predicate<Carrera> filterCarrera = carrera -> carrera.getCodigo() == codigo;
		Optional<Carrera> carrera = carreras.stream().filter(filterCarrera).findFirst();
		if (carrera.isPresent()) {
			return carrera.get();
		} else {
			return null;
		}
	}
}
