package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.services.imp.AlumnoServiceImp;
import ar.edu.unju.fi.services.imp.MateriaServiceImp;

@Controller
@RequestMapping("/inscripcion")
public class InscripcionController {
    
    @Autowired
    private AlumnoServiceImp alumnoService;
    
    @Autowired
    private MateriaServiceImp materiaService;
    
    @Autowired
    private AlumnoMapper alumnoMapper; // Inyectar el mapper correspondiente
    
    @Autowired
    private MateriaMapper materiaMapper;
    
    @GetMapping("/form")
    public String inscripcionForm(Model model) {
        model.addAttribute("alumnos", alumnoService.findAll());
        model.addAttribute("materias", materiaService.findAll());
        return "inscripcion"; // Nombre del HTML creado para inscripción
    }

    @PostMapping("/inscribir")
    public String inscribirAlumnoEnMateria(@RequestParam("alumnoId") String alumnoId,
                                           @RequestParam("materiaId") Long materiaId,
                                           Model model) {
        try {
        	AlumnoDTO alumnoDTO = alumnoService.findById(alumnoId);
            MateriaDTO materiaDTO = materiaService.findById(materiaId);

            Alumno alumno = alumnoMapper.toAlumno(alumnoDTO); // Convertir DTO a entidad Alumno
            Materia materia = materiaMapper.toMateria(materiaDTO); // Convertir DTO a entidad Materia
            
            if (alumno.getMaterias().contains(materia)) {
                throw new RuntimeException("El alumno ya está inscrito en esta materia.");
            }

            alumno.getMaterias().add(materia); // Agregar materia al alumno

            model.addAttribute("successMessage", "Inscripción exitosa");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al realizar la inscripción: " + e.getMessage());
        }

        return "redirect:/materias"; // Redirige a la página de materias o donde corresponda
    }
}
