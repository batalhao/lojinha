package br.com.ameridata.lojinha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/vendas")
public class VendasController {

    @GetMapping("/novo")
    public String novo() {
        return "venda/CadastroVenda";
    }

}
