package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.collections.CollectionCarrera;

@Controller
@RequestMapping("/admin/carreras")
public class CarreraController {
	
	@GetMapping
	public String getCarrerasHTML(Model model) {
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("title", "carreras");
		return "carreras";
	}
	


	
	@GetMapping("/delete/{codigo}")
	public String deleteCarrera(@PathVariable Integer codigo) {
		CollectionCarrera.deleteCarrera(codigo);
		return "redirect:/admin/carreras";
	}

}