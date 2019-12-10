package br.com.ameridata.lojinha.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Cidade;

@Controller
public class CidadeController {

	@RequestMapping(value = "/cidades/novo", method = RequestMethod.GET)
	public String novo(Cidade cidade) {
		return "cidade/CadastroCidade";
	}

	@RequestMapping(value = "/cidades/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cidade cidade, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cidade);
		}

		// Salvar banco de dados...

		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");

		System.out.println(">>> Nome: " + cidade.getNome());
		return "redirect:/cidades/novo";
	}

}