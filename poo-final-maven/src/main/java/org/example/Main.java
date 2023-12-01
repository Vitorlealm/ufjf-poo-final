package org.example;

import org.example.view.InterfaceGrafica;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Carrega dados salvos nos JSONs
        Dados.atualizaMemoriaPrincipal();
        InterfaceGrafica tela = new InterfaceGrafica();
        tela.incicializaTela();
        Usuario usuarioteste = new Usuario("Vitor", "vmart@gmail.com", "123456", "1112121112", "rua A", true, true);
        Dados.cadastrarUsuario(usuarioteste);
        System.out.println(Dados.getUsuariosCadastrados());

        List<Produto> listaPedidos = new ArrayList<>();
        Sorvete sorvete = new Sorvete(Sorvete.Tamanho.MEDIO, Sorvete.Cobertura.CHOCOLATE);
        listaPedidos.add(sorvete);
        Pedido pedido = new Pedido(usuarioteste,listaPedidos);
        Dados.cadastrarPedido(pedido);
        Dados.salvarEmDisco();
        System.out.println(Dados.listaPedidos);

    }
}