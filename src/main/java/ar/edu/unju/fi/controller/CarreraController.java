package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.services.ICarreraService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    ICarreraService carreraService;

    @GetMapping
    public String getCarrerasView(Model model) {
        model.addAttribute("carreras", carreraService.getCarreras());
        model.addAttribute("title", "Carreras");
        model.addAttribute("msg", "");
        model.addAttribute("response", false); 
        return "carreras";
    }

    @GetMapping("/create")
    public String createCarreraView(Model model) {
        CarreraDTO carreraDTO = new CarreraDTO();
        model.addAttribute("titleForm", "Nueva Carrera");
        model.addAttribute("action", "create");
        model.addAttribute("carrera", carreraDTO);
        model.addAttribute("msg", "");
        model.addAttribute("response", false); 
        return "carrera";
    }

    @PostMapping("/create")
    public String createCarrera(@Valid @ModelAttribute("carrera") CarreraDTO carreraDTO,
                                BindingResult result,
                                ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("titleForm", "Nueva Carrera");
            model.addAttribute("action", "create");
            model.addAttribute("carrera", carreraDTO);
            model.addAttribute("msg", "Hay errores en el formulario");
            model.addAttribute("response", false); 
            return "carrera";
        }

        try {
            carreraDTO.setEstado("Activa");
            boolean response = carreraService.saveCarrera(carreraDTO);
            String msg = response ? "¡Carrera agregada con éxito!" : "¡Ocurrió un problema! :(";
            model.addAttribute("msg", msg);
            model.addAttribute("response", response); 
            model.addAttribute("carreras", carreraService.getCarreras());
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("response", false); 
            model.addAttribute("carrera", carreraDTO);
            model.addAttribute("carreras", carreraService.getCarreras());
        }
        return "carreras";
    }

    @GetMapping("/delete/{codigo}")
    public String deleteCarrera(@PathVariable Integer codigo) {
        carreraService.deleteCarrera(codigo);
        return "redirect:/carreras";
    }

    @GetMapping("/edit/{id}")
    public String editCarreraView(@PathVariable Integer id, Model model) {
        CarreraDTO carreraEncontradaDTO = carreraService.getCarreraById(id);
        model.addAttribute("titleForm", "Editar Carrera");
        model.addAttribute("action", "edit");
        model.addAttribute("carrera", carreraEncontradaDTO);
        model.addAttribute("msg", "");
        model.addAttribute("response", false);
        return "carrera";
    }

    @PostMapping("/edit")
    public String editCarrera(@Valid @ModelAttribute("carrera") CarreraDTO carreraDTO,
                              BindingResult result,
                              ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("titleForm", "Editar Carrera");
            model.addAttribute("action", "edit");
            model.addAttribute("carrera", carreraDTO);
            model.addAttribute("msg", "Hay errores en el formulario");
            model.addAttribute("response", false);
            return "carrera";
        }

        try {
            carreraService.editCarrera(carreraDTO);
            String msg = "¡Carrera editada con éxito!";
            model.addAttribute("msg", msg);
            model.addAttribute("response", true);
            model.addAttribute("carreras", carreraService.getCarreras());
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("response", false);
            model.addAttribute("carrera", carreraDTO);
            model.addAttribute("carreras", carreraService.getCarreras());
        }
        return "carreras";
    }
}