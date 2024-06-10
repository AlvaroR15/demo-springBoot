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

import ar.edu.unju.fi.collections.CollectionAlumno;
import ar.edu.unju.fi.model.Alumno;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

	@Autowired
	private Alumno alumno;
	
	
	@GetMapping
	public String getAlumnosView(Model model) {
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		model.addAttribute("title", "alumnos");
		model.addAttribute("response", false);
		model.addAttribute("msg", "");
		return "alumnos";
	}
	

	@GetMapping("/create")
	public String createAlumnoView(Alumno nuevoAlumno, Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("titleForm", "Nuevo Alumno");
		return "alumno";
	}
	
	@GetMapping("/edit/{dni}")
	public String editAlumnoView(@PathVariable String dni, Model model) {
		model.addAttribute("action", "edit");
		model.addAttribute("titleForm", "Editar Alumno");
		model.addAttribute("alumno", CollectionAlumno.getAlumno(dni));
		return "alumno";
	}
	
	
	@PostMapping("/create")
	public String createAlumno(@ModelAttribute Alumno nuevoAlumno, Model model) {
		boolean response = CollectionAlumno.saveAlumno(nuevoAlumno);
		String msg;
		if (response) {
			msg = "Alumno agregado con éxito!";
		} else {
			msg = "¡Ocurrió un problema! :(";
		}
		model.addAttribute("response", response);
		model.addAttribute("msg", msg);
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		return "alumnos";
	}
	
	@PostMapping("/edit")
	public String editAlumno(@ModelAttribute Alumno alumno, Model model) {
		boolean response = false;
		String msg = "";
		try {
			CollectionAlumno.editAlumno(alumno);
			msg = "El alumno con DNI "+alumno.getDni()+" fue modificado con éxito";
			response = true;
		} catch(Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("msg", msg);
		model.addAttribute("response", response);
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		return "alumnos";
	}
	
	@GetMapping("/delete/{dni}")
	public String deleteAlumno(@PathVariable String dni) {
		CollectionAlumno.deleteAlumno(dni);
		return "redirect:/alumnos";
	}
	
}
