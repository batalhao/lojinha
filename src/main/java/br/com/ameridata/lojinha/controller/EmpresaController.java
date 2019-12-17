package br.com.ameridata.lojinha.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ameridata.lojinha.model.Empresa;
import br.com.ameridata.lojinha.service.CadastroEmpresaService;
import br.com.ameridata.lojinha.service.exception.EmpresaNomeCadastradoException;

@Controller
public class EmpresaController {

	@Autowired
	private CadastroEmpresaService empresaService;

	@RequestMapping(value = "/empresas/novo", method = RequestMethod.GET)
	public ModelAndView novo(Empresa empresa) {
		return new ModelAndView("empresa/CadastroEmpresa");
	}

	@RequestMapping(value = "/empresas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Empresa empresa, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(empresa);
		}

		try {
			empresaService.salvar(empresa);
		} catch (EmpresaNomeCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(empresa);
		}

		attributes.addFlashAttribute("mensagem", "Empresa salva com sucesso!");

		return new ModelAndView("redirect:/empresas/novo");
	}

}
