package br.com.ameridata.lojinha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.repository.Produtos;

@Service
public class CadastroProdutoService {

	@Autowired
	private Produtos produtos;

	@Transactional
	public void salvar(Produto produto) {
		produtos.save(produto);
	}

}
