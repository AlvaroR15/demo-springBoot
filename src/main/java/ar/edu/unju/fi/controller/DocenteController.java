package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.services.IDocenteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/docentes")
public class DocenteController {

	@Autowired
	private DocenteDTO docenteDTO;
	
	@Autowired
	private IDocenteService docenteService;
	
	@GetMapping
	public String listDocentesView(Model model) {
		model.addAttribute("docentes", docenteService.getDocentes());
		model.addAttribute("response", false);
		model.addAttribute("msg","");
		model.addAttribute("title", "docentes");
		return "docentes";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editDocenteView(@PathVariable Integer id, Model model) {
	    DocenteDTO docenteEncontradoDTO = docenteService.getDocenteById(id);
	    model.addAttribute("titleForm", "Editar Docente");
	    model.addAttribute("action", "edit");
	    model.addAttribute("docente", docenteEncontradoDTO);
	    return "docente";
	}

	@PostMapping("/edit")
	public String editDocente(@Valid @ModelAttribute("docente") DocenteDTO docenteDTO,
	                          BindingResult bindingResult,
	                          Model model) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("titleForm", "Editar Docente");
	        model.addAttribute("action", "edit");
	        return "docente";
	    }

	    boolean response = false;
	    String msg = "";
	    try {
	        docenteService.editDocente(docenteDTO);
	        msg = "El docente con legajo " + docenteDTO.getLegajo() + " fue modificado con éxito";
	        response = true;
	    } catch (Exception e) {
	        msg = e.getMessage();
	        e.printStackTrace();
	    }
	    model.addAttribute("msg", msg);
	    model.addAttribute("response", response);
	    model.addAttribute("docentes", docenteService.getDocentes());
	    return "docentes";
	}
	
	
	
	
	@GetMapping("/create")
	public String createDocenteView(Model model) {
	    DocenteDTO docenteDTO = new DocenteDTO();
	    model.addAttribute("action", "create");
	    model.addAttribute("titleForm", "Crear Docente");
	    model.addAttribute("docente", docenteDTO);
	    return "docente";
	}

	@PostMapping("/create")
	public String createDocente(@Valid @ModelAttribute("docente") DocenteDTO docenteDTO,
	                            BindingResult bindingResult,
	                            Model model) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("action", "create");
	        model.addAttribute("titleForm", "Crear Docente");
	        return "docente";
	    }

	    boolean response = docenteService.saveDocente(docenteDTO);
	    String msg = response ? "¡Docente agregado con éxito!" : "¡Ocurrió un problema! :(";
	    model.addAttribute("response", response);
	    model.addAttribute("msg", msg);
	    model.addAttribute("docentes", docenteService.getDocentes());
	    return "docentes";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteDocente(@PathVariable Integer id) {
		docenteService.deleteDocente(id);
		return "redirect:/docentes";
	}
	
}













