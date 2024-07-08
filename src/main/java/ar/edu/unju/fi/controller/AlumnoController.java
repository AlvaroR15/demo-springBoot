package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.services.IAlumnoService;
import ar.edu.unju.fi.services.ICarreraService;
import ar.edu.unju.fi.services.IMateriaService;
import ar.edu.unju.fi.services.imp.CarreraServiceImp;
import jakarta.validation.Valid;
import ar.edu.unju.fi.model.Alumno;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

	@Autowired
	private AlumnoDTO alumnoDTO;
	
	@Autowired
	private IAlumnoService alumnoService;
	
	@Autowired
	private ICarreraService carreraService;
	
	@Autowired
	private IMateriaService materiaService;
	
	@Autowired
	private AlumnoMapper alumnoMapper;
	
	
	@GetMapping
	public String listAlumnosView(Model model) {
		model.addAttribute("alumnos", alumnoService.getAlumnos());
		model.addAttribute("title", "alumnos");
		model.addAttribute("response", false);
		model.addAttribute("msg", "");
		return "alumnos";
	}
	

	@GetMapping("/create")
	public String createAlumnoView(Model model) {
		AlumnoDTO alumnoDTO = new AlumnoDTO();
		model.addAttribute("action", "create");
		model.addAttribute("titleForm", "Nuevo Alumno");
		model.addAttribute("alumno", alumnoDTO);
		return "alumno";
	}
	
	@GetMapping("/edit/{id}")
	public String editAlumnoView(@PathVariable Integer id, Model model) {
	    AlumnoDTO alumnoEncontradoDTO = alumnoService.getAlumnoById(id);
	    model.addAttribute("action", "edit");
	    model.addAttribute("titleForm", "Editar Alumno");
	    model.addAttribute("alumno", alumnoEncontradoDTO);
	    return "alumno";
	}
	
	
	@PostMapping("/create")
	public String createAlumno(@ModelAttribute AlumnoDTO alumnoDTO, Model model) {
		boolean response = alumnoService.saveAlumno(alumnoDTO);
		String msg = response ? "¡Alumno agregado con éxito!" : "¡Ocurrió un problema! :(";
		model.addAttribute("response", response);
		model.addAttribute("msg", msg);
		model.addAttribute("alumnos", alumnoService.getAlumnos());
		return "alumnos";
	}
	
	@PostMapping("/edit")
	public String editAlumno(@Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO,
	                         BindingResult bindingResult,
	                         Model model) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("action", "edit");
	        model.addAttribute("titleForm", "Editar Alumno");
	        return "alumno";
	    }

	    boolean response = false;
	    String msg = "";
	    try {
	        alumnoService.editAlumno(alumnoDTO);
	        msg = "El alumno con DNI " + alumnoDTO.getDni() + " fue modificado con éxito";
	        response = true;
	    } catch (Exception e) {
	        msg = e.getMessage();
	        e.printStackTrace();
	    }
	    model.addAttribute("msg", msg);
	    model.addAttribute("response", response);
	    model.addAttribute("alumnos", alumnoService.getAlumnos());
	    return "alumnos";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAlumno(@PathVariable Integer id) {
		alumnoService.deleteAlumno(id);
		return "redirect:/alumnos";
	}
	
	
	/**
	 * Vista para el formulario de inscribir alumno en materia
	 * @param model
	 * @return
	 */
	@GetMapping("/inscribir")
	public String inscripcionAlumnoView(Model model) {
	    model.addAttribute("alumnos", alumnoService.getAlumnos());
	    model.addAttribute("materias", materiaService.getMaterias());
	    return "inscribirAlumnoMateria";
	}

	@PostMapping("/inscribir")
	public String inscribirAlumno(@RequestParam("alumnoId") Integer alumnoId,
	                              @RequestParam("materiaId") Integer materiaId,
	                              Model model) {
	    try {
	        System.out.println("Inscribiendo alumno en materia");
	        alumnoService.inscribirAlumnoEnMateria(alumnoId, materiaId);
	        model.addAttribute("msg", "¡Inscripción realizada con éxito!");
	        model.addAttribute("response", true);
	    } catch (Exception e) {
	        model.addAttribute("msg", e.getMessage());
	        model.addAttribute("response", false);
	    }
	    model.addAttribute("alumnos", alumnoService.getAlumnos());
	    model.addAttribute("materias", materiaService.getMaterias());
	    return "alumnos";
	}

	
	
	
	/**
	 * Consultar alumnos por carrera
	 * @param carreraId
	 * @param model
	 * @return
	 */
    @GetMapping("/consultar-por-carrera")
    public String consultaPorCarreraView(Model model) {
        List<CarreraDTO> carreras = carreraService.getCarreras();
        model.addAttribute("carreras", carreras);
        return "consultarAlumnosCarrera";
    }

    @PostMapping("/consultar-por-carrera/resultados")
    public String getAlumnosPorCarrera(@ModelAttribute CarreraDTO carreraDTO, Model model) {
        List<AlumnoDTO> alumnos = alumnoService.getAlumnosPorCarrera(carreraDTO.getId());
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("carreras", carreraService.getCarreras());
        System.out.println("ALUMNOS ENCONTRADOS::::: "+ alumnos);
        return "consultarAlumnosCarrera";
    }



	
    @GetMapping("/consultar-por-materia/{materiaId}")
    public String getAlumnosPorMateria(@PathVariable Integer materiaId, Model model) {
        MateriaDTO materia = materiaService.getMateriaById(materiaId);
        List<Alumno> alumnos = alumnoService.getAlumnosPorMateria(materiaId);
        List<AlumnoDTO> alumnosDTO = alumnoMapper.toAlumnoDTOs(alumnos);
        model.addAttribute("materia", materia);
        model.addAttribute("alumnos", alumnosDTO);
        return "consultarAlumnosMateria";
    }

    @PostMapping("/consultar-por-materia/resultados")
    public String consultarAlumnosPorMateria(@ModelAttribute("materiaId") Integer materiaId, Model model) {
        MateriaDTO materia = materiaService.getMateriaById(materiaId);
        List<Alumno> alumnos = alumnoService.getAlumnosPorMateria(materiaId);
        List<AlumnoDTO> alumnosDTO = alumnoMapper.toAlumnoDTOs(alumnos);
        model.addAttribute("materia", materia);
        model.addAttribute("alumnos", alumnosDTO);
        return "consultarAlumnosMateria";
    }

    @GetMapping("/consultar-por-materia")
    public String consultaPorMateriaView(Model model) {
        List<MateriaDTO> materias = materiaService.getMaterias();
        model.addAttribute("materias", materias);
        return "consultarAlumnosMateria";
    }

}
