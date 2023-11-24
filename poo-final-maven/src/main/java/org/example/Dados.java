package org.example;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Dados {
    public static List<Usuario> usuariosCadastrados = new ArrayList<>();
    private static List<Produto> produtosCadastrados = new ArrayList<>();
    public static List<Pedido> listaPedidos = new ArrayList<>();

    private static final String USUARIOS_JSON = "src/main/resources/usuarios.json";
    private static final String PEDIDOS_JSON = "src/main/resources/pedidos.json";


    public static List<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

    public static List<Produto> getProdutosCadastrados() {
        return produtosCadastrados;
    }

    public static List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public static void setListaPedidos(List<Pedido> listaPedidos) {
        Dados.listaPedidos = listaPedidos;
    }

    public static void setUsuariosCadastrados(List<Usuario> usuariosCadastrados) {
        Dados.usuariosCadastrados = usuariosCadastrados;
    }


    public static void salvarEmDisco(){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(USUARIOS_JSON)) {
            writer.write(gson.toJson(Dados.usuariosCadastrados));
        }catch (IOException ioException){
            System.err.println(ioException.getMessage());
        }

        try (FileWriter writer = new FileWriter(PEDIDOS_JSON)){
            writer.write(gson.toJson(Dados.listaPedidos));
        }catch (IOException ioException){
            System.err.println(ioException.getMessage());
        }
    }

    public static void atualizaMemoriaPrincipal(){
        String usuarioJson = readJsonFromFile(USUARIOS_JSON);
        System.out.println("JSON lido do arquivo Usuarios: " + usuarioJson);
        Dados.usuariosCadastrados = mapJsonUsuario(usuarioJson);

        String pedidosJson = readJsonFromFile(PEDIDOS_JSON);
        System.out.println("JSON lido do arquivo Pedidos: " + pedidosJson);
        Dados.listaPedidos = mapJsonPedidos(pedidosJson);
    }

    private static String readJsonFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<Usuario> mapJsonUsuario(String json) {
        Type listType = new TypeToken<List<Usuario>>(){}.getType();
        return new Gson().fromJson(json, listType);
    }

    private static List<Pedido> mapJsonPedidos(String json) {
        Type listType = new TypeToken<List<Pedido>>(){}.getType();
        return new Gson().fromJson(json, listType);
    }
}
