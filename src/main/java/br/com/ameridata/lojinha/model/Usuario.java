package br.com.ameridata.lojinha.model;

import br.com.ameridata.lojinha.validation.AtributoConfirmacao;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Classe: Usuário
 *
 * @author Paulo R. Batalhão
 * @version 1.0.0
 * @since 0.1.1
 */

@AtributoConfirmacao(atributo = "senha", atributoConfirmacao = "confirmacaoSenha", message = "Senha: A confirmação de senha é diferente.")
@Entity
@Table(name = "usuarios")
@DynamicUpdate
public class Usuario implements Serializable {

    private static final long serialVersionUID = -8630688574693255245L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome: Campo obrigatório.")
    @Size(max = 50, message = "Nome: Tamanho máximo de 50 caracteres.")
    private String nome;

    @NotBlank(message = "E-mail: Campo obrigatório.")
    @Size(max = 50, message = "E-mail: Tamanho máximo de 50 caracteres.")
    @Email(message = "E-mail: Inválido")
    private String email;

    //	@NotBlank(message = "Senha: Campo obrigatório.")
    //	@Size(max = 50, message = "Senha: Tamanho máximo de 50 caracteres.")
    private String senha;

    @Transient
//	@Size(max = 50, message = "Confirmação de senha: Tamanho máximo de 50 caracteres.")
    private String confirmacaoSenha;

    private boolean ativo;

    @NotNull(message = "Data de nascimento: Campo obrigatório.")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Size(min = 1, message = "Grupos: Selecione pelo menos 1 grupo.")
    @ManyToMany
    @JoinTable(name = "usuarios_grupos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<Grupo> grupos;

    public boolean isNovo() {
        return this.id == null;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    @PreUpdate
    private void preUpdate() {
        this.confirmacaoSenha = this.senha;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(id, other.id);
    }

}
