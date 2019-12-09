package br.com.ameridata.lojinha.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Usuario;

@Controller
public class UsuariosController {

	@RequestMapping(value = "/usuarios/novo", method = RequestMethod.GET)
	public String novo(Usuario usuario) {
		return "usuario/CadastroUsuario";
	} 

	@RequestMapping(value = "/usuarios/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(usuario);
		}

		// Salvar banco de dados...

		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");

		System.out.println(">>> Nome: " + usuario.getNome());
		return "redirect:/usuarios/novo";
	}

}
