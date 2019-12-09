package br.com.ameridata.lojinha.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Cliente;

@Controller
public class ClientesController {

	@RequestMapping(value = "/clientes/novo", method = RequestMethod.GET)
	public String novo(Cliente cliente) {
		return "cliente/CadastroCliente";
	}

	@RequestMapping(value = "/clientes/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
 
		// Salvar banco de dados...

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");

		System.out.println(">>> Nome: " + cliente.getNome());
		return "redirect:/cliente/novo";
	}

}
