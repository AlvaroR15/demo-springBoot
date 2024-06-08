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
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.collections.CollectionMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Controller
@RequestMapping("/materias")
public class MateriaController {
	@Autowired
	private Materia materia;
	private Docente docente;
	private Carrera carrera;
	
	
	@GetMapping
	public String getMateriasView(Model model) {
		model.addAttribute("title", "Materias");
		model.addAttribute("materias", CollectionMateria.getMaterias());
		return "materias";
	}
	
	@GetMapping("/create")
	public String createMateriaView(Materia nuevaMateria, Model model) {
		model.addAttribute("titleForm", "Crear materia");
		model.addAttribute("action", "create");
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		return "materia";
	}
	
	@GetMapping("/edit/{codigo}")
	public String editMateriaView(@PathVariable String codigo, Model model) {
		model.addAttribute("titleForm", "Editar materia");
		model.addAttribute("materia", CollectionMateria.getMateria(codigo));
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("action", "edit");
		return "materia";
	}
	
	
	@PostMapping("/create")
	public String createMateria(@ModelAttribute Materia nuevaMateria, Model model) {
		/*ModelAndView modelView = new ModelAndView("materias");
		CollectionMateria.saveMateria(nuevaMateria);
		modelView.addObject("materias", CollectionMateria.getMaterias());
		return modelView;*/

		Docente docente = CollectionDocente.getDocente(nuevaMateria.getDocente().getLegajo());
		Carrera carrera = CollectionCarrera.getCarrera(nuevaMateria.getCarrera().getCodigo());
		nuevaMateria.setDocente(docente);
		nuevaMateria.setCarrera(carrera);
		CollectionMateria.saveMateria(nuevaMateria);
		model.addAttribute("materias", CollectionMateria.getMaterias());
		return "redirect:/materias";
	}
	
	@PostMapping("/edit")
	public String editMateria(@ModelAttribute Materia materia) {
		Docente docente = CollectionDocente.getDocente(materia.getDocente().getLegajo());
		Carrera carrera = CollectionCarrera.getCarrera(materia.getCarrera().getCodigo());
		materia.setDocente(docente);
		materia.setCarrera(carrera);
		CollectionMateria.editMateria(materia);
		return "redirect:/materias";
	}
	
	@GetMapping("/delete/{codigo}")
	public String deleteMateria(@PathVariable String codigo) {
		CollectionMateria.deleteMateria(codigo);
		return "redirect:/materias";
	}
	
}
