package br.com.ameridata.lojinha.service.event.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.ameridata.lojinha.storage.FotoStorage;

@Component
public class ProdutoListener {

	@Autowired
	private FotoStorage fotoStorage;

	@EventListener(condition = "#event.foto")
	public void produtoSalvo(ProdutoSalvoEvent event) {
		fotoStorage.save(event.getProduto().getFoto());
	}

}
