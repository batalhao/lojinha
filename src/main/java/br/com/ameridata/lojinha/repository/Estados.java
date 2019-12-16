package br.com.ameridata.lojinha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Estado;

@Repository
public interface Estados extends JpaRepository<Estado, Long> {

	public Optional<Estado> findByUf(String uf);

	public Optional<Estado> findByUfIgnoreCase(String uf);

	public Optional<Estado> findByNome(String nome);

	public Optional<Estado> findByNomeIgnoreCase(String nome);

}
