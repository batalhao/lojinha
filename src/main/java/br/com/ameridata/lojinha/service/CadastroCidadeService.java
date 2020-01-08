package br.com.ameridata.lojinha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ameridata.lojinha.model.Cidade;
import br.com.ameridata.lojinha.repository.Cidades;
import br.com.ameridata.lojinha.service.exception.CidadeNomeCadastradoException;

@Service
public class CadastroCidadeService {

	@Autowired
	private Cidades cidades;

	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> cidadeOptional = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if (cidadeOptional.isPresent()) {
			throw new CidadeNomeCadastradoException("Nome: Cidade já cadastrada.");
		}

		cidades.save(cidade);
	}

}
