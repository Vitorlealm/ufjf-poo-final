package org.example;

import lombok.Data;

import javax.swing.*;

@Data
public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String endereco;
    private boolean admin;

    public void teste(){
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
