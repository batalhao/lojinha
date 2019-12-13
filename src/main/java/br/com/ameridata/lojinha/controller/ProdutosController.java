package br.com.ameridata.lojinha.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.repository.Produtos;

@Controller
public class ProdutosController {

	private static final Logger logger = LoggerFactory.getLogger(ProdutosController.class);

//	private ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//	Produto p = context.getBean(Produto.class);

	@Autowired
	private Produtos produtos;

	@RequestMapping(value = "/produtos/novo", method = RequestMethod.GET)
	public String novo(Produto produto /* Model model */) {
		logger.info("Novo produto chamado"); // será retirado. Apenas para testes
//		model.addAttribute(new Produto());
		Optional<Produto> produtoOptional = produtos.findBySku("AA111"); // Apagar
		produtoOptional = produtos.findBySkuIgnoreCase("AA111"); // Apagar
		System.out.println(produtoOptional.isPresent());
		return "produto/CadastroProduto";
	}

	@RequestMapping(value = "/produtos/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Produto produto, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
//			model.addAttribute("mensagem", "Erro no formulário");
//			System.out.println(">>> Tem erro!");

			return novo(produto);

			// Tratamento que será bustituido pelo tratamento acima
//			model.addAttribute(produto);
//			return "produto/CadastroProduto"; /* .html */
		}

		// Salvar banco de dados...

		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");

		System.out.println(">>> Sku: " + produto.getSku());
		System.out.println(">>> Nome: " + produto.getNome());
		System.out.println(">>> Descrição: " + produto.getDescricao());
		return "redirect:/produtos/novo"; /* GET */
	}

//	@RequestMapping(value = "produtos/cadastro")
//	public String cadastro() {
//		return "produto/cadastro-produto";
//	}

}