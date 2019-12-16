package br.com.ameridata.lojinha.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.ameridata.lojinha.model.Empresa;

public class EmpresaConverter implements Converter<String, Empresa> {

	@Override
	public Empresa convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			Empresa empresa = new Empresa();
			empresa.setId(Integer.parseInt(id));

			return empresa;
		} else {
			return null;
		}
	}

}
