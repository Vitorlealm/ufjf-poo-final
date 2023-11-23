package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Teste!");
        Usuario usuario = new Usuario();
        JFrame s = new JFrame("Chat 555555555555555555");
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        s.setSize(400, 400);
        s.setVisible(true);
        usuario.teste();
    }
}