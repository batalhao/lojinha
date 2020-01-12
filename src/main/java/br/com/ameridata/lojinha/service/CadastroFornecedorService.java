package br.com.ameridata.lojinha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ameridata.lojinha.model.Fornecedor;
import br.com.ameridata.lojinha.repository.Fornecedores;
import br.com.ameridata.lojinha.service.exception.FornecedorDocumentoCadastradoException;

@Service
public class CadastroFornecedorService {

	@Autowired
	private Fornecedores fornecedores;

	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor) {
		String documento = Fornecedor.removerFormatacaoDocumento(fornecedor.getDocumento());
		Optional<Fornecedor> fornecedorOptional = fornecedores.findByDocumento(documento);
		if (fornecedorOptional.isPresent()) {
			throw new FornecedorDocumentoCadastradoException("Documento: Fornecedor já cadastrado.");
		}

		return fornecedores.saveAndFlush(fornecedor);
	}

}
