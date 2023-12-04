package org.example;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Bebida extends Produto{
    private Tamanho tamanho;
    private TipoBebida tipoBebida;

    public BigDecimal calculaValor(double valor, double ajuste){
        valor = valor*ajuste;
        return BigDecimal.valueOf(valor);
    }

    public BigDecimal defineValor(double valor, TipoBebida tipo){
        BigDecimal val;
        switch (tipo){
            case DRINK:
                val = calculaValor(valor, 2);
                break;
            case REFRIGERANTE:
                val = calculaValor(valor, 1.2);
                break;
            case CERVEJA:
                val = calculaValor(valor, 1.5);
                break;
            default:
                // Caso seja agua
                val = BigDecimal.valueOf(valor);
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
        this.setNome("A bebida escolhida foi: " + tipoBebida.name());
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
