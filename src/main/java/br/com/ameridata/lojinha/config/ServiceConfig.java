package br.com.ameridata.lojinha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.ameridata.lojinha.service.CadastroProdutoService;
import br.com.ameridata.lojinha.storage.FotoStorage;
import br.com.ameridata.lojinha.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroProdutoService.class)
public class ServiceConfig {

	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}

}
