package org.example;

import lombok.Data;

@Data
public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String endereco;
    private boolean admin;
}
