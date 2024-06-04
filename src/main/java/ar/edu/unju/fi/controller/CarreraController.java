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
import ar.edu.unju.fi.model.Carrera;

@Controller
@RequestMapping("/carreras")
public class CarreraController {
	
	@Autowired
	private Carrera carrera;
	
	/**
	 * Mostrar carreras
	 * @param model
	 * @return
	 */
	@GetMapping
	public String getCarrerasView(Model model) {
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("title", "carreras");
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
	public ModelAndView createCarrera(@ModelAttribute("carrera") Carrera carrera) {
		ModelAndView modelView = new ModelAndView("carreras");
		carrera.setEstado("Activa");
		CollectionCarrera.saveCarrera(carrera);
		modelView.addObject("carreras", CollectionCarrera.getCarreras());
		return modelView;
	}
	
	

	/**
	 * Eliminar una carrera
	 * @param codigo
	 * @return
	 */
	@GetMapping("/delete/{codigo}")
	public String deleteCarrera(@PathVariable Integer codigo) {
		CollectionCarrera.deleteCarrera(codigo);
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
		Carrera carreraEncontrada = new Carrera();
		String action = "edit";
		carreraEncontrada = CollectionCarrera.getCarrera(codigo);
		model.addAttribute("titleForm", "Editar Carrera");
		model.addAttribute("action", action);
		model.addAttribute("carrera", carreraEncontrada);
		return "carrera";
	}
	
	@PostMapping("/edit")
	public String editCarrera(@ModelAttribute("carrera")Carrera carrera) {
		CollectionCarrera.editCarrera(carrera);
		return "redirect:/carreras";
	}
	
}



