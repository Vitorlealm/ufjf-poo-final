package org.example.produtos;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Hamburguer extends Produto{

    private int salada;
    private int carne;
    private int queijo;
    private int presunto;
    private int bacon;
    private int ovo;

    public Hamburguer(int salada, int carne, int queijo, int presunto, int bacon, int ovo) {
        this.setNome("HAMB: "+
            salada + "S " +
            carne + "C " +
            queijo + "Q " +
            presunto + "P " +
            bacon + "B " +
            ovo + "O"
        );
        this.setTipo("HAMBURGUER");
        this.salada = salada;
        this.carne = carne;
        this.queijo = queijo;
        this.presunto = presunto;
        this.bacon = bacon;
        this.ovo = ovo;
    }

    @Override
    public BigDecimal calcularValorItem(){
        double valorTotal = 2.00;
        valorTotal = valorTotal + (this.salada * 1.50);
        valorTotal = valorTotal + (this.carne * 2.50);
        valorTotal = valorTotal + (this.queijo * 1.00);
        valorTotal = valorTotal + (this.presunto * 1.00);
        valorTotal = valorTotal + (this.bacon * 1.50);
        valorTotal = valorTotal + (this.ovo * 1.00);
        return BigDecimal.valueOf(valorTotal).setScale(2, RoundingMode.HALF_UP);

    }
}
