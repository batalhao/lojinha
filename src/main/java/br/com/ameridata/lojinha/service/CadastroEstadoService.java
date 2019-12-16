package br.com.ameridata.lojinha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ameridata.lojinha.model.Estado;
import br.com.ameridata.lojinha.repository.Estados;
import br.com.ameridata.lojinha.service.exception.EstadoNomeCadastradoException;
import br.com.ameridata.lojinha.service.exception.EstadoUfCadastradoException;

@Service
public class CadastroEstadoService {

	@Autowired
	private Estados estados;

	@Transactional
	public void salvar(Estado estado) {
		Optional<Estado> estadoOptional = estados.findByUfIgnoreCase(estado.getUf());

		if (estadoOptional.isPresent()) {
			throw new EstadoUfCadastradoException("UF: Estado já cadastrado.");
		}

		estadoOptional = estados.findByNomeIgnoreCase(estado.getNome());
		if (estadoOptional.isPresent()) {
			throw new EstadoNomeCadastradoException("Nome: Estado já cadastrado.");
		}

		estado.setUf(estado.getUf().toUpperCase());

		estados.save(estado);
	}

}
