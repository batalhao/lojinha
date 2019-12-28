package br.com.ameridata.lojinha.service.event.produto;

import org.springframework.util.StringUtils;

import br.com.ameridata.lojinha.model.Produto;

public class ProdutoSalvoEvent {

	private Produto produto;

	public ProdutoSalvoEvent(Produto produto) {
		super();
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public boolean isFoto() {
		return !StringUtils.isEmpty(produto.getFoto());
	}

}
