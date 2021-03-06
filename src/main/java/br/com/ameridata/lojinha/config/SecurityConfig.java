package br.com.ameridata.lojinha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.ameridata.lojinha.security.AppUserDetailsService;

//@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/layout/**")
				.antMatchers("/images/**")
				.antMatchers("/fotos/**")
				.antMatchers("/javascripts/**")
				.antMatchers("/stylesheets/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/440").permitAll()
				.antMatchers("/500").permitAll()
				.antMatchers("/cidades/novo").hasRole("CADASTRAR_CIDADE")
				.antMatchers("/usuarios/novo").hasRole("CADASTRAR_USUARIO")
				.antMatchers("/usuarios/status").hasRole("CADASTRAR_USUARIO")
				.antMatchers("/produtos/novo").hasRole("CADASTRAR_PRODUTO")
				.antMatchers("/fabricantes/novo").hasRole("CADASTRAR_FABRICANTE")
				.antMatchers("/fornecedores/novo").hasRole("CADASTRAR_FORNECEDOR")
				.antMatchers("/clientes/novo").hasRole("CADASTRAR_CLIENTE")
				.antMatchers("/estados/novo").hasRole("CADASTRAR_ESTADO")
//				.antMatchers("/cidades/**").authenticated()
//				.antMatchers("/usuarios/**").authenticated()
				.antMatchers("/cidades").authenticated()
				.antMatchers("/usuarios").authenticated()
				.antMatchers("/produtos/**").authenticated()
				.antMatchers("/fabricantes").authenticated()
				.antMatchers("/fornecedores").authenticated()
				.antMatchers("/clientes").authenticated()
				.antMatchers("/estados").authenticated()
				.antMatchers("/vendas/**").authenticated()
				.antMatchers("/vendas/item").authenticated()
				.antMatchers("/javascripts/vendors/**").authenticated()
				.antMatchers("/stylesheets/vendors/**").authenticated()
				.anyRequest().denyAll()
			.and()
				.formLogin()
					.loginPage("/login").permitAll()
			.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
				.exceptionHandling()
					.accessDeniedPage("/403")
//			.and()
//				.sessionManagement()
//					.invalidSessionUrl("/440")
			.and()
				.sessionManagement()
					.maximumSessions(1).expiredUrl("/440");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
