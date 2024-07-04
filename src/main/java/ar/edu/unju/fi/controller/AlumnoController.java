package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.services.IAlumnoService;
import ar.edu.unju.fi.model.Alumno;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

	@Autowired
	private AlumnoDTO alumnoDTO;
	
	@Autowired
	private IAlumnoService alumnoService;
	
	
	@GetMapping
	public String listAlumnosView(Model model) {
		model.addAttribute("alumnos", alumnoService.findAll());
		model.addAttribute("title", "alumnos");
		model.addAttribute("response", false);
		model.addAttribute("msg", "");
		return "alumnos";
	}
	

	@GetMapping("/create")
	public String createAlumnoView(AlumnoDTO docenteDTO,Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("titleForm", "Nuevo Alumno");
		return "alumno";
	}
	
	@GetMapping("/edit/{dni}")
	public String editAlumnoView(@PathVariable String dni, Model model) {
		AlumnoDTO alumnoEncontradoDTO = alumnoService.findById(dni);
		model.addAttribute("action", "edit");
		model.addAttribute("titleForm", "Editar Alumno");
		model.addAttribute("alumno", alumnoEncontradoDTO);
		return "alumno";
	}
	
	
	@PostMapping("/create")
	public String createAlumno(@ModelAttribute AlumnoDTO alumnoDTO, Model model) {
		boolean response = alumnoService.save(alumnoDTO);
		String msg;
		if (response) {
			msg = "Alumno agregado con éxito!";
		} else {
			msg = "¡Ocurrió un problema! :(";
		}
		model.addAttribute("response", response);
		model.addAttribute("msg", msg);
		model.addAttribute("alumno", alumnoService.findAll());
		return "alumnos";
	}
	
	@PostMapping("/edit")
	public String editAlumno(@ModelAttribute AlumnoDTO alumnoDTO, Model model) {
		boolean response = false;
		String msg = "";
		try {
			alumnoService.edit(alumnoDTO);
			msg = "El alumno con el dni "+alumnoDTO.getDni()+" fue modificado con éxito";
			response = true;
		} catch(Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("msg", msg);
		model.addAttribute("response", response);
		model.addAttribute("alumnos", alumnoService.findAll());
		return "alumnos";
	}
	
	@GetMapping("/delete/{dni}")
	public String deleteAlumno(@PathVariable String dni) {
		alumnoService.deleteById(dni);
		return "redirect:/alumnos";
	}
	
}
