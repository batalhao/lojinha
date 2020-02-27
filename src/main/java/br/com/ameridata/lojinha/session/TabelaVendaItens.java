package br.com.ameridata.lojinha.session;

import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.model.VendaItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SessionScope
@Component
public class TabelaVendaItens {

    private List<VendaItem> itens = new ArrayList<>();

    public BigDecimal getValorTotal() {
        return itens.stream()
                .map(VendaItem::getValorTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public void adicionarItem(Produto produto, Integer quantidade) {
        Optional<VendaItem> vendaItemOptional = buscarItemPorProduto(produto);

        VendaItem vendaItem;

        if (vendaItemOptional.isPresent()) {
            vendaItem = vendaItemOptional.get();
            vendaItem.setQuantidade(vendaItem.getQuantidade() + quantidade);
        } else {
            vendaItem = new VendaItem();

            vendaItem.setProduto(produto);
            vendaItem.setQuantidade(quantidade);
            vendaItem.setValorUnitario(produto.getPrecoVenda());

            itens.add(0, vendaItem);
        }
    }

    public void alterarQuantidadeItens(Produto produto, Integer quantidade) {
        VendaItem vendaItem = buscarItemPorProduto(produto).get();
        vendaItem.setQuantidade(quantidade);
    }

    public List<VendaItem> getItens() {
        return itens;
    }

    private Optional<VendaItem> buscarItemPorProduto(Produto produto) {
        return itens.stream()
                .filter(i -> i.getProduto().equals(produto))
                .findAny();
    }
}
