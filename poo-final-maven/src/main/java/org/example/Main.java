package org.example;

import org.example.view.InterfaceGrafica;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    /*
    Integrante - Matrícula:

    Davi Aires Lage – 201965023A
    Vitor Leal de Oliveira Martins – 202276027
    Bruno Nascimento Rodrigues – 202065114A
     */

    public static void main(String[] args) {
        //Carrega dados salvos nos JSONs
        Dados.atualizaMemoriaPrincipal();
        InterfaceGrafica tela = new InterfaceGrafica();
        tela.incicializaTela();
    }
}