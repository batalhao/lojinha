package br.com.ameridata.lojinha.service;

import br.com.ameridata.lojinha.model.Usuario;
import br.com.ameridata.lojinha.repository.Usuarios;
import br.com.ameridata.lojinha.service.exception.SenhaObrigatoriaUsuarioException;
import br.com.ameridata.lojinha.service.exception.UsuarioEmailCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CadastroUsuarioService {

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void salvar(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarios.findByEmail(usuario.getEmail());

        if (usuarioOptional.isPresent()) {
            throw new UsuarioEmailCadastradoException("E-mail: Usuário já cadastrado.");
        }

        if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
            throw new SenhaObrigatoriaUsuarioException("Senha: Campo obrigatório.");
        }

        if (usuario.isNovo()) {
            usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
            usuario.setConfirmacaoSenha(usuario.getSenha());
        }

        usuarios.save(usuario);
    }

    @Transactional
    public void alterarStatus(Long[] ids, String status) {
        usuarios.findByIdIn(ids).forEach(u -> u.setAtivo(status.equals("ATIVAR")));
    }

}
