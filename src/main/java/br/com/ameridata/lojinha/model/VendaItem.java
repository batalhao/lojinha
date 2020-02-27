package br.com.ameridata.lojinha.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(exclude = { "quantidade", "valorUnitario", "produto" })
public class VendaItem {

    private Long id;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private Produto produto;

    public BigDecimal getValorTotal() {
        return valorUnitario.multiply(new BigDecimal(quantidade));
    }

}
