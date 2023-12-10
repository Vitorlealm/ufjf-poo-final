package org.example.produtos;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Hamburguer extends Produto{
    /*
    Integrante - Matrícula:

    Davi Aires Lage – 201965023A
    Vitor Leal de Oliveira Martins – 202276027
    Bruno Nascimento Rodrigues – 202065114A
     */

    private int salada;
    private int carne;
    private int queijo;
    private int presunto;
    private int bacon;
    private int ovo;

    public Hamburguer(int salada, int carne, int queijo, int presunto, int bacon, int ovo) {
            this.setNome("Hamburguer: "+
            salada + " Salada " +
            carne + " Carne " +
            queijo + " Queijo " +
            presunto + " Presunto " +
            bacon + " Bacon " +
            ovo + " Ovo"
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
    
    @Override
    public String toString() {
        return "Hamburguer | " +
                "Salada: " + salada + 
                ", Carne: " + carne +
                ", Queijo: " + queijo +
                ", Presunto: " + presunto +
                ", Bacon: " + bacon +
                ", Ovo: " + ovo +
                '}';
    }
}
