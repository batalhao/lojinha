package br.com.ameridata.lojinha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ameridata.lojinha.model.Empresa;
import br.com.ameridata.lojinha.repository.Empresas;
import br.com.ameridata.lojinha.service.exception.EmpresaNomeCadastradoException;

@Service
public class CadastroEmpresaService {

	@Autowired
	private Empresas empresas;

	@Transactional
	public void salvar(Empresa empresa) {
		Optional<Empresa> empresaOptional = empresas.findByNomeIgnoreCase(empresa.getNome());

		if (empresaOptional.isPresent()) {
			throw new EmpresaNomeCadastradoException("Nome: Empresa já cadastrada.");
		}

		empresas.save(empresa);
	}

}