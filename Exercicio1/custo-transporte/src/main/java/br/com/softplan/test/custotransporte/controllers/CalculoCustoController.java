package br.com.softplan.test.custotransporte.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.softplan.test.custotransporte.exceptions.ServiceException;
import br.com.softplan.test.custotransporte.models.CalculoCusto;
import br.com.softplan.test.custotransporte.models.domains.TipoVeiculoEnum;
import br.com.softplan.test.custotransporte.services.CalculoService;

@Controller
@RequestMapping("/calculos")
public class CalculoCustoController {
	
	@Autowired
	private CalculoService calculoCustoService;
	
	@PostMapping
	public String calcular(@Valid @ModelAttribute("calculoCusto") CalculoCusto calculoCusto,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			List<TipoVeiculoEnum> veiculos = Arrays.asList(TipoVeiculoEnum.values());
			model.addAttribute("veiculos", veiculos);
			return "home/index";
		}
		
		try {
			this.calculoCustoService.calculaValorTotal(calculoCusto);
		} catch (ServiceException se) {
			model.addAttribute("message", se.getMessage());
			return "home/index";
		}
		
		model.addAttribute("resultado", calculoCusto);
		return "calculo/resultado";
	}

}