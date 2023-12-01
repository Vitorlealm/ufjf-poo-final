package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Produto {
    private String nome;
    private BigDecimal valor;

    private Tipos tipo;

    public Produto() {
    }

    public static Tipos[] getTiposPedido(){
        return new Tipos[]{Tipos.SOBREMESA, Tipos.HAMBURGUER, Tipos.ACOMPANHAMENTO};
    }

    public static enum Tipos {
        HAMBURGUER,
        SOBREMESA,
        ACOMPANHAMENTO
    }
}
