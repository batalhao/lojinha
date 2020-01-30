package br.com.ameridata.lojinha.controller;

import br.com.ameridata.lojinha.controller.page.PageWrapper;
import br.com.ameridata.lojinha.model.Cliente;
import br.com.ameridata.lojinha.model.Status;
import br.com.ameridata.lojinha.model.TipoPessoa;
import br.com.ameridata.lojinha.repository.Cidades;
import br.com.ameridata.lojinha.repository.Clientes;
import br.com.ameridata.lojinha.repository.Estados;
import br.com.ameridata.lojinha.repository.filter.ClienteFilter;
import br.com.ameridata.lojinha.service.CadastroClienteService;
import br.com.ameridata.lojinha.service.exception.ClienteDocumentoCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value = "/clientes")
public class ClientesController {

    @Autowired
    private Estados estados;

    @Autowired
    private Cidades cidades;

    @Autowired
    private CadastroClienteService clienteService;

    @Autowired
    private Clientes clientes;

    //	@RequestMapping(value = "/clientes/novo", method = RequestMethod.GET)
    @GetMapping(value = "/novo")
    public ModelAndView novo(Cliente cliente) {
        ModelAndView modelAndView = new ModelAndView("cliente/CadastroCliente");

        List<TipoPessoa> listaTiposPessoas = Arrays.asList(TipoPessoa.values());
        listaTiposPessoas.sort(Comparator.comparing(TipoPessoa::getDescricao));
        modelAndView.addObject("tiposPessoa", listaTiposPessoas);

        modelAndView.addObject("status", Status.values());
        modelAndView.addObject("estados", estados.findAllByOrderByNomeAsc());
        modelAndView.addObject("cidades", cidades.findAllByOrderByNomeAsc());

        return modelAndView;
    }

    //	@RequestMapping(value = "/clientes/novo", method = RequestMethod.POST)
    @PostMapping(value = "/novo")
    public ModelAndView cadastrar(@Valid Cliente cliente, BindingResult result, Model model,
                                  RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return novo(cliente);
        }

        try {
            clienteService.salvar(cliente);
        } catch (ClienteDocumentoCadastradoException e) {
            result.rejectValue("documento", e.getMessage(), e.getMessage());
            return novo(cliente);
        }

        attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");

        return new ModelAndView("redirect:/clientes/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result,
                                  @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("cliente/PesquisaClientes");

        PageWrapper<Cliente> pageWrapper = new PageWrapper<>(clientes.filtrar(clienteFilter, pageable),
                httpServletRequest);

        modelAndView.addObject("pagina", pageWrapper);

        return modelAndView;
    }

    @RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody
    List<Cliente> pesquisar(String nome) {
        if (StringUtils.isEmpty(nome) || nome.length() < 3) {
            throw new IllegalArgumentException();
        }

        List<Cliente> clientesList = clientes.findByNomeStartingWithIgnoreCase(nome);
        clientesList.forEach(c -> {
            c.setCidade(null);
            c.setEstado(null);
        });

        return clientesList;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> tratarIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
