package br.com.ameridata.lojinha.controller;

import br.com.ameridata.lojinha.controller.page.PageWrapper;
import br.com.ameridata.lojinha.dto.ProdutoDTO;
import br.com.ameridata.lojinha.model.Fornecedor;
import br.com.ameridata.lojinha.model.Origem;
import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.model.Unidade;
import br.com.ameridata.lojinha.repository.Fabricantes;
import br.com.ameridata.lojinha.repository.Fornecedores;
import br.com.ameridata.lojinha.repository.Produtos;
import br.com.ameridata.lojinha.repository.filter.ProdutoFilter;
import br.com.ameridata.lojinha.service.CadastroProdutoService;
import br.com.ameridata.lojinha.service.exception.ProdutoNomeCadastradoException;
import br.com.ameridata.lojinha.service.exception.ProdutoSkuCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value = "/produtos")
public class ProdutosController {

    @Autowired
    private Fabricantes fabricantes;

    @Autowired
    private Fornecedores fornecedores;

    @Autowired
    private CadastroProdutoService produtoService;

    @Autowired
    private Produtos produtos;

    //	@RequestMapping(value = "/produtos/novo", method = RequestMethod.GET)
    @GetMapping(value = "/novo")
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

    //	@RequestMapping(value = "/produtos/novo", method = RequestMethod.POST)
    @PostMapping(value = "/novo")
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

    @GetMapping
    public ModelAndView pesquisar(ProdutoFilter produtoFilter, BindingResult result,
                                  @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("produto/PesquisaProdutos");

        modelAndView.addObject("origens", Origem.values());

        modelAndView.addObject("fabricantes", fabricantes.findByAtivoTrueOrderByNomeAsc());

        List<Fornecedor> listaFornecedores = fornecedores.findAll();
        listaFornecedores.sort(Comparator.comparing(Fornecedor::getNome));
        modelAndView.addObject("fornecedores", listaFornecedores.stream().filter(f -> f.isAtivo()).toArray());

        List<Unidade> listaUnidades = Arrays.asList(Unidade.values());
        listaUnidades.sort(Comparator.comparing(Unidade::getDescricao));
        modelAndView.addObject("unidades", listaUnidades);

        PageWrapper<Produto> pageWrapper = new PageWrapper<>(produtos.filtrar(produtoFilter, pageable),
                httpServletRequest);

        modelAndView.addObject("pagina", pageWrapper);

        return modelAndView;
    }

    @GetMapping("/pesquisa")
    public @ResponseBody
    List<ProdutoDTO> pesquisar(String skuOuNome) {
        List<ProdutoDTO> produtoDTOList = produtos.porSkuOuNome(skuOuNome);
        return produtoDTOList;
    }

}