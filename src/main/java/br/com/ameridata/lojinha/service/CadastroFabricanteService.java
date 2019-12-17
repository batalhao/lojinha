package br.com.ameridata.lojinha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ameridata.lojinha.model.Fabricante;
import br.com.ameridata.lojinha.repository.Fabricantes;
import br.com.ameridata.lojinha.service.exception.FabricanteDocumentoCadastradoException;

@Service
public class CadastroFabricanteService {

	@Autowired
	private Fabricantes fabricantes;

	@Transactional
	public void salvar(Fabricante fabricante) {
		Optional<Fabricante> fabricanteOptional = fabricantes.findByDocumento(fabricante.getDocumento());
		if (fabricanteOptional.isPresent()) {
			throw new FabricanteDocumentoCadastradoException("Documento: Fabricante já cadastrado.");
		}

		fabricantes.save(fabricante);
	}

}
