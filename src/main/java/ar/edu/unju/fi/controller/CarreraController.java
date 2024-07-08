package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.services.ICarreraService;

@Controller
@RequestMapping("/carreras")
public class CarreraController {
	
	@Autowired
	private CarreraDTO carreraDTO;
	
	@Autowired ICarreraService carreraService;
	
	@Autowired
	private Carrera carrera;
	
	/**
	 * Mostrar carreras
	 * @param model
	 * @return
	 */
	@GetMapping
	public String getCarrerasView(Model model) {
		model.addAttribute("carreras", carreraService.getCarreras());
		model.addAttribute("title", "carreras");
		model.addAttribute("response", false);
		model.addAttribute("msg", "");
		return "carreras";
	}
	
	
	/**
	 * Vista al formulario para poder crear una carrera
	 * @param nuevaCarrera
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	public String createCarreraView(Carrera nuevaCarrera, Model model) {
		String action = "create";
		model.addAttribute("titleForm","Nueva Carrera");
		model.addAttribute("action", action);
		return "carrera";
	}
	
	@PostMapping("/create")
	public String createCarrera(@ModelAttribute("carrera") CarreraDTO carreraDTO, Model model) {
		carrera.setEstado("Activa");		
		boolean response = carreraService.saveCarrera(carreraDTO);
		String msg;
		if (response) {
			msg = "¡Carrera agregada con éxito!";
		} else {
			msg = "¡Ocurrió un problema! :(";
		}
		model.addAttribute("response", response);
		model.addAttribute("msg", msg);
		model.addAttribute("carreras", carreraService.getCarreras());
		return "carreras";
	}
	
	

	/**
	 * Eliminar una carrera
	 * @param codigo
	 * @return
	 */
	@GetMapping("/delete/{codigo}")
	public String deleteCarrera(@PathVariable Integer codigo) {
		carreraService.deleteCarrera(codigo);
		return "redirect:/carreras";
	}
	
	
	
	
	/**
	 * Formulario de edicion de carrera
	 * @param codigo
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{codigo}")
	public String editCarreraView(Model model, @PathVariable(value="codigo")Integer codigo) {
		String action = "edit";
		CarreraDTO carreraEncontrada = carreraService.findById(codigo);
		model.addAttribute("titleForm", "Editar Carrera");
		model.addAttribute("action", action);
		model.addAttribute("carrera", carreraEncontrada);
		return "carrera";
	}
	
	@PostMapping("/edit")
	public String editCarrera(@ModelAttribute("carrera") CarreraDTO carreraDTO, Model model) {
	    boolean response = false;
	    String msg = "";
	    try {
	        carreraService.editCarrera(carreraDTO, carreraDTO.getCodigo());
	        msg = "La carrera con código " + carreraDTO.getCodigo() + " fue modificada con éxito";
	        response = true;
	    } catch(Exception e) {
	        msg = e.getMessage();
	        e.printStackTrace();
	    }
	    model.addAttribute("msg", msg);
	    model.addAttribute("response", response);
	    model.addAttribute("carreras", carreraService.getCarreras());
	    return "carreras";
	}
	
}



