package org.example.produtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public abstract class Produto {
    private String nome;
    private BigDecimal valor;

    String tipo;

    public Produto() {
    }

    public abstract BigDecimal calcularValorItem();



    public static String[] tipos = new String[]{"HAMBURGUER", "ACOMPANHAMENTO", "SOBREMESA"};
}
