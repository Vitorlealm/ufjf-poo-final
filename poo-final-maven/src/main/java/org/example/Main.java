package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Teste!");
        Usuario usuario = new Usuario("Vitor", "EMail", "Vitor", "EMail","Vitor", false);
        usuario.setCpf("1");

        Dados.usuariosCadastrados.add(usuario);
        Dados.salvarEmDisco();
        Dados.atualizaMemoriaPrincipal();
        JFrame s = new JFrame("Chat 555555555555555555");
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        s.setSize(400, 400);
        s.setVisible(true);
        usuario.teste();
    }
}