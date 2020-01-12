package br.com.ameridata.lojinha.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import br.com.ameridata.lojinha.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> buscaPorEmailEAtivo(String email);

	public List<String> permissoes(Usuario usuario);

}
