package org.example;

import org.example.view.InterfaceGrafica;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Carrega dados salvos nos JSONs
        Produto p1 = new Sorvete(Sorvete.Tamanho.GRANDE, Sorvete.Cobertura.CHOCOLATE);
        System.out.println(p1+" "+p1.getValor());

        Produto p2 = new Hamburguer(Hamburguer.Lanche.X_BACON);
        System.out.println(p2+" "+p2.getValor());

    }
}