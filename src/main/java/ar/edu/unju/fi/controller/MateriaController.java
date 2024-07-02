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
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Controller
@RequestMapping("/materias")
public class MateriaController {
	@Autowired
    private MateriaMapper materiaMapper;
    
  /*  @Autowired
    private Docente docente;
    
    @Autowired
    private Carrera carrera;
	*/
	
	@GetMapping
	public String getMateriasView(Model model) {
		model.addAttribute("title", "Materias");
		model.addAttribute("materias", materiaMapper.toMateriaDTOs(CollectionMateria.getMaterias()));
		model.addAttribute("response", false);
		model.addAttribute("msg", "");
		return "materias";
	}
	
	@GetMapping("/create")
	public String createMateriaView(MateriaDTO nuevaMateria, Model model) {
		model.addAttribute("titleForm", "Crear materia");
		model.addAttribute("action", "create");
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		return "materia";
	}
	
	@GetMapping("/edit/{codigo}")
	public String editMateriaView(@PathVariable String codigo, Model model) {
		Materia materia = CollectionMateria.getMateria(codigo);
        MateriaDTO materiaDTO = materiaMapper.toMateriaDTO(materia);
		model.addAttribute("titleForm", "Editar materia");
		model.addAttribute("materia", materiaDTO);
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("action", "edit");
		return "materia";
	}
	
	
	@PostMapping("/create")
	public String createMateria(@ModelAttribute MateriaDTO nuevaMateriaDTO, Model model) {
		Materia nuevaMateria = materiaMapper.toMateria(nuevaMateriaDTO);
		Docente docente = CollectionDocente.getDocente(nuevaMateria.getDocente().getLegajo());
		Carrera carrera = CollectionCarrera.getCarrera(nuevaMateria.getCarrera().getCodigo());
		nuevaMateria.setDocente(docente);
		nuevaMateria.setCarrera(carrera);
		boolean response = CollectionMateria.saveMateria(nuevaMateria);
		String msg;
		if (response) {
			msg = "Materia agregada con éxito!";
		} else {
			msg = "¡Ocurrió un problema! :(";
		}
		model.addAttribute("response", response);
		model.addAttribute("msg", msg);
		model.addAttribute("materias", materiaMapper.toMateriaDTOs(CollectionMateria.getMaterias()));
		return "materias";
	}
	
	@PostMapping("/edit")
	public String editMateria(@ModelAttribute MateriaDTO materiaDTO, Model model) {
		Materia materia = materiaMapper.toMateria(materiaDTO);
		Docente docente = CollectionDocente.getDocente(materia.getDocente().getLegajo());
		Carrera carrera = CollectionCarrera.getCarrera(materia.getCarrera().getCodigo());
		materia.setDocente(docente);
		materia.setCarrera(carrera);
		boolean response = false;
		String msg = "";
		try {
			CollectionMateria.editMateria(materia);
			msg = "La materia con código "+materia.getCodigo()+" fue modificada con éxito";
			response = true;
		} catch(Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("msg", msg);
		model.addAttribute("response", response);
		model.addAttribute("materias", CollectionMateria.getMaterias());
		return "materias";
	}
	
	@GetMapping("/delete/{codigo}")
	public String deleteMateria(@PathVariable String codigo) {
		CollectionMateria.deleteMateria(codigo);
		return "redirect:/materias";
	}
	
}
