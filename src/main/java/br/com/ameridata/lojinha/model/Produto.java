package br.com.ameridata.lojinha.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.ameridata.lojinha.validation.SKU;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

	private static final long serialVersionUID = 8134784763999125418L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
//	@NotNull(message = "Empresa: Campo obrigatório.")
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	@SKU
	@NotBlank(message = "SKU: Campo obrigatório.")
	private String sku;

	@Pattern(regexp = "([0-9]+)?", message = "GTIN: Informe apenas números.")
	@Size(max = 14, message = "GTIN: Tamanho máximo de 14 dígitos numéricos.")
	private String gtin;

	@NotBlank(message = "Nome: Campo obrigatório.")
	@Size(max = 50, message = "Nome: Tamanho máximo de 50 caracteres.")
	private String nome;

	@NotBlank(message = "Descrição: Campo obrigatório.")
	@Size(max = 150, message = "Descrição: Tamanho entre 1 e 150 caracteres.")
	private String descricao;

	@NotNull(message = "Custo unitário: Campo obrigatório.")
	@DecimalMin(value = "0.01", message = "Custo unitário: Deve ser maior que R$0,01")
	@DecimalMax(value = "9999999.99", message = "Custo unitário: Deve ser menor que R$9.999.999,99")
	@Column(name = "custo_unitario")
	private BigDecimal custoUnitario;

	@NotNull(message = "Preço de venda: Campo obrigatório.")
	@DecimalMin(value = "0.01", message = "Preço de venda: Deve ser maior que R$0,01")
	@DecimalMax(value = "9999999.99", message = "Preço de venda: Deve ser menor que R$9.999.999,99")
	@Column(name = "preco_venda")
	private BigDecimal precoVenda;

//	@NotNull(message = "Comissão: Campo obrigatório.")
	@DecimalMax(value = "100.00", message = "Comissão: Deve ser menor ou igual a 100,00%")
	private BigDecimal comissao;

//	@NotNull(message = "Estoque: Campo obrigatório.")
	@Max(value = 999999, message = "Estoque: Deve ser menor que 999.999")
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;

	@NotNull(message = "Fabricante: Campo obrigatório.")
	@ManyToOne
	@JoinColumn(name = "fabricante_id")
	private Fabricante fabricante;

	@NotNull(message = "Fornecedor: Campo obrigatório.")
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;

	@NotNull(message = "Unidade: Campo obrigatório.")
	@Enumerated(EnumType.STRING)
	private Unidade unidade;

	@NotNull(message = "Origem: Campo obrigatório.")
	@Enumerated(EnumType.STRING)
	private Origem origem;

	private String foto;

	@Column(name = "content_type")
	private String contentType;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		this.sku = this.sku.toUpperCase();
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getCustoUnitario() {
		return custoUnitario;
	}

	public void setCustoUnitario(BigDecimal custoUnitario) {
		this.custoUnitario = custoUnitario;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}