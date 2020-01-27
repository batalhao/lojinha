package br.com.ameridata.lojinha.controller;

import br.com.ameridata.lojinha.controller.page.PageWrapper;
import br.com.ameridata.lojinha.model.Usuario;
import br.com.ameridata.lojinha.repository.Grupos;
import br.com.ameridata.lojinha.repository.Usuarios;
import br.com.ameridata.lojinha.repository.filter.UsuarioFilter;
import br.com.ameridata.lojinha.service.CadastroUsuarioService;
import br.com.ameridata.lojinha.service.exception.SenhaObrigatoriaUsuarioException;
import br.com.ameridata.lojinha.service.exception.UsuarioEmailCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuariosController {

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Autowired
    private Grupos grupos;

    @Autowired
    private Usuarios usuarios;

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
        } catch (SenhaObrigatoriaUsuarioException e) {
            result.rejectValue("senha", e.getMessage(), e.getMessage());
            return novo(usuario);
        }

        attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");

        return new ModelAndView("redirect:/usuarios/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(UsuarioFilter usuarioFilter, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("usuario/PesquisaUsuarios");
        modelAndView.addObject("grupos", grupos.findAll());

//        modelAndView.addObject("usuarios", usuarios.filtrar(usuarioFilter));
        PageWrapper<Usuario> pageWrapper = new PageWrapper<>(usuarios.filtrar(usuarioFilter, pageable), httpServletRequest);
        modelAndView.addObject("pagina", pageWrapper);
        return modelAndView;
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarStatus(@RequestParam("ids[]") Long[] ids, @RequestParam("status") String status) {
//        Arrays.asList(ids).forEach(System.out::println);
//        System.out.println(">> Status: " + status);
        usuarioService.alterarStatus(ids, status);
    }

}
