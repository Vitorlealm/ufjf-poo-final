package org.example;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Pedido {
    private Long clienteId;
    private List<Produto> listaProdutos;
    private String enderecoEntrega;
    private BigDecimal valorTotal;
    private Date dataCriacao;

    public Pedido(Long clienteId, List<Produto> listaProdutos, String enderecoEntrega, BigDecimal valorTotal, Date dataCriacao) {
        this.clienteId = clienteId;
        this.listaProdutos = listaProdutos;
        this.enderecoEntrega = enderecoEntrega;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
