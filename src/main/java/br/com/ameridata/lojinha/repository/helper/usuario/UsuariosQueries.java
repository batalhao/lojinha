package br.com.ameridata.lojinha.repository.helper.usuario;

import br.com.ameridata.lojinha.model.Usuario;
import br.com.ameridata.lojinha.repository.filter.UsuarioFilter;

import java.util.List;
import java.util.Optional;

public interface UsuariosQueries {

    public Optional<Usuario> buscaPorEmailEAtivo(String email);

    public List<String> permissoes(Usuario usuario);

    public List<Usuario> filtrar(UsuarioFilter filtro);

}
