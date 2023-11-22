package org.example;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Pedido {
    private Long clienteId;
    private List<Produto> listaProdutos;
    private String enderecoEntrega;
    private BigDecimal valorTotal;
    private Date dataCriacao;
}
