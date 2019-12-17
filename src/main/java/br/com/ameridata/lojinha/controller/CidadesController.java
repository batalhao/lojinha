package br.com.ameridata.lojinha.controller;

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

import br.com.ameridata.lojinha.model.Cidade;
import br.com.ameridata.lojinha.model.Estado;
import br.com.ameridata.lojinha.repository.Estados;
import br.com.ameridata.lojinha.service.CadastroCidadeService;
import br.com.ameridata.lojinha.service.exception.CidadeNomeCadastradoException;

@Controller
public class CidadesController {

	@Autowired
	private Estados estados;

	@Autowired
	private CadastroCidadeService cidadeService;

	@RequestMapping(value = "/cidades/novo", method = RequestMethod.GET)
	public ModelAndView novo(Cidade cidade) {
		ModelAndView modelAndView = new ModelAndView("cidade/CadastroCidade");

		List<Estado> listaEstados = estados.findAll();
		listaEstados.sort(Comparator.comparing(Estado::getNome));
		modelAndView.addObject("estados", listaEstados);

		return modelAndView;
	}

	@RequestMapping(value = "/cidades/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cidade cidade, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cidade);
		}

		try {
			cidadeService.salvar(cidade);
		} catch (CidadeNomeCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(cidade);
		}

		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");

		return new ModelAndView("redirect:/cidades/novo");
	}

}