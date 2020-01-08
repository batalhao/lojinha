package br.com.ameridata.lojinha.model;

import br.com.ameridata.lojinha.model.validation.group.CnpjGroup;
import br.com.ameridata.lojinha.model.validation.group.CpfGroup;

public enum TipoPessoa implements Comparable<TipoPessoa> {

	F("Física", "CPF", "000.000.000-00", CpfGroup.class) {
		@Override
		public String formatarDocumento(String documento) {
			return documento.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	J("Jurídica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class) {
		@Override
		public String formatarDocumento(String documento) {
			return documento.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	};

	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> group;

	TipoPessoa(String descricao, String documento, String mascara, Class<?> group) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.group = group;
	}

	public abstract String formatarDocumento(String documento);

	public String getDescricao() {
		return this.descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}

	public Class<?> getGroup() {
		return group;
	}

}