package br.com.ameridata.lojinha.dto;

import br.com.ameridata.lojinha.model.Origem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;
    private String sku;
    private String nome;
    private String origem;
    private BigDecimal precoVenda;
    private String foto;

    public ProdutoDTO(Long id, String sku, String nome, Origem origem, BigDecimal precoVenda, String foto) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.origem = origem.getDescricao();
        this.precoVenda = precoVenda;
        this.foto = foto;
    }

}
