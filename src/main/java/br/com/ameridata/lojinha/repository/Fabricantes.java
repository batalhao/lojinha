package br.com.ameridata.lojinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Fabricante;

@Repository
public interface Fabricantes extends JpaRepository<Fabricante, Long> {

	public List<Fabricante> findByAtivoTrue();

	public List<Fabricante> findByAtivoTrueOrderByNomeAsc();

}