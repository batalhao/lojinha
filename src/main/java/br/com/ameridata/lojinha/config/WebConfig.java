package br.com.ameridata.lojinha.config;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;

import br.com.ameridata.lojinha.controller.ProdutosController;
import br.com.ameridata.lojinha.controller.converter.CidadeConverter;
import br.com.ameridata.lojinha.controller.converter.EmpresaConverter;
import br.com.ameridata.lojinha.controller.converter.EstadoConverter;
import br.com.ameridata.lojinha.controller.converter.FabricanteConverter;
import br.com.ameridata.lojinha.controller.converter.FornecedorConverter;
import br.com.ameridata.lojinha.controller.converter.GrupoConverter;
import br.com.ameridata.lojinha.thymeleaf.LojinhaDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackageClasses = { ProdutosController.class })
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableCaching
public class WebConfig implements ApplicationContextAware, WebMvcConfigurer /* extends WebMvcConfigurerAdapter */ {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();

		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");

		return resolver;
	}

	@Bean
	public ISpringTemplateEngine /* TemplateEngine */ templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();

		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());

		engine.addDialect(new LayoutDialect());
		engine.addDialect(new LojinhaDialect());
		engine.addDialect(new DataAttributeDialect());

		engine.addDialect(new SpringSecurityDialect());

		return engine;
	}

	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

		resolver.setApplicationContext(this.applicationContext);
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);

		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		conversionService.addConverter(new FornecedorConverter());
		conversionService.addConverter(new FabricanteConverter());
		conversionService.addConverter(new EmpresaConverter());
		conversionService.addConverter(new EstadoConverter());
		conversionService.addConverter(new CidadeConverter());
		conversionService.addConverter(new GrupoConverter());

		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
		conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);

		NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
		conversionService.addFormatterForFieldType(Integer.class, integerFormatter);

		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		dateTimeFormatter.registerFormatters(conversionService);

		return conversionService;
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@Bean
	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager();

//		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(3).expireAfterAccess(20,
//				TimeUnit.SECONDS);
//
//		GuavaCacheManager cacheManager = new GuavaCacheManager();
//		cacheManager.setCacheBuilder(cacheBuilder);

//		return cacheManager;

		CaffeineCacheManager caffeineManager = new CaffeineCacheManager();
		Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder().maximumSize(3).expireAfterAccess(20,
				TimeUnit.SECONDS);
		caffeineManager.setCaffeine(caffeineBuilder);

		return caffeineManager;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("classpath:/messages");
		bundle.setDefaultEncoding("UTF-8");
		return bundle;
	}

}