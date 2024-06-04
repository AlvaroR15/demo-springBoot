package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("title","Facultad de Ciencias Agrarias");
		return "index";
	}
	
	
}
