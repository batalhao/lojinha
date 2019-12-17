package br.com.ameridata.lojinha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ameridata.lojinha.model.Cliente;
import br.com.ameridata.lojinha.repository.Clientes;
import br.com.ameridata.lojinha.service.exception.ClienteDocumentoCadastradoException;
import br.com.ameridata.lojinha.service.exception.ClienteNomeCadastradoException;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;

	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Cliente> clienteOptional = clientes.findByNomeIgnoreCase(cliente.getNome());
		if (clienteOptional.isPresent()) {
			throw new ClienteNomeCadastradoException("Nome: Cliente já cadastrado.");
		}

		clienteOptional = clientes.findByDocumento(cliente.getDocumento());
		if (clienteOptional.isPresent()) {
			throw new ClienteDocumentoCadastradoException("Documento: Cliente já cadastrado.");
		}

		clientes.save(cliente);
	}

}