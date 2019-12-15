package br.com.ameridata.lojinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Fornecedor;

@Repository
public interface Fornecedores extends JpaRepository<Fornecedor, Long> {

	public List<Fornecedor> findByAtivoTrue();

	public List<Fornecedor> findByAtivoTrueOrderByNomeAsc();

}