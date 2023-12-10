package org.example.produtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public abstract class Produto {
    /*
    Integrante - Matrícula:

    Davi Aires Lage – 201965023A
    Vitor Leal de Oliveira Martins – 202276027
    Bruno Nascimento Rodrigues – 202065114A
     */
    private String nome;
    private BigDecimal valor;
    public static String[] tipos = new String[]{"HAMBURGUER", "ACOMPANHAMENTO", "SOBREMESA"};
    String tipo;

    public Produto() {
    }

    public abstract BigDecimal calcularValorItem();
}
