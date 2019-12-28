package br.com.ameridata.lojinha.repository.helper.produto;

import java.util.List;

import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.repository.filter.ProdutoFilter;

public interface ProdutosQueries {

	public List<Produto> filtrar(ProdutoFilter filtro);

}
