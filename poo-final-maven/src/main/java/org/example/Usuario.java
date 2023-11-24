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


    public static boolean validarCPF(String cpf) {
        // Removendo caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");
        
        // Verificando se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Verificando CPFs com todos os dígitos iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        // Calculando o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (10 - i) * Integer.parseInt(String.valueOf(cpf.charAt(i)));
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        
        // Calculando o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (11 - i) * Integer.parseInt(String.valueOf(cpf.charAt(i)));
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }
        
        // Verificando se os dígitos calculados são iguais aos dígitos informados
        return (digito1 == Integer.parseInt(String.valueOf(cpf.charAt(9))) &&
                digito2 == Integer.parseInt(String.valueOf(cpf.charAt(10))));
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
