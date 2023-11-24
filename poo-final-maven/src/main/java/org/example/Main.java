package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String nome, email, senha, cpf, endereco;
        Boolean admin;
        nome = "Davi";
        email = "davi.aires@estudante.ufjf.br";
        senha = "123";
        cpf = "000.000.000-44";
        endereco = "R BvP";
        admin = false;

        Usuario user = new Usuario(nome, email, senha, cpf, endereco, admin);

        System.out.println(user.getNome()+" mora em "+ user.getEndereco());
    }
}