package org.example.exceptions;

public class UsuarioNaoSelecionadoException extends Exception{
    public UsuarioNaoSelecionadoException(){
        super("Nenhum usuario selecionado");
    }
}
