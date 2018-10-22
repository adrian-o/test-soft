package br.com.softplan.test.custotransporte.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.softplan.test.custotransporte.models.CalculoCusto;
import br.com.softplan.test.custotransporte.models.domains.TipoVeiculoEnum;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String index(Model model) {
		List<TipoVeiculoEnum> veiculos = Arrays.asList(TipoVeiculoEnum.values());
		model.addAttribute("calculoCusto", new CalculoCusto());
		model.addAttribute("veiculos", veiculos);
		return "home/index";
	}
	
	@GetMapping("/denied")
	public String accessDenied(Model model) {
		return "home/403";
	}
}
