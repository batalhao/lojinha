package br.com.ameridata.lojinha.repository;

import br.com.ameridata.lojinha.model.Cliente;
import br.com.ameridata.lojinha.repository.helper.cliente.ClientesQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {

    public Optional<Cliente> findByNome(String nome);

    public Optional<Cliente> findByNomeIgnoreCase(String nome);

    public Optional<Cliente> findByDocumento(String dcumento);

    List<Cliente> findByNomeStartingWithIgnoreCase(String nome);

}
