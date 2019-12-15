package br.com.ameridata.lojinha.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.ameridata.lojinha.model.Fornecedor;

public class FornecedorConverter implements Converter<String, Fornecedor> {

	@Override
	public Fornecedor convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setId(Long.valueOf(id));

			return fornecedor;
		} else {
			return null;
		}
	}

}
