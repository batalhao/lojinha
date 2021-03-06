package br.com.ameridata.lojinha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.repository.helper.produto.ProdutosQueries;

@Repository
public interface Produtos extends JpaRepository<Produto, Long>, ProdutosQueries {

	public Optional<Produto> findBySku(String sku);

	public Optional<Produto> findBySkuIgnoreCase(String sku);

	public Optional<Produto> findByNome(String nome);

	public Optional<Produto> findByNomeIgnoreCase(String nome);

}
