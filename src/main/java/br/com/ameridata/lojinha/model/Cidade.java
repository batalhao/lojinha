package br.com.ameridata.lojinha.model;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cidades")
@Getter
@Setter
public class Cidade implements Serializable {

    private static final long serialVersionUID = -6898722947358560393L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Integer id;

    @NotBlank(message = "Nome: Campo obrigatório.")
    @Size(max = 50, message = "Nome: Tamanho máximo de 50 caracteres.")
    @Expose
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Estado: Campo obrigatório.")
    @JoinColumn(name = "estado_id")
//	@JsonIgnore
    private Estado estado;

    public boolean hasEstado() {
        return this.estado != null;
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
        Cidade other = (Cidade) obj;
        return Objects.equals(id, other.id);
    }

}
