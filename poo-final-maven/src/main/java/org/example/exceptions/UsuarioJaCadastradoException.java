package org.example.exceptions;

public class UsuarioJaCadastradoException extends Exception{
    /*
    Integrante - Matrícula:

    Davi Aires Lage – 201965023A
    Vitor Leal de Oliveira Martins – 202276027
    Bruno Nascimento Rodrigues – 202065114A
     */

    public UsuarioJaCadastradoException(){
        super("Usuario já existe!");
    }
}