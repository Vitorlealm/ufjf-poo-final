package org.example;

import lombok.Data;

import javax.swing.*;
import java.awt.event.MouseListener;

@Data
public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String endereco;
    private boolean admin;

    public Usuario(String nome, String email, String senha, String cpf, String endereco, boolean admin) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.endereco = endereco;
        this.admin = admin;
    }


    public void setCpf(String cpf){
        if(cpf.length() < 3){
            System.err.println("Menor q tres");
        }
        else{
            this.cpf = cpf;
            System.out.println(this.cpf);
        }
    }

    public void teste(){
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        JButton button = new JButton("AAAAAAA");
        frame.add(button);
        frame.setVisible(true);
    }
}
