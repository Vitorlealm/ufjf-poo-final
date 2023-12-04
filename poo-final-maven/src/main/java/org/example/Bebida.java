package org.example;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Bebida extends Produto{
    private Tamanho tamanho;
    private TipoBebida tipoBebida;

    public BigDecimal defineValor(double valor, TipoBebida tipo){
        BigDecimal val;
        switch (tipo){
            case DRINK:
                valor = valor*2;
                val = BigDecimal.valueOf(valor);
                break;
            case REFRIGERANTE:
                valor = valor*1.2;
                val = BigDecimal.valueOf(valor);
                break;
            case AGUA:
                val = BigDecimal.valueOf(valor);
                break;
            case CERVEJA:
                valor = valor*1.5;
                val = BigDecimal.valueOf(valor);
                break;
            default:
                val = BigDecimal.valueOf(valor);
                break;
        }
        return val;
    }

    public Bebida(Tamanho tamanho, TipoBebida tipoBebida){
        //seta preço base da bebida
        switch (tamanho){
            case PEQUENO:
                this.setValor(defineValor(5, tipoBebida));
                break;
            case MEDIO:
                this.setValor(defineValor(12, tipoBebida));
                break;
            case GRANDE:
                this.setValor(defineValor(15, tipoBebida));
                break;

        }
        this.setNome("O LANCHE É UM: " + tipoBebida.name());
        this.setTipo(Tipos.ACOMPANHAMENTO);
        this.tipoBebida = tipoBebida;
        this.tamanho = tamanho;
    }


    public enum Tamanho {
        PEQUENO,
        MEDIO,
        GRANDE
    }

    public enum TipoBebida {
        DRINK,
        REFRIGERANTE,
        CERVEJA,
        AGUA
    }
}
