package org.example.exceptions;

public class UsuarioNaoEncontradoException extends Exception{

    public UsuarioNaoEncontradoException(){
        super("Usuario não encontrado.");
    }
}
