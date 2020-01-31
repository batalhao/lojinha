package br.com.ameridata.lojinha.repository.helper.produto;

import br.com.ameridata.lojinha.dto.ProdutoDTO;
import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.repository.filter.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutosQueries {

    public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);

    public List<ProdutoDTO> porSkuOuNome(String skuOuNome);

}
