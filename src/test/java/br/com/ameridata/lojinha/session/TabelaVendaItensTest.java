package br.com.ameridata.lojinha.session;

import br.com.ameridata.lojinha.model.Produto;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TabelaVendaItensTest {

    private TabelaVendaItens tabelaVendaItens;

    @Before
    public void SetUp() {
        this.tabelaVendaItens = new TabelaVendaItens();
    }

    @Test
    public void deveCalcularValorTotalSemItens() throws Exception {
        assertEquals(BigDecimal.ZERO, tabelaVendaItens.getValorTotal());
    }

    @Test
    public void deveCalcularValorTotalComUmItem() throws Exception {
        Produto produto = new Produto();
        BigDecimal valor = new BigDecimal(8.9);
        produto.setPrecoVenda(valor);

        tabelaVendaItens.adicionarItem(produto, 1);

        assertEquals(valor, tabelaVendaItens.getValorTotal());
    }

    @Test
    public void deveCalcularValorTotalComVariosItens() {
        Produto p1 = new Produto();
        p1.setId(1L);
        BigDecimal v1 = new BigDecimal(10);
        p1.setPrecoVenda(v1);

        Produto p2 = new Produto();
        p2.setId(2L);
        BigDecimal v2 = new BigDecimal(5);
        p2.setPrecoVenda(v2);

        tabelaVendaItens.adicionarItem(p1, 1);
        tabelaVendaItens.adicionarItem(p2, 2);

        assertEquals(v1.add(v2.multiply(new BigDecimal(2))), tabelaVendaItens.getValorTotal());
    }

    @Test
    public void deveManterTamanhoListaParaMesmosProdutos() throws Exception {
        Produto p1 = new Produto();
        p1.setId(1L);
        p1.setPrecoVenda(new BigDecimal("4.5"));

        tabelaVendaItens.adicionarItem(p1, 1);
        tabelaVendaItens.adicionarItem(p1, 1);

        assertEquals(1, tabelaVendaItens.getItens().size());
        assertEquals(new BigDecimal("9.0"), tabelaVendaItens.getValorTotal());
    }

    @Test
    public void deveAtualizarTamanhoListaParaProdutosDiferentes() throws Exception {
        Produto p1 = new Produto();
        p1.setId(1L);
        p1.setPrecoVenda(new BigDecimal("4.5"));

        Produto p2 = new Produto();
        p2.setId(2L);
        p2.setPrecoVenda(new BigDecimal("6.5"));

        tabelaVendaItens.adicionarItem(p1, 1);
        tabelaVendaItens.adicionarItem(p2, 1);

        assertEquals(2, tabelaVendaItens.getItens().size());
    }

}
