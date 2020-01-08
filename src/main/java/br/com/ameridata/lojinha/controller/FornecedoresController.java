package br.com.ameridata.lojinha.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Fornecedor;
import br.com.ameridata.lojinha.model.Status;
import br.com.ameridata.lojinha.model.TipoPessoa;
import br.com.ameridata.lojinha.repository.Cidades;
import br.com.ameridata.lojinha.repository.Estados;
import br.com.ameridata.lojinha.service.CadastroFornecedorService;
import br.com.ameridata.lojinha.service.exception.FornecedorDocumentoCadastradoException;

@Controller
@RequestMapping(value = "/fornecedores")
public class FornecedoresController {

	@Autowired
	private Estados estados;

	@Autowired
	private Cidades cidades;

	@Autowired
	private CadastroFornecedorService fornecedorService;

//	@RequestMapping(value = "/fornecedores/novo", method = RequestMethod.GET)
	@GetMapping(value = "/novo")
	public ModelAndView novo(Fornecedor fornecedor) {
		ModelAndView modelAndView = new ModelAndView("fornecedor/CadastroFornecedor");

		List<TipoPessoa> listaTiposPessoas = Arrays.asList(TipoPessoa.values());
		listaTiposPessoas.sort(Comparator.comparing(TipoPessoa::getDescricao));
		modelAndView.addObject("tiposPessoa", listaTiposPessoas);

		modelAndView.addObject("status", Status.values());
		modelAndView.addObject("estados", estados.findAllByOrderByNomeAsc());
		modelAndView.addObject("cidades", cidades.findAllByOrderByNomeAsc());

		return modelAndView;
	}

//	@RequestMapping(value = "/fornecedores/novo", method = RequestMethod.POST)
	@PostMapping(value = "/novo")
	public ModelAndView cadastrar(@Valid Fornecedor fornecedor, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(fornecedor);
		}

		try {
			fornecedorService.salvar(fornecedor);
		} catch (FornecedorDocumentoCadastradoException e) {
			result.rejectValue("documento", e.getMessage(), e.getMessage());
			return novo(fornecedor);
		}

		attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");

		return new ModelAndView("redirect:/fornecedores/novo");
	}

//	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Fornecedor fornecedor, BindingResult result) {
		List<String> listaErros = new ArrayList<>();
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();

			for (FieldError error : errors) {
				listaErros.add(error.getDefaultMessage());
			}

			return ResponseEntity.badRequest().body(listaErros);
		} else {

			try {
				fornecedor = fornecedorService.salvar(fornecedor);
			} catch (FornecedorDocumentoCadastradoException e) {
				listaErros.add(e.getMessage());
				return ResponseEntity.badRequest().body(listaErros);
			}

			return ResponseEntity.ok(fornecedor);
		}

	}

}
