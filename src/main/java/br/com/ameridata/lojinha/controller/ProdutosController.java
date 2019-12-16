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

import br.com.ameridata.lojinha.model.Fornecedor;
import br.com.ameridata.lojinha.model.Origem;
import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.model.Unidade;
import br.com.ameridata.lojinha.repository.Fabricantes;
import br.com.ameridata.lojinha.repository.Fornecedores;
import br.com.ameridata.lojinha.service.CadastroProdutoService;
import br.com.ameridata.lojinha.service.exception.ProdutoNomeCadastradoException;
import br.com.ameridata.lojinha.service.exception.ProdutoSkuCadastradoException;

@Controller
public class ProdutosController {

	@Autowired
	private Fabricantes fabricantes;

	@Autowired
	private Fornecedores fornecedores;

	@Autowired
	private CadastroProdutoService produtoService;

	@RequestMapping(value = "/produtos/novo", method = RequestMethod.GET)
	public ModelAndView novo(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produto/CadastroProduto");

		modelAndView.addObject("origens", Origem.values());

		modelAndView.addObject("fabricantes", fabricantes.findByAtivoTrueOrderByNomeAsc());

		List<Fornecedor> listaFornecedores = fornecedores.findAll();
		listaFornecedores.sort(Comparator.comparing(Fornecedor::getNome));
		modelAndView.addObject("fornecedores", listaFornecedores.stream().filter(f -> f.isAtivo()).toArray());

		List<Unidade> listaUnidades = Arrays.asList(Unidade.values());
		listaUnidades.sort(Comparator.comparing(Unidade::getDescricao));
		modelAndView.addObject("unidades", listaUnidades);

		return modelAndView;
	}

	@RequestMapping(value = "/produtos/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Produto produto, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(produto);
		}

		try {
			produtoService.salvar(produto);
		} catch (ProdutoNomeCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(produto);
		} catch (ProdutoSkuCadastradoException e) {
			result.rejectValue("sku", e.getMessage(), e.getMessage());
			return novo(produto);
		}

		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");

		return new ModelAndView("redirect:/produtos/novo");
	}

}