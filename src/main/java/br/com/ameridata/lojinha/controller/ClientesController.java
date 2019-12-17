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

import br.com.ameridata.lojinha.model.Cliente;
import br.com.ameridata.lojinha.model.TipoPessoa;
import br.com.ameridata.lojinha.repository.Cidades;
import br.com.ameridata.lojinha.repository.Estados;
import br.com.ameridata.lojinha.service.CadastroClienteService;
import br.com.ameridata.lojinha.service.exception.ClienteDocumentoCadastradoException;
import br.com.ameridata.lojinha.service.exception.ClienteNomeCadastradoException;

@Controller
public class ClientesController {

	@Autowired
	private Estados estados;

	@Autowired
	private Cidades cidades;

	@Autowired
	private CadastroClienteService clienteService;

	@RequestMapping(value = "/clientes/novo", method = RequestMethod.GET)
	public ModelAndView novo(Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView("cliente/CadastroCliente");

		List<TipoPessoa> listaTiposPessoas = Arrays.asList(TipoPessoa.values());
		listaTiposPessoas.sort(Comparator.comparing(TipoPessoa::getDescricao));
		modelAndView.addObject("tiposPessoas", listaTiposPessoas);

		modelAndView.addObject("estados", estados.findAllOrderByNomeAsc());
		modelAndView.addObject("cidades", cidades.findAllOrderByNomeAsc());

		return modelAndView;
	}

	@RequestMapping(value = "/clientes/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cliente cliente, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}

		try {
			clienteService.salvar(cliente);
		} catch (ClienteNomeCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(cliente);
		} catch (ClienteDocumentoCadastradoException e) {
			result.rejectValue("documento", e.getMessage(), e.getMessage());
			return novo(cliente);
		}

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");

		return new ModelAndView("redirect:/clientes/novo");
	}

}
