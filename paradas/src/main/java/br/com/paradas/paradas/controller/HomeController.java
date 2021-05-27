package br.com.paradas.paradas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.paradas.paradas.model.Cliente;
import br.com.paradas.paradas.model.Status;
import br.com.paradas.paradas.repository.ClienteRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ClienteRepository repository;
	
	@GetMapping(value = {"home","/"})
	public String getHome(Model model) {
		List<Cliente> clientes = (List<Cliente>) repository.findAll();
		model.addAttribute("lista", clientes);
		return "home";
	}
	
	@GetMapping("/{status}")
	public String tipoCliente(@PathVariable("status") String status, Model model) {
		List<Cliente> clientes = repository.findByStatus(Status.valueOf(status.toUpperCase()));
		model.addAttribute("lista", clientes);
		return "home";
	}
	
	@ExceptionHandler(IllegalAccessException.class)
	public String onError() {
		return "redirect:/home";
	}

}
