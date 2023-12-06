package org.example;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class Hamburguer extends Produto{
    private Lanche lanche;

    public Hamburguer(Lanche lanche){
        switch (lanche){
            case X_BACON:
                this.setValor(BigDecimal.valueOf(12.9));
                break;
            case X_TUDO:
                this.setValor(BigDecimal.valueOf(17.90));
                break;
            case ESPECIAL:
                this.setValor(BigDecimal.valueOf(20));
                break;
        }
        this.setNome("O LANCHE É UM: " + lanche.name());
        this.setTipo(Tipos.HAMBURGUER);
        this.lanche = lanche;
    }

    public enum Lanche {
        X_TUDO,
        X_BACON,
        ESPECIAL

    }
}
