package br.com.ameridata.lojinha.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode
@Entity
@Table(name = "usuarios_grupos")
@Getter
@Setter
public class UsuarioGrupo implements Serializable {

    @EmbeddedId
    private UsuarioGrupoId id;
    
}
