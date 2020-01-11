package br.com.ameridata.lojinha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.ameridata.lojinha.model.Usuario;
import br.com.ameridata.lojinha.repository.Usuarios;
import br.com.ameridata.lojinha.service.exception.SenhaObrigatoriaUsuarioException;
import br.com.ameridata.lojinha.service.exception.UsuarioEmailCadastradoException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;

	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioOptional = usuarios.findByEmail(usuario.getEmail());

		if (usuarioOptional.isPresent()) {
			throw new UsuarioEmailCadastradoException("E-mail: Usuário já cadastrado.");
		}

		if (usuario.isNovo() || StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha: Campo obrigatório.");
		}

		usuarios.save(usuario);
	}

}
