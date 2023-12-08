package org.example.produtos;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Sorvete extends Produto {

    private String tamanho;
    private String cobertura;
    
    public Sorvete(String tamanho, String cobertura) {
        this.setNome("SORVETE " + cobertura + " " + tamanho);
        this.setTipo("SOBREMESA");
        this.cobertura = cobertura;
        this.tamanho = tamanho;
    }


    @Override
    public BigDecimal calcularValorItem(){
        BigDecimal valorTotal = BigDecimal.valueOf(0);
        switch (this.tamanho){
            case "PEQUENO":
                valorTotal = BigDecimal.valueOf(5.90).setScale(2, RoundingMode.HALF_UP);
                break;
            case "MEDIO":
                valorTotal = BigDecimal.valueOf(7.90).setScale(2, RoundingMode.HALF_UP);
                break;
            case "GRANDE":
                valorTotal = BigDecimal.valueOf(9.90).setScale(2, RoundingMode.HALF_UP);
                break;
            default:
                valorTotal = BigDecimal.valueOf(0);
        }
        return valorTotal.setScale(2, RoundingMode.HALF_UP);
    }



    public static String[] coberturas = new String[]{"MORANGO", "CHOCOLATE", "BAUNILHA"};

    public static String[] tamanhos = new String[]{"PEQUENO", "MEDIO", "GRANDE"};

}
