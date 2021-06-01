package br.com.paradas.paradas.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.paradas.paradas.model.Cliente;
import br.com.paradas.paradas.repository.ClienteRepository;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@GetMapping("novo")
	public String getFormularioNovoCliente(Cliente cliente) {
		return "cliente/formulario";
	}
	
	@PostMapping("novo")
	public String cadastrarNovoCliente(@Valid Cliente cliente, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("PAssou aqui");
			return "cliente/formulario";
		}
		repository.save(cliente);
		return "redirect:/home";
	}
	
	@GetMapping("editar")
	public String editarCliente(@RequestParam(name = "id") Long id, Model model) {
		Optional<Cliente> optional = repository.findById(id);
		Cliente cliente = optional.get();
		model.addAttribute("cliente", cliente);
		return "cliente/formularioeditar";
	}
	
	@PostMapping("editar")
	public String editarCliente(Cliente paramCliente) {
		System.out.println(paramCliente.getId());
		Optional<Cliente> optional = repository.findById(paramCliente.getId());
		Cliente cliente = optional.get();
		cliente.setNome(paramCliente.getNome());
		cliente.setLogradouro(paramCliente.getLogradouro());
		cliente.setCep(paramCliente.getCep());
		cliente.setNumero(paramCliente.getNumero());
		repository.save(cliente);
		return "redirect:/home";
	}
	
	@GetMapping("deletar")
	public String deletarCliente(@RequestParam(name = "id") Long id) {
		repository.deleteById(id);
		return "redirect:/home";
	}
	
	

}
