package org.example;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Sorvete extends Produto{

    private Tamanho tamanho;
    private Cobertura cobertura;
    public Sorvete(Tamanho tamanho, Cobertura cobertura) {
        BigDecimal valorTotal = BigDecimal.valueOf(0);
        switch (tamanho){
            case PEQUENO:
                this.setValor(BigDecimal.valueOf(5.90));
                break;
            case MEDIO:
                this.setValor(BigDecimal.valueOf(7.90));
                break;
            case GRANDE:
                this.setValor(BigDecimal.valueOf(9.90));
                break;

        }
        this.setNome("SORVETE DE " + cobertura.name());
        this.setTipo(Tipos.SOBREMESA);
        this.cobertura = cobertura;
        this.tamanho = tamanho;
    }



    public enum Cobertura {
        MORANGO,
        BAUNILHA,
        CHOCOLATE
    }

    public enum Tamanho {
        PEQUENO,
        MEDIO,
        GRANDE
    }
}
