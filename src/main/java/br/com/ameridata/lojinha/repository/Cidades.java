package br.com.ameridata.lojinha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Cidade;

@Repository
public interface Cidades extends JpaRepository<Cidade, Long> {

	public Optional<Cidade> findByNome(String nome);

	public Optional<Cidade> findByNomeIgnoreCase(String nome);

	public List<Cidade> findAllByOrderByNomeAsc();

}