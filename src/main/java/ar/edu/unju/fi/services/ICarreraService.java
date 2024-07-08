package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

public interface ICarreraService {
		public List<CarreraDTO> getCarreras();
		public CarreraDTO findById(Integer id);
		public boolean saveCarrera(CarreraDTO carreraDTO);
		void editCarrera(CarreraDTO carreraDTO, Integer id) throws Exception;
		public void deleteCarrera(Integer id);
}
