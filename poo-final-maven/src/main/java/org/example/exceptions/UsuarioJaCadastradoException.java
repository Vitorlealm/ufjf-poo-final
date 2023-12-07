package org.example.exceptions;

public class UsuarioJaCadastradoException extends Exception{

    public UsuarioJaCadastradoException(){
        super("Usuario já existe!");
    }
}