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
				.antMatchers("/cidades/novo").hasRole("CADASTRAR_CIDADE")
				.antMatchers("/usuarios/novo").hasRole("CADASTRAR_USUARIO")
				.antMatchers("/produtos/**").authenticated()
				.antMatchers("/fabricantes/**").authenticated()
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
			.and()
				.sessionManagement()
					.invalidSessionUrl("/440")
					.maximumSessions(1).expiredUrl("/440");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
