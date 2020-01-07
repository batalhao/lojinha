package br.com.ameridata.lojinha.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.com.ameridata.lojinha.model.Pessoa;

public class PessoaGroupSequenceProvider implements DefaultGroupSequenceProvider<Pessoa> {

	@Override
	public List<Class<?>> getValidationGroups(Pessoa pessoa) {
		List<Class<?>> grupos = new ArrayList<>();

		grupos.add(Pessoa.class);

		if (isPessoaSelecionada(pessoa)) {
			grupos.add(pessoa.getTipoPessoa().getGroup());
		}

		return grupos;
	}

	private boolean isPessoaSelecionada(Pessoa pessoa) {
		return pessoa != null && pessoa.getTipoPessoa() != null;
	}

}
