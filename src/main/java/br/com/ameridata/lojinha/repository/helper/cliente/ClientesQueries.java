package br.com.ameridata.lojinha.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ameridata.lojinha.model.Cliente;
import br.com.ameridata.lojinha.repository.filter.ClienteFilter;

public interface ClientesQueries {

	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);

}
