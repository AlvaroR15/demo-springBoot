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

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.services.IDocenteService;

@Controller
@RequestMapping("/docentes")
public class DocenteController {

	@Autowired
	private DocenteDTO docenteDTO;
	
	@Autowired
	private IDocenteService docenteService;
	
	@GetMapping
	public String listDocentesView(Model model) {
		model.addAttribute("docentes", docenteService.findAll());
		model.addAttribute("response", false);
		model.addAttribute("msg","");
		model.addAttribute("title", "docentes");
		return "docentes";
	}
	
	
	@GetMapping("/edit/{legajo}")
	public String editDocenteView(@PathVariable String legajo, Model model) {
		DocenteDTO docenteEncontradoDTO = docenteService.findById(legajo);
		String action = "edit";
		//docenteEncontrado = CollectionDocente.getDocente(legajo);
		model.addAttribute("titleForm", "Editar docente");
		model.addAttribute("action", action);
		model.addAttribute("docente", docenteEncontradoDTO);
		return "docente";
	}
	
	
	@PostMapping("/edit")
	public String editDocente(@ModelAttribute DocenteDTO docenteDTO, Model model) {
		boolean response = false;
		String msg = "";
		try {
			docenteService.edit(docenteDTO);
			msg = "El docente con legajo "+docenteDTO.getLegajo()+" fue modificado con éxito";
			response = true;
		} catch(Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("msg", msg);
		model.addAttribute("response", response);
		model.addAttribute("docentes", docenteService.findAll());
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
	public String createDocente(@ModelAttribute DocenteDTO docenteDTO, Model model) {
		boolean response = docenteService.save(docenteDTO);
		String msg;
		if (response) {
			msg = "¡Docente agregado con éxito!";
		} else {
			msg = "¡Ocurrió un problema! :(";
		}
		model.addAttribute("response", response);
		model.addAttribute("msg", msg);
		model.addAttribute("docentes", docenteService.findAll());
		return "docentes";
	}
	
	
	@GetMapping("/delete/{legajo}")
	public String deleteDocente(@PathVariable String legajo) {
		docenteService.deleteById(legajo);
		return "redirect:/docentes";
	}
	
}













