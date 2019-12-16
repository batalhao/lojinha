package br.com.ameridata.lojinha.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.ameridata.lojinha.model.Estado;

public class EstadoConverter implements Converter<String, Estado> {

	@Override
	public Estado convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			Estado estado = new Estado();
			estado.setId(Integer.parseInt(id));

			return estado;
		} else {
			return null;
		}
	}

}