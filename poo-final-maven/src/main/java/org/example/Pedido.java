package org.example;

import lombok.Data;
import org.example.produtos.Produto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Pedido {
    /*
    Integrante - Matrícula:

    Davi Aires Lage – 201965023A
    Vitor Leal de Oliveira Martins – 202276027
    Bruno Nascimento Rodrigues – 202065114A
     */


    private Long id;
    private String clienteEmail;
    private List<Produto> listaProdutos;
    private String enderecoEntrega;
    private BigDecimal valorTotal;
    private Date dataCriacao;
    private String status;

    public Pedido(Usuario usuario, List<Produto> listaProdutos) {
        this.clienteEmail = usuario.getEmail();
        this.listaProdutos = listaProdutos;
        this.enderecoEntrega = usuario.getEndereco();
        this.dataCriacao = new Date();
        this.status = "ANDAMENTO";
        this.id = Dados.getIdPedidos();
        Dados.incrementaIdPedidos();
        BigDecimal valor = new BigDecimal(0);
        for(Produto p : listaProdutos){
            valor = valor.add(p.getValor());
        }
        this.valorTotal = valor.setScale(2, RoundingMode.HALF_UP);
    }

    public String concatenaNomeProdutos(){
        String nomesConcatenados = listaProdutos.stream()
                .map(Produto::getNome)
                .collect(Collectors.joining(", "));

        return nomesConcatenados;
    }

    @Override
    public String toString() {
        String stringProdutos;

        return id + " | " + clienteEmail + " | Status: " + status ;
    }

    public void cancelarPedido(){
        for(int i = 0; i < Dados.listaPedidos.size(); i++){
            if (Dados.listaPedidos.get(i).getId() == this.id){
                Dados.listaPedidos.get(i).setStatus("CANCELADO");
            }
        }
    }
}
