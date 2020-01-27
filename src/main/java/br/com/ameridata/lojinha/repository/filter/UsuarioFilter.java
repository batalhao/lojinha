package br.com.ameridata.lojinha.repository.filter;

import br.com.ameridata.lojinha.model.Grupo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioFilter {

    private String nome;
    private String email;
    private List<Grupo> grupos;

}
