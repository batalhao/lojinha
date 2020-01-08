package br.com.ameridata.lojinha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ameridata.lojinha.model.Cliente;
import br.com.ameridata.lojinha.repository.helper.cliente.ClientesQueries;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {

	public Optional<Cliente> findByNome(String nome);

	public Optional<Cliente> findByNomeIgnoreCase(String nome);

	public Optional<Cliente> findByDocumento(String dcumento);

}
