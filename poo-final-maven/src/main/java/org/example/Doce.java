package org.example;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Doce extends Produto{
    private Sabor sabor;
    private Especificacao especificacao;

    public BigDecimal calculaPreco(Sabor sabor){
        BigDecimal val;
        switch (sabor){
            case AGRIDOCE:
                val = BigDecimal.valueOf(10 + 3);
                break;
            case MORANGO:
                val = BigDecimal.valueOf(15 + 2);
                break;
            case CARAMELO:
                val = BigDecimal.valueOf(20 + 1);
                break;
            default:
                val = BigDecimal.valueOf(8);
        }
        return val;
    }

    public Doce(Especificacao espec, Sabor sabor){
        switch (espec){
            case TRUFA:
                this.setValor(calculaPreco(sabor));
                break;
            case BOMBOM:
                this.setValor(BigDecimal.valueOf(17.90));
                break;
            case CROASSANT:
                this.setValor(BigDecimal.valueOf(20));
                break;
        }
        this.setNome("O doce é um(a): " + espec.name() + " de "+sabor.name());
        this.setTipo(Tipos.SOBREMESA);
        this.especificacao = espec;
        this.sabor = sabor;
    }

    public enum Sabor{
        CHOCOLATE,
        MORANGO,
        CARAMELO,
        AGRIDOCE
    }

    public enum Especificacao{
        TRUFA,
        BOMBOM,
        CROASSANT
    }
}
