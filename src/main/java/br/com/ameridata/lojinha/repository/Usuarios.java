package br.com.ameridata.lojinha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long> /* , UsuariosQueries */ {

	public Optional<Usuario> findByEmail(String email);

	public Optional<Usuario> findByEmailIgnoreCaseAndAtivoTrue(String email);

}
