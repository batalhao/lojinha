package br.com.ameridata.lojinha.security;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.ameridata.lojinha.model.Usuario;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = -3471087755364456816L;

	private Usuario usuario;

	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);

		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getSaudacao() {
		Calendar data = Calendar.getInstance();
		int hora = data.get(Calendar.HOUR_OF_DAY);

		String saudacao = "Olá " + this.usuario.getNome();
		boolean aniversario = verificaAniversario(data);

		if (aniversario) {
			return saudacao + ", feliz aniversário!";
		}

		if (hora >= 18 && hora < 24) {
			return saudacao + ", boa noite!";
		} else if (hora >= 12 && hora < 18) {
			return saudacao + ", boa tarde!";
		} else if (hora >= 0 && hora < 12) {
			return saudacao + ", bom dia!";
		} else {
			return saudacao;
		}
	}

	private boolean verificaAniversario(Calendar data) {
		if (this.usuario.getDataNascimento() != null) {
			boolean mesmoMes = this.usuario.getDataNascimento().getDayOfMonth() == data.get(Calendar.DAY_OF_MONTH);
			boolean mesmoDia = this.usuario.getDataNascimento().getMonthValue() == data.get(Calendar.MONTH) + 1;

			return mesmoMes && mesmoDia;
		} else {
			return false;
		}
	}

}
