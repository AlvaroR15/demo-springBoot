package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ar.edu.unju.fi.model.Carrera;

public class CollectionCarrera {

	public static List<Carrera> carreras = new ArrayList<Carrera>();
	
	/*
	 * private Integer codigo;
	private String nombre;
	private Byte cantidadDeAnios;
	private String estado;*/
	public List<Carrera> getCarreras() {
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
	public void saveCarrera(Carrera carrera) {
		carreras.add(carrera);
	}
	
	
	/**
	 * Actualiza la carrera
	 * @param nuevaCarrera
	 */
	public void editCarrera(Carrera nuevaCarrera) {
		for (Carrera carrera: carreras) {
			if (carrera.getCodigo() == nuevaCarrera.getCodigo()) {
				carrera.setNombre(nuevaCarrera.getNombre());
				carrera.setCantidadDeAnios(nuevaCarrera.getCantidadDeAnios());
				carrera.setEstado(nuevaCarrera.getEstado());
			}
		}
	}
	
	
	/**
	 * Elimina la carrera 
	 */
	
	public void deleteCarrera(Integer codigo) {
		Iterator iterator = carreras.iterator();
		while(iterator.hasNext()) {
			Carrera carrera = (Carrera) iterator.next();
			if (carrera.getCodigo() == codigo) {
				iterator.remove();
			}
		}
	}
	
}
