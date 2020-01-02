package br.com.ameridata.lojinha.repository.helper.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.repository.filter.ProdutoFilter;

public interface ProdutosQueries {

	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);

}
