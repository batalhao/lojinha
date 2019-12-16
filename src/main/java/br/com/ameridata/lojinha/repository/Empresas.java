package br.com.ameridata.lojinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Empresa;

@Repository
public interface Empresas extends JpaRepository<Empresa, Long> {

}
