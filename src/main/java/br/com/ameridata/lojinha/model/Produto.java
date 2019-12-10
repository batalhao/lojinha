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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

	private static final long serialVersionUID = 8134784763999125418L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "SKU é obrigatório")
	private String sku;

	private String gtin;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@Size(min = 1, max = 50, message = "A descrição deve ter tamanho entre 1 e 50")
	private String descricao;

	@Column(name = "custo_unitario")
	private BigDecimal custoUnitario;

	@Column(name = "preco_venda")
	private BigDecimal precoVenda;

	private BigDecimal comissao;

	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;

	@ManyToOne
	@JoinColumn(name = "fabricante_id")
	private Fabricante fabricante;

	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;

	@Enumerated(EnumType.STRING)
	private Unidade unidade;

	@Enumerated(EnumType.STRING)
	private Origem origem;

}