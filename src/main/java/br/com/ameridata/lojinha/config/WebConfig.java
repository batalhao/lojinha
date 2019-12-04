package br.com.ameridata.lojinha.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.ameridata.lojinha.controller.ProdutosController;

@Configuration
@ComponentScan(basePackageClasses = { ProdutosController.class })
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer /* extends WebMvcConfigurerAdapter */ {

}