package ar.edu.unju.fi.service;

import java.util.List;


import ar.edu.unju.fi.model.Carrera;

public interface CarreraService {
		public List<Carrera> getCarreras();
		public Carrera findById(Integer id);
		public void saveCarrera(Carrera carrera);
		public void editCarrera(Carrera carrera, Integer id);
		public void deleteCarrera(Integer id);
}
