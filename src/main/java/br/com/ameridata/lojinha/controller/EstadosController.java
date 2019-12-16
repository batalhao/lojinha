package br.com.ameridata.lojinha.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Estado;

@Controller
public class EstadosController {

	@RequestMapping(value = "/estados/novo", method = RequestMethod.GET)
	public String novo(Estado estado) {
		return "estado/CadastroEstado";
	}

	@RequestMapping(value = "/estados/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Estado estado, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(estado);
		}

		// Salvar banco de dados...

		attributes.addFlashAttribute("mensagem", "Estado salvo com sucesso!");

		System.out.println(">>> Nome: " + estado.getNome());
		return "redirect:/estados/novo";
	}

}
