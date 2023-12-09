package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.exceptions.UsuarioJaCadastradoException;
import org.example.exceptions.UsuarioNaoEncontradoException;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.example.Dados.getIdPedidos;
import static org.example.Dados.salvarEmDisco;
import org.example.produtos.Produto;

public class Dados {
    public static List<Usuario> usuariosCadastrados = new ArrayList<>();
    private static List<Produto> produtosCadastrados = new ArrayList<>();
    public static List<Pedido> listaPedidos = new ArrayList<>();
    private static final String USUARIOS_JSON = "src/main/resources/usuarios.json";
    private static final String PEDIDOS_JSON = "src/main/resources/pedidos.json";
    private static Usuario usuarioLogado;

    private static Long idPedidos = getIdPedidos();

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        Dados.usuarioLogado = usuarioLogado;
    }

    public static boolean login(String email, String senha){
        for(Usuario usuario : Dados.usuariosCadastrados){
            if(usuario.getEmail() ==  email){
                if(usuario.getSenha() == senha){
                    setUsuarioLogado(usuario);
                    return true;
                }
            }
        }
        System.err.println("Usuario ou senha incorretos");
        return false;
    }

    public static boolean logout(){
        if (usuarioLogado != null){
            usuarioLogado = null;
            return true;
        }
        else{
            System.out.println("Usuario logado nao encontrado");
            return false;
        }
    }

    private static void atualizarIdPedido(){
        Long ultimoId = Dados.listaPedidos.stream()
                .mapToLong(Pedido::getId)
                .max()
                .orElse(0);
        idPedidos = ultimoId + 1L;
    }

    public static List<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

    public static void cadastrarUsuario(Usuario usuario) throws Exception{
        for(Usuario u : Dados.usuariosCadastrados){
            if(u.getEmail().equals(usuario.getEmail())){
                System.out.println("Email já cadastrado: " + usuario.getEmail());
                throw new UsuarioJaCadastradoException();
            }
        }
        Dados.usuariosCadastrados.add(usuario);
        Dados.salvarEmDisco();
        System.out.println("Usuario cadastrado com sucesso: " + usuario.getEmail());

    }

    public static void excluirUsuario(Usuario u) {
        for(int i=0; i < Dados.usuariosCadastrados.size(); i++){
            if(Dados.usuariosCadastrados.get(i).getEmail().equalsIgnoreCase(u.getEmail())){
                Dados.usuariosCadastrados.remove(i);
            }
        }
        salvarEmDisco();
    }

    public static void editarUsuario(String nome, String email, String senha, String endereco, String cpf, boolean admin) throws UsuarioNaoEncontradoException {
        for(Usuario u : Dados.usuariosCadastrados){
            if(u.getCpf().equalsIgnoreCase(cpf)){
                u.setNome(nome);
                u.setEmail(email);
                u.setSenha(senha);
                u.setEndereco(endereco);
                u.setAdmin(admin);
            }
        }
        if (cpf == null){
            throw new UsuarioNaoEncontradoException();
        }
        salvarEmDisco();
    }
    public static Usuario buscaUsuario(String cpf) throws UsuarioNaoEncontradoException {
        Usuario usuario = null;
        for(int i=0; i < Dados.usuariosCadastrados.size(); i++){
            if(Dados.usuariosCadastrados.get(i).getCpf() == cpf){
                usuario = Dados.usuariosCadastrados.get(i);
            }
        }
        if (usuario == null){
            throw new UsuarioNaoEncontradoException();
        }
        return usuario;
    }

    public static void editarPedido(Long id, String clienteEmail, String enderecoEntrega, BigDecimal valorTotal, String status){
        for(Pedido p : Dados.listaPedidos){
            if(p.getId() == id){
                p.setClienteEmail(clienteEmail);
                p.setEnderecoEntrega(enderecoEntrega);
                p.setValorTotal(valorTotal);
                p.setStatus(status);
            }
        }
        salvarEmDisco();
    }


    public static void pedidoEntregue(Pedido p){
        for(Pedido pedido : Dados.listaPedidos){
            if(p.getId().equals(pedido.getId())){
                if(pedido.getStatus() != "ENTREGUE"){
                    pedido.setStatus("ENTREGUE");
                }
                else{
                    pedido.setStatus("ANDAMENTO");
                }
            }
        }
        salvarEmDisco();
    }

    public static void cancelarPedido(Pedido p){
        for(int i=0; i < Dados.listaPedidos.size(); i++){
            if(Dados.listaPedidos.get(i).getId().equals(p.getId())){
                Dados.listaPedidos.remove(i);
            }
        }
        salvarEmDisco();
    }

    public static void limparPedidos(){
        for(int i = Dados.listaPedidos.size()-1; i >= 0; i--){
            Dados.listaPedidos.remove(i);
        }
        salvarEmDisco();
    }

    public static void cadastrarPedido(Pedido pedido){
        //atualizarIdPedido();
        for(Pedido p : Dados.listaPedidos){
            if(p.getId().equals(pedido.getId())){
                System.out.println("Pedido já cadastrado: " + p.getId());
                return;
            }
        }
        Dados.listaPedidos.add(pedido);
        Dados.salvarEmDisco();
        System.out.println("Pedido cadastrado com sucesso: " + pedido.getId());

    }

    public static boolean autenticaUsuario(String email, String senha) throws UsuarioNaoEncontradoException {
        for(Usuario u : Dados.usuariosCadastrados){
            if(u.getEmail().equalsIgnoreCase(email)){
                if(u.getSenha().equals(senha)){
                    Dados.usuarioLogado = u;
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        throw new UsuarioNaoEncontradoException();
    }


    public static void salvarEmDisco(){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(USUARIOS_JSON)) {
            writer.write(gson.toJson(Dados.usuariosCadastrados));
        }catch (IOException ioException){
            System.err.println(ioException.getMessage());
        }

        Gson gsonProdutos = new GsonBuilder()
                .registerTypeAdapter(Produto.class,
                        new PropertyBasedInterfaceMarshal())
                .create();

        try (FileWriter writer = new FileWriter(PEDIDOS_JSON)){
            writer.write(gsonProdutos.toJson(Dados.listaPedidos));
        }catch (IOException ioException){
            System.err.println(ioException.getMessage());
        }
    }

    public static void atualizaMemoriaPrincipal(){
        String usuarioJson = readJsonFromFile(USUARIOS_JSON);
        System.out.println("JSON lido do arquivo Usuarios: " + usuarioJson);
        if(usuarioJson.length() > 0) {
            Dados.usuariosCadastrados = mapJsonUsuario(usuarioJson);
        }

        String pedidosJson = readJsonFromFile(PEDIDOS_JSON);
        System.out.println("JSON lido do arquivo Pedidos: " + pedidosJson);
        if(pedidosJson.length() > 0) {
            Dados.listaPedidos = mapJsonPedidos(pedidosJson);
        }

        Dados.idPedidos = 0L;
        if(!Dados.listaPedidos.isEmpty()){
            Long maiorId =  Dados.listaPedidos.get(0).getId();
            for(Pedido pedido : Dados.listaPedidos){
                if(pedido.getId() > maiorId){
                    maiorId = pedido.getId();
                }
            }
            idPedidos = maiorId + 1L;
        }
    }

    public static List<Pedido> getPedidosUsuario(Usuario usuario){
        List<Pedido> listaPedidos = new ArrayList<>();
        for(Pedido p : Dados.listaPedidos){
            if(p.getClienteEmail().equalsIgnoreCase(usuario.getEmail())){
                listaPedidos.add(p);
            }
        }
        return listaPedidos;
    }

    public static Long getIdPedidos(){
        return Dados.idPedidos;
    }

    public static void incrementaIdPedidos(){
        Dados.idPedidos += 1L;
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
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Produto.class,
                        new PropertyBasedInterfaceMarshal())
                .create();
        Type listType = new TypeToken<List<Pedido>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}
