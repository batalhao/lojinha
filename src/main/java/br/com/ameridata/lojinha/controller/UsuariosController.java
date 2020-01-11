package br.com.ameridata.lojinha.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Usuario;
import br.com.ameridata.lojinha.repository.Grupos;
import br.com.ameridata.lojinha.service.CadastroUsuarioService;
import br.com.ameridata.lojinha.service.exception.UsuarioEmailCadastradoException;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuariosController {

	@Autowired
	private CadastroUsuarioService usuarioService;

	@Autowired
	private Grupos grupos;

//	@RequestMapping(value = "/usuarios/novo", method = RequestMethod.GET)
	@GetMapping(value = "/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuario/CadastroUsuario");
		modelAndView.addObject("grupos", grupos.findAll());
		return modelAndView;
	}

//	@RequestMapping(value = "/usuarios/novo", method = RequestMethod.POST)
	@PostMapping(value = "/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(usuario);
		}

		try {
			usuarioService.salvar(usuario);
		} catch (UsuarioEmailCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		}

		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");

		return new ModelAndView("redirect:/usuarios/novo");
	}

}
