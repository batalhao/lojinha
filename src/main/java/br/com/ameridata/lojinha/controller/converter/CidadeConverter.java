package br.com.ameridata.lojinha.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.ameridata.lojinha.model.Cidade;

public class CidadeConverter implements Converter<String, Cidade> {

	@Override
	public Cidade convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			Cidade cidade = new Cidade();
			cidade.setId(Integer.parseInt(id));

			return cidade;
		} else {
			return null;
		}
	}

}