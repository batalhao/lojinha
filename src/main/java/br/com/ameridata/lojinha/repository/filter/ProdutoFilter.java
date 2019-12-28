package br.com.ameridata.lojinha.repository.filter;

import java.math.BigDecimal;

import br.com.ameridata.lojinha.model.Fabricante;
import br.com.ameridata.lojinha.model.Fornecedor;
import br.com.ameridata.lojinha.model.Origem;
import br.com.ameridata.lojinha.model.Unidade;

public class ProdutoFilter {

	private String sku;
	private String gtin;
	private String nome;
	private Fabricante fabricante;
	private Fornecedor fornecedor;
	private Unidade unidade;
	private Origem origem;
	private BigDecimal custoDe;
	private BigDecimal custoAte;
	private BigDecimal precoDe;
	private BigDecimal precoAte;
	private BigDecimal comissaoDe;
	private BigDecimal comissaoAte;
	private Integer estoqueDe;
	private Integer estoqueAte;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public BigDecimal getCustoDe() {
		return custoDe;
	}

	public void setCustoDe(BigDecimal custoDe) {
		this.custoDe = custoDe;
	}

	public BigDecimal getCustoAte() {
		return custoAte;
	}

	public void setCustoAte(BigDecimal custoAte) {
		this.custoAte = custoAte;
	}

	public BigDecimal getPrecoDe() {
		return precoDe;
	}

	public void setPrecoDe(BigDecimal precoDe) {
		this.precoDe = precoDe;
	}

	public BigDecimal getPrecoAte() {
		return precoAte;
	}

	public void setPrecoAte(BigDecimal precoAte) {
		this.precoAte = precoAte;
	}

	public BigDecimal getComissaoDe() {
		return comissaoDe;
	}

	public void setComissaoDe(BigDecimal comissaoDe) {
		this.comissaoDe = comissaoDe;
	}

	public BigDecimal getComissaoAte() {
		return comissaoAte;
	}

	public void setComissaoAte(BigDecimal comissaoAte) {
		this.comissaoAte = comissaoAte;
	}

	public Integer getEstoqueDe() {
		return estoqueDe;
	}

	public void setEstoqueDe(Integer estoqueDe) {
		this.estoqueDe = estoqueDe;
	}

	public Integer getEstoqueAte() {
		return estoqueAte;
	}

	public void setEstoqueAte(Integer estoqueAte) {
		this.estoqueAte = estoqueAte;
	}

}
