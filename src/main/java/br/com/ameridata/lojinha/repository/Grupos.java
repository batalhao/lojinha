package br.com.ameridata.lojinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long> {

}
