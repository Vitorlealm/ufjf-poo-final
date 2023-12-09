package org.example.produtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Bebida extends Produto{

    private int tamanhoMl;
    private String bebida;
    public static final String[] bebidas = new String[]{"COCA-COLA", "ABACATINHO", "GUARANA", "SPRITE", "AGUA", "CERVEJA"};
    public static final String[] copos = new String[]{"350", "600", "1000"};

    public Bebida(String bebida, String tamanhoMl) {
        this.setNome(bebida + " " + tamanhoMl + " ML");
        this.setTipo("BEBIDA");
        this.tamanhoMl =  Integer.parseInt(tamanhoMl);
        this.bebida = bebida;
    }

    @Override
    public BigDecimal calcularValorItem(){
        double valorTotal = 0;

        switch(this.bebida){
            case "AGUA":
                valorTotal = (4.20/1000) * this.tamanhoMl; //agua 4.20/litro
                break;
            case "CERVEJA":
                valorTotal = (14.90/1000) * this.tamanhoMl; //cerveja 14.90/litro
                break;
            default:
                valorTotal = (10.00/1000) * this.tamanhoMl; //agua 10.00/litro
                break;
        }

        return BigDecimal.valueOf(valorTotal).setScale(2, RoundingMode.HALF_UP);

    }
    
    @Override
    public String toString() {
        return "Bebida | " +
                "Tipo: " + bebida + 
                " " + tamanhoMl +
                "ml ";
    }

}
