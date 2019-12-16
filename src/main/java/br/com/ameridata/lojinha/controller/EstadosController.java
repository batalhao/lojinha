package br.com.ameridata.lojinha.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Estado;
import br.com.ameridata.lojinha.service.CadastroEstadoService;
import br.com.ameridata.lojinha.service.exception.EstadoNomeCadastradoException;
import br.com.ameridata.lojinha.service.exception.EstadoUfCadastradoException;

@Controller
public class EstadosController {

	@Autowired
	private CadastroEstadoService estadoService;

	@RequestMapping(value = "/estados/novo", method = RequestMethod.GET)
	public ModelAndView novo(Estado estado) {
		return new ModelAndView("estado/CadastroEstado");
	}

	@RequestMapping(value = "/estados/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estado estado, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(estado);
		}

		try {
			estadoService.salvar(estado);
		} catch (EstadoUfCadastradoException e) {
			result.rejectValue("uf", e.getMessage(), e.getMessage());
			return novo(estado);
		} catch (EstadoNomeCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estado);
		}

		attributes.addFlashAttribute("mensagem", "Estado salvo com sucesso!");

		return new ModelAndView("redirect:/estados/novo");
	}

}
