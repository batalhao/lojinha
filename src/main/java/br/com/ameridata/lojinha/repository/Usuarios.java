package br.com.ameridata.lojinha.repository;

import br.com.ameridata.lojinha.model.Usuario;
import br.com.ameridata.lojinha.repository.helper.usuario.UsuariosQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findByEmailIgnoreCaseAndAtivoTrue(String email);

    List<Usuario> findByIdIn(Long[] ids);
}
