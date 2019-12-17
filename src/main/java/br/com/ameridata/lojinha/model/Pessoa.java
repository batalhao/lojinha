package br.com.ameridata.lojinha.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = -8183707589451071606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome: Campo obrigatório.")
	@Size(max = 50, message = "Nome: Tamanho máximo de 50 caracteres.")
	private String nome;

	@NotBlank(message = "Documento: Campo obrigatório.")
	@Pattern(regexp = "([0-9]+)?", message = "Documento: Informe apenas números.")
	@Size(max = 14, message = "Documento: Tamanho máximo de 14 caracteres.")
	private String documento;

	@Pattern(regexp = "([0-9]+)?", message = "Telefone: Informe apenas números.")
	@Size(max = 15, message = "Telefone: Tamanho máximo de 15 caracteres.")
	private String telefone;

	@Email(message = "Email: Inválido")
	@Size(max = 100, message = "Email: Tamanho máximo de 100 caracteres.")
	private String email;

	@Size(max = 100, message = "Logradouro: Tamanho máximo de 100 caracteres.")
	private String logradouro;

	@Size(max = 20, message = "Número: Tamanho máximo de 20 caracteres.")
	private String numero;

	@Size(max = 50, message = "Complemento: Tamanho máximo de 50 caracteres.")
	private String complemento;

	@Size(max = 10, message = "CEP: Tamanho máximo de 10 caracteres.")
	private String cep;

//	@NotNull(message = "Status: Campo obrigatório.")
	private Boolean ativo;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Tipo pessoa: Campo obrigatório.")
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa;

	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
