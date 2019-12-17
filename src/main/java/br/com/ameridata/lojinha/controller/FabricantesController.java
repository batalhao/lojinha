package br.com.ameridata.lojinha.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Fabricante;
import br.com.ameridata.lojinha.model.Status;
import br.com.ameridata.lojinha.model.TipoPessoa;
import br.com.ameridata.lojinha.repository.Cidades;
import br.com.ameridata.lojinha.repository.Estados;
import br.com.ameridata.lojinha.service.CadastroFabricanteService;
import br.com.ameridata.lojinha.service.exception.FabricanteDocumentoCadastradoException;

@Controller
public class FabricantesController {

	@Autowired
	private Estados estados;

	@Autowired
	private Cidades cidades;

	@Autowired
	private CadastroFabricanteService fabricanteService;

	@RequestMapping(value = "/fabricantes/novo", method = RequestMethod.GET)
	public ModelAndView novo(Fabricante fabricante) {
		ModelAndView modelAndView = new ModelAndView("fabricante/CadastroFabricante");

		List<TipoPessoa> listaTiposPessoas = Arrays.asList(TipoPessoa.values());
		listaTiposPessoas.sort(Comparator.comparing(TipoPessoa::getDescricao));
		modelAndView.addObject("tiposPessoa", listaTiposPessoas);

		modelAndView.addObject("status", Status.values());
		modelAndView.addObject("estados", estados.findAllByOrderByNomeAsc());
		modelAndView.addObject("cidades", cidades.findAllByOrderByNomeAsc());

		return modelAndView;
	}

	@RequestMapping(value = "/fabricantes/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Fabricante fabricante, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(fabricante);
		}

		try {
			fabricanteService.salvar(fabricante);
		} catch (FabricanteDocumentoCadastradoException e) {
			result.rejectValue("documento", e.getMessage(), e.getMessage());
			return novo(fabricante);
		}

		attributes.addFlashAttribute("mensagem", "Fabricante salvo com sucesso!");

		return new ModelAndView("redirect:/fabricantes/novo");
	}

}
