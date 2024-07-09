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

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.services.imp.CarreraServiceImp;
import ar.edu.unju.fi.services.imp.DocenteServiceImpl;
import ar.edu.unju.fi.services.imp.MateriaServiceImp;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/materias")
public class MateriaController {
	@Autowired
    private MateriaServiceImp materiaService;
 
	@Autowired
	private CarreraServiceImp carreraService;
	
	@Autowired
	private DocenteServiceImpl docenteService;
	
	@GetMapping
	public String getMateriasView(Model model) {
		model.addAttribute("title", "Materias");
		model.addAttribute("materias", materiaService.getMaterias());
		model.addAttribute("response", false);
		model.addAttribute("msg", "");
		return "materias";
	}
	
	@GetMapping("/create")
	public String createMateriaView(Model model) {
	    MateriaDTO materiaDTO = new MateriaDTO();
	    model.addAttribute("titleForm", "Crear materia");
	    model.addAttribute("action", "create");
	    model.addAttribute("materia", materiaDTO);
	    model.addAttribute("docentes", docenteService.getDocentes());
	    model.addAttribute("carreras", carreraService.getCarreras());
	    return "materia";
	}
	
	@PostMapping("/edit")
	public String editMateria(@Valid @ModelAttribute("materia") MateriaDTO materiaDTO,
	                          BindingResult bindingResult,
	                          Model model) {
	    if (bindingResult.hasErrors()) {
	        // Si hay errores de validación, volver a la vista "materia" para mostrar los errores
	        model.addAttribute("action", "edit");
	        model.addAttribute("titleForm", "Editar Materia");
	        model.addAttribute("docentes", docenteService.getDocentes());
	        model.addAttribute("carreras", carreraService.getCarreras());
	        return "materia";
	    }

	    boolean response = false;
	    String msg = "";
	    try {
	        materiaService.editMateria(materiaDTO);
	        msg = "La materia con código fue modificada con éxito";
	        response = true;
	    } catch (Exception e) {
	        msg = e.getMessage();
	        e.printStackTrace();
	    }
	    model.addAttribute("msg", msg);
	    model.addAttribute("response", response);
	    model.addAttribute("materias", materiaService.getMaterias());
	    return "redirect:/materias";
	}


	@GetMapping("/edit/{codigo}")
	public String editMateriaView(@PathVariable Integer codigo, Model model) {
	    MateriaDTO materiaEncontradaDTO = materiaService.getMateriaById(codigo);
	    model.addAttribute("action", "edit");
	    model.addAttribute("titleForm", "Editar Materia");
	    model.addAttribute("materia", materiaEncontradaDTO);
	    model.addAttribute("docentes", docenteService.getDocentes());
	    model.addAttribute("carreras", carreraService.getCarreras());
	    System.out.println("GET EDIT: " + docenteService.getDocentes());
	    System.out.println("GET EDIT: " + carreraService.getCarreras());
	    return "materia";
	}

	@PostMapping("/create")
	public String createMateria(@Valid @ModelAttribute("materia") MateriaDTO materiaDTO,
	                            BindingResult bindingResult,
	                            Model model) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("titleForm", "Crear materia");
	        model.addAttribute("action", "create");
	        model.addAttribute("docentes", docenteService.getDocentes());
	        model.addAttribute("carreras", carreraService.getCarreras());
	        return "materia";
	    }

	    boolean response = materiaService.saveMateria(materiaDTO);
	    String msg = response ? "¡Materia agregada con éxito!" : "¡Ocurrió un problema! :(";
	    model.addAttribute("response", response);
	    model.addAttribute("msg", msg);
	    model.addAttribute("materias", materiaService.getMaterias());
	    return "redirect:/materias";
	}

	@GetMapping("/delete/{codigo}")
	public String deleteMateria(@PathVariable Integer codigo) {
	    materiaService.deleteMateria(codigo);
	    return "redirect:/materias";
	}

	
}
