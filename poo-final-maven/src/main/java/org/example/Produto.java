package org.example;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Produto {
    private Long idProduto;
    private String nome;
    private BigDecimal valor;
}
