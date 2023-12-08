package org.example.view;

import lombok.Data;
import org.example.Dados;
import org.example.Pedido;
import org.example.produtos.Produto;
import org.example.exceptions.UsuarioNaoEncontradoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class InterfaceGrafica extends JFrame {

    private JFrame tela;
    private final int WIDTH = 1000;
    private final int HEIGHT = 500;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    public void incicializaTela() {
        tela = new JFrame("Lanchonete Orientado a Xtudo");
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desenhaLogin();
        tela.pack();
        tela.setVisible(true);
        tela.setLayout(new BorderLayout());
        desenhaLogin();

    }


    public void desenhaLogin() {

        // Criação do painel
        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Campos de entrada
        JLabel rotuloEmail = new JLabel("Email:");
        final JTextField campoEmail = new JTextField();
        campoEmail.setPreferredSize(new Dimension(100, 25));

        JLabel rotuloSenha = new JLabel("Senha:");
        final JPasswordField campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(100, 25));


        // Botões
        JButton botaoLogin = new JButton("Login");
        JButton botaoCriarConta = new JButton("Criar Conta");

        // Adiciona componentes ao painel
        painel.add(rotuloEmail);
        painel.add(campoEmail);
        painel.add(rotuloSenha);
        painel.add(campoSenha);
        painel.add(botaoLogin);
        painel.add(botaoCriarConta);

        // Adiciona o painel à janela
        tela.getContentPane().add(painel, BorderLayout.CENTER);

        // Adiciona ação ao botão "Login"
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores dos campos de texto
                String email = campoEmail.getText();
                char[] passwordChars = campoSenha.getPassword();
                String senha = new String(passwordChars);

                System.out.println("Usuário: " + email);
                System.out.println("Senha: " + senha);

                try {
                    if(Dados.autenticaUsuario(email, senha)){
                        desenhaTelaClientes();
                    }
                    else {
                        JOptionPane.showMessageDialog(tela, "Senha incorreta.");

                    }
                } catch (UsuarioNaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(tela, ex.getMessage());

                }
            }
        });
    }

    public void desenhaTelaClientes() {
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Bem vindo ao Orientado a Xtudo!"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JTable tabelaPedidos = criarTabelaClientes();
        tabelaPedidos.setPreferredSize(new Dimension(WIDTH, HEIGHT/2));
        JScrollPane scrollPane = new JScrollPane(tabelaPedidos);
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT/2));
        painel.add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton botao1 = new JButton("Fazer novo pedido");
        JButton botao2 = new JButton("Botão 2");
        painelBotoes.add(botao1);
        painelBotoes.add(botao2);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        botao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              desenhaTelaFazerPedido();
            }
        });

        tela.getContentPane().removeAll();
        tela.getContentPane().add(painel, BorderLayout.CENTER);
        tela.revalidate();
        tela.repaint();
    }

    public void desenhaTelaFazerPedido(){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Faça seu pedido!"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JLabel rotuloTipoPedido = new JLabel("Selecione um tipo de alimento:");
        JComboBox comboBox = new JComboBox<>(Produto.getTiposPedido());
        comboBox.setPreferredSize(new Dimension(WIDTH/2, 25));
        JButton botaoFazerPedido = new JButton("Fazer pedido.");

        JLabel a = new JLabel("Selecione um tipo de alimento:");
        JComboBox b = new JComboBox<>(Produto.getTiposPedido());
        JButton c = new JButton("Fazer pedido.");



        botaoFazerPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboBox.getSelectedItem());
            }
        });


        painel.add(rotuloTipoPedido);
        painel.add(comboBox);
        painel.add(botaoFazerPedido);

        painel.add(a);
        painel.add(b);
        painel.add(c);


        tela.getContentPane().removeAll();
        tela.getContentPane().add(painel, BorderLayout.CENTER);
        tela.revalidate();
        tela.repaint();
    }


    private JTable criarTabelaClientes() {
        // Lógica para criar e popular a tabela de clientes
        // Substitua ou adapte conforme necessário
        List<Pedido> listaPedidos = Dados.getPedidosUsuario(Dados.getUsuarioLogado());
        String[] colunas = {"Numero do pedido", "Produtos", "Valor Total", "Endereço", "Data de criação", "Status"};
        Object[][] dados = new Object[listaPedidos.size()][6];
        for (int i = 0; i < listaPedidos.size(); i++) {
            dados[i][0] = listaPedidos.get(i).getId();
            dados[i][1] = listaPedidos.get(i).concatenaNomeProdutos();
            dados[i][2] = listaPedidos.get(i).getValorTotal();
            dados[i][3] = listaPedidos.get(i).getEnderecoEntrega();
            dados[i][4] = listaPedidos.get(i).getDataCriacao();
            dados[i][5] = listaPedidos.get(i).getStatus();
        }

        JTable tabela = new JTable(dados, colunas);
        // Configurar outras propriedades da tabela conforme necessário

        return tabela;
    }

    private List<String> criarListaDeObjetos() {
        // Implemente a lógica para criar e retornar uma lista de objetos
        // Este método é apenas um exemplo e pode ser substituído pela sua lógica específica
        List<String> lista = new ArrayList<>();
        lista.add("Objeto 1");
        lista.add("Objeto 2");
        lista.add("Objeto 3");
        return lista;
    }

}
