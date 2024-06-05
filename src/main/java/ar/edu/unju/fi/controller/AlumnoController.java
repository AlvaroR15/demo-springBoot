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
	public ModelAndView createAlumno(@ModelAttribute Alumno nuevoAlumno, Model model) {
		ModelAndView modelView = new ModelAndView("alumnos");
		CollectionAlumno.saveAlumno(nuevoAlumno);
		modelView.addObject("alumnos", CollectionAlumno.getAlumnos());
		return modelView;
	}
	
	@PostMapping("/edit")
	public String editAlumno(@ModelAttribute Alumno alumno, Model model) {
		CollectionAlumno.editAlumno(alumno);
		return "redirect:/alumnos";
	}
	
	@GetMapping("/delete/{dni}")
	public String deleteAlumno(@PathVariable String dni) {
		CollectionAlumno.deleteAlumno(dni);
		return "redirect:/alumnos";
	}
	
}
