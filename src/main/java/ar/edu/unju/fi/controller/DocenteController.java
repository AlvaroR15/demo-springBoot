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

import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.model.Docente;

@Controller
@RequestMapping("/docentes")
public class DocenteController {

	@Autowired
	private Docente docente;
	
	
	@GetMapping
	public String listDocentesView(Model model) {
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("response", false);
		model.addAttribute("msg","");
		model.addAttribute("title", "docentes");
		return "docentes";
	}
	
	
	@GetMapping("/edit/{legajo}")
	public String editDocenteView(@PathVariable String legajo, Model model) {
		Docente docenteEncontrado = new Docente();
		String action = "edit";
		docenteEncontrado = CollectionDocente.getDocente(legajo);
		model.addAttribute("titleForm", "Editar docente");
		model.addAttribute("action", action);
		model.addAttribute("docente", docenteEncontrado);
		return "docente";
	}
	
	
	@PostMapping("/edit")
	public String editDocente(@ModelAttribute Docente docente, Model model) {
		boolean response = false;
		String msg = "";
		try {
			CollectionDocente.editDocente(docente);
			msg = "El docente con legajo "+docente.getLegajo()+" fue modificado con éxito";
			response = true;
		} catch(Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("msg", msg);
		model.addAttribute("response", response);
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		return "docentes";
	}
	
	
	
	
	@GetMapping("/create")
	public String createDocenteView(Docente nuevoDocente,Model model) {
		String action = "create";
		model.addAttribute("action", action);
		model.addAttribute("titleForm", "Crear docente");
		return "docente";
	}
	
	@PostMapping("/create")
	public String createDocente(@ModelAttribute Docente docente, Model model) {
		boolean response = CollectionDocente.saveDocente(docente);
		String msg;
		if (response) {
			msg = "¡Docente agregado con éxito!";
		} else {
			msg = "¡Ocurrió un problema! :(";
		}
		model.addAttribute("response", response);
		model.addAttribute("msg", msg);
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		return "docentes";
	}
	
	
	@GetMapping("/delete/{legajo}")
	public String deleteDocente(@PathVariable String legajo) {
		CollectionDocente.deleteDocente(legajo);
		return "redirect:/docentes";
	}
	
}













