package br.com.ameridata.lojinha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Cidade;
import br.com.ameridata.lojinha.model.Estado;

@Repository
public interface Cidades extends JpaRepository<Cidade, Long> {

	public Optional<Cidade> findByNome(String nome);

	public Optional<Cidade> findByNomeIgnoreCase(String nome);

	public List<Cidade> findAllByOrderByNomeAsc();

	public List<Cidade> findByEstadoIdOrderByNomeAsc(Integer id);

	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);

}