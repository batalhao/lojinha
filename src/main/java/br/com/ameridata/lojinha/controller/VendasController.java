package br.com.ameridata.lojinha.controller;

import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.repository.Produtos;
import br.com.ameridata.lojinha.session.TabelaVendaItens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value = "/vendas")
public class VendasController {

    @Autowired
    private Produtos produtos;

    @Autowired
    private TabelaVendaItens itens;

    @GetMapping("/novo")
    public String novo() {
        return "venda/CadastroVenda";
    }

    @PostMapping("/item")
    public ModelAndView adicionarItem(Long idProduto) {
        Optional<Produto> produto = produtos.findById(idProduto);
        if (produto.isPresent()) {
            itens.adicionarItem(produto.get(), 1);
        }

        ModelAndView modelAndView = new ModelAndView("venda/TabelaVendaItens");
        modelAndView.addObject("itens", itens.getItens());

        return modelAndView;
    }

}
