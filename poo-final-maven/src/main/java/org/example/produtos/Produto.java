package org.example.produtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class Produto {
    private String nome;
    private BigDecimal valor;
    public static String[] tipos = new String[]{"HAMBURGUER", "ACOMPANHAMENTO", "SOBREMESA"};
    String tipo;

    public Produto() {
    }

    public abstract BigDecimal calcularValorItem();
}
