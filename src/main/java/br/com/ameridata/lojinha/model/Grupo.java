package br.com.ameridata.lojinha.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
 
/**
 * Classe: Grupo
 *
 * @author Paulo R. Batalhão
 * @version 1.0.0
 * @since 0.1.1
 */

@Entity
@Table(name = "grupos")
@Getter
@Setter
public class Grupo implements Serializable {

    private static final long serialVersionUID = 7558982278274106276L;

    @Id
    private Long id;

    @NotBlank(message = "Nome: Campo obrigatório.")
    @Size(max = 50, message = "Nome: Tamanho máximo de 50 caracteres.")
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupos_permissoes", joinColumns = @JoinColumn(name = "grupo_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private List<Permissao> permissoes;

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
        Grupo other = (Grupo) obj;
        return Objects.equals(id, other.id);
    }

}
