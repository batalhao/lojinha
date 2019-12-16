package br.com.ameridata.lojinha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ameridata.lojinha.model.Empresa;
import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.repository.Produtos;
import br.com.ameridata.lojinha.service.exception.ProdutoNomeCadastradoException;
import br.com.ameridata.lojinha.service.exception.ProdutoSkuCadastradoException;

@Service
public class CadastroProdutoService {

	@Autowired
	private Produtos produtos;

	@Transactional
	public void salvar(Produto produto) {
		// RETIRAR - PRB
		Empresa empresa = new Empresa();
		empresa.setId(1);
		produto.setEmpresa(empresa);
		// RETIRAR - PRB

		Optional<Produto> produtoOptional = produtos.findBySkuIgnoreCase(produto.getSku());
		if (produtoOptional.isPresent()) {
			throw new ProdutoSkuCadastradoException("SKU: Produto já cadastrado.");
		}

		produtoOptional = produtos.findByNomeIgnoreCase(produto.getNome());
		if (produtoOptional.isPresent()) {
			throw new ProdutoNomeCadastradoException("Nome: Produto já cadastrado.");
		}

		produto.setSku(produto.getSku().toUpperCase());

		produtos.save(produto);
	}

}
