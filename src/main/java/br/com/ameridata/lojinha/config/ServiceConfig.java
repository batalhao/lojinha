package br.com.ameridata.lojinha.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.ameridata.lojinha.service.CadastroProdutoService;

@Configuration
@ComponentScan(basePackageClasses = CadastroProdutoService.class)
public class ServiceConfig {

}
