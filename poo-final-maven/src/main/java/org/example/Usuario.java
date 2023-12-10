package org.example;

import lombok.Data;

import javax.swing.*;
import java.awt.event.MouseListener;

@Data
public class Usuario {
    /*
    Integrante - Matrícula:

    Davi Aires Lage – 201965023A
    Vitor Leal de Oliveira Martins – 202276027
    Bruno Nascimento Rodrigues – 202065114A
     */

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String endereco;

    private boolean ativo;
    private boolean admin;

    //Adicionar regras de negocio
    public Usuario(String nome, String email, String senha, String cpf, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.endereco = endereco;
        this.ativo = true;
        this.admin = false;
    }


    public void editarUsuario(String nome, String cpf, String endereco){
        for(int i = 0; i < Dados.usuariosCadastrados.size(); i++){
            if (Dados.usuariosCadastrados.get(i).getEmail() == this.email){
                Dados.usuariosCadastrados.get(i).setNome(nome);
                Dados.usuariosCadastrados.get(i).setCpf(cpf);
                Dados.usuariosCadastrados.get(i).setEndereco(endereco);

            }
        }
    }

    public void desativarUsuario(String email){
        for(int i = 0; i < Dados.usuariosCadastrados.size(); i++){
            if (Dados.usuariosCadastrados.get(i).getEmail() == this.email){
                Dados.usuariosCadastrados.get(i).setAtivo(false);
            }
        }
    }

    public static boolean validarEmail(String email) {
        int indiceArroba = email.indexOf('@');
        
        if (indiceArroba > 0 && indiceArroba < email.length() - 1) {
            
            int indicePonto = email.indexOf('.', indiceArroba);
            if (indicePonto > indiceArroba && indicePonto < email.length() - 1) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean validarCPF(String cpf) {
        
        cpf = cpf.replaceAll("[^0-9]", "");
        
        if (cpf.length() != 11) {
            return false;
        }
        
        boolean digitosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                digitosIguais = false;
                break;
            }
        }
        if (digitosIguais) {
            return false;
        }
        
        int soma = 0;
        int resto;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        
        resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : (11 - resto);
        
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : (11 - resto);
        
        
        return (Character.getNumericValue(cpf.charAt(9)) == digito1 &&
                Character.getNumericValue(cpf.charAt(10)) == digito2);
    }
    
    public static void main(String[] args) {
        String cpf = "123.456.789-09"; 
        boolean valido = validarCPF(cpf);
        if (valido) {
            System.out.println("CPF válido!");
        } else {
            System.out.println("CPF inválido!");
        }
    }

    

    @Override
    public String toString() {
        return nome;
    }
}
