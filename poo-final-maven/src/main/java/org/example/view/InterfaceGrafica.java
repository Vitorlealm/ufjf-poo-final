package org.example.view;

import org.example.Dados;
import org.example.Pedido;
import org.example.Produto;
import org.example.exceptions.UsuarioNaoEncontradoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.example.Usuario;
import org.example.produtos.Produto;
public class InterfaceGrafica extends JFrame {

    private JFrame tela;
    private final int WIDTH = 1000;
    private final int HEIGHT = 500;
    private Usuario usuarioAux;
    private Pedido pedidoAux;

    private Produto produtoAux;

    private Produto produtoEditAux;

    private List<Produto> listaProdutosAux = new ArrayList<>();

    private void selecionaUsuario(Usuario selectedUser) {
        usuarioAux = selectedUser;
    }
    private void selecionaPedido(Pedido pedidoSelecionado) {
        pedidoAux = pedidoSelecionado;
    }

    private void selecionaProduto(Produto produtoSelecionado) {
        produtoAux = produtoSelecionado;
    }

    public void incicializaTela() {
        tela = new JFrame("Lanchonete Orientado a Xtudo");
        tela.setSize(WIDTH, HEIGHT);

        tela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(tela,
                        "Quer sair?", "Pede pra sair",
                        JOptionPane.YES_NO_OPTION, JOptionPane.CANCEL_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    System.out.println("Fechando a aplicação...");
                    System.exit(0);
                } else {
                    Dados.salvarEmDisco();
                    tela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);
        tela.setLayout(new BorderLayout());
        desenhaLogin();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
        tela.getContentPane().removeAll();
        tela.getContentPane().add(painel, BorderLayout.CENTER);
        tela.revalidate();
        tela.repaint();

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
                       if(Dados.getUsuarioLogado().isAdmin()){
                           desenhaTelaAdmin();
                       }
                       else{
                           desenhaTelaClientes();
                       }
                    }
                    else {
                        JOptionPane.showMessageDialog(tela, "Senha incorreta.");
                    }
                } catch (UsuarioNaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(tela, ex.getMessage());

                }
            }
        });
        botaoCriarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores dos campos de texto
                desenhaCadastroUsuario();
            }
        });
    }

    public void desenhaCadastroUsuario(){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Cadastro de Usuario"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new BoxLayout(painelInput, BoxLayout.Y_AXIS));
        painel.setPreferredSize(new Dimension(WIDTH , HEIGHT));

        JLabel rotuloNome = new JLabel("Nome:");
            final JTextField campoNome = new JTextField();
            campoNome.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoNome.getPreferredSize().height));
        JLabel rotuloEmail = new JLabel("Email:");
            final JTextField campoEmail = new JTextField();
            campoEmail.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoEmail.getPreferredSize().height));

        JLabel rotuloSenha = new JLabel("Senha:");
            final JPasswordField campoSenha = new JPasswordField();
            campoSenha.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoSenha.getPreferredSize().height));
        JLabel rotuloCpf = new JLabel("CPF:");
            final JTextField campoCPF = new JTextField();
            campoCPF.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoCPF.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoCPF.getPreferredSize().height));
        JLabel rotuloEndereco = new JLabel("Endereço:");
            final JTextField campoEndereco = new JTextField();
            campoEndereco.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoEndereco.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoEndereco.getPreferredSize().height));

        JButton botao1 = new JButton("Criar usuario");
        JButton botaoVoltar = new JButton("Voltar");

        botao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    Dados.cadastrarUsuario(new Usuario(
                            campoNome.getText(),
                            campoEmail.getText(),
                            campoSenha.getText(),
                            campoCPF.getText(),
                            campoEndereco.getText()
                    ));
                    JOptionPane.showMessageDialog(tela, "Usuario cadastrado com sucesso!");
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(tela, e.getMessage());
                }
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                desenhaLogin();
            }
        });

        painelInput.add(rotuloNome);
        painelInput.add(campoNome);
        painelInput.add(rotuloEmail);
        painelInput.add(campoEmail);
        painelInput.add(rotuloSenha);
        painelInput.add(campoSenha);
        painelInput.add(rotuloCpf);
        painelInput.add(campoCPF);
        painelInput.add(rotuloEndereco);
        painelInput.add(campoEndereco);
        painelInput.add(botao1);
        painelInput.add(botaoVoltar);

        painel.add(painelInput, BorderLayout.CENTER);

        tela.getContentPane().removeAll();
        tela.getContentPane().add(painel, BorderLayout.CENTER);
        tela.revalidate();
        tela.repaint();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ADMIN

    public void desenhaTelaAdmin(){
        JPanel painelPai = new JPanel();
        painelPai.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        painelPai.setLayout(new BoxLayout(painelPai, BoxLayout.Y_AXIS));

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Painel de administração"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT - 50));
        painel.setLayout(new BoxLayout(painel, BoxLayout.X_AXIS));
        JButton botaoLogout = new JButton("Logout");
        botaoLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Dados.logout();
               desenhaLogin();
            }
        });

        painel.add(desenhaListaUsuarios(), BorderLayout.WEST);
        painel.add(desenhaPainelPedidos(), BorderLayout.EAST);
        painelPai.add(botaoLogout, BorderLayout.NORTH);
        painelPai.add(painel);

        tela.getContentPane().removeAll();
        tela.getContentPane().add(painelPai, BorderLayout.CENTER);
        tela.revalidate();
        tela.repaint();
    }

    private JPanel desenhaListaUsuarios(){

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Usuarios"));

        DefaultListModel<Usuario> listModel = new DefaultListModel<>();
        for(Usuario u : Dados.usuariosCadastrados){
            listModel.addElement(u);
        }
        painel.setPreferredSize(new Dimension(WIDTH/2, 500));
        painel.setLayout(new BorderLayout());
        JList listaUsuarios = new JList<>(listModel);
        listaUsuarios.setPreferredSize(new Dimension(WIDTH/2, 400));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setPreferredSize(new Dimension(WIDTH/2, 25));
            JButton botaoEditarUsuario = new JButton("Editar usuario");
            JButton botaoExcluirUsuario = new JButton("Excluir usuario");
            botaoEditarUsuario.setPreferredSize(new Dimension(200, 25));
            botaoExcluirUsuario.setPreferredSize(new Dimension(200, 25));
            painelBotoes.add(botaoEditarUsuario, BorderLayout.WEST);
            painelBotoes.add(botaoExcluirUsuario, BorderLayout.WEST);

        botaoEditarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usuarioAux != null) {
                    desenhaTelaEditarUsuario(usuarioAux);
                }else{
                    JOptionPane.showMessageDialog(tela, "Selecione um usuario");
                }
            }
        });
        botaoExcluirUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usuarioAux != null) {
                    Dados.excluirUsuario(usuarioAux);
                    System.err.println(usuarioAux.toString());
                    desenhaTelaAdmin();
                }else{
                    JOptionPane.showMessageDialog(tela, "Selecione um usuario");
                }
            }
        });


        painel.add(new JScrollPane(listaUsuarios), BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);
        listaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList<?> list = (JList<?>) evt.getSource();
                if (evt.getClickCount() == 1) {
                    // Obtém o índice do item clicado
                    int index = list.locationToIndex(evt.getPoint());

                    // Obtém o objeto Usuario clicado no modelo da lista
                    Usuario selectedUser = listModel.getElementAt(index);

                    // Chama a função com o Usuario clicado
                    selecionaUsuario(selectedUser);
                }
            }
        });

        return painel;
    }

    public void desenhaTelaEditarUsuario(Usuario usuario){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Edição de Usuario"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new BoxLayout(painelInput, BoxLayout.Y_AXIS));
        JLabel rotuloNome = new JLabel("Nome:");
            final JTextField campoNome = new JTextField(usuario.getNome());
            campoNome.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoNome.getPreferredSize().height));
        JLabel rotuloEmail = new JLabel("Email:");
            final JTextField campoEmail = new JTextField(usuario.getEmail());
            campoEmail.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoEmail.getPreferredSize().height));
        JLabel rotuloSenha = new JLabel("Senha:");
            final JPasswordField campoSenha = new JPasswordField(usuario.getSenha());
            campoSenha.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoSenha.getPreferredSize().height));
        JLabel rotuloCpf = new JLabel("CPF:");
            final JTextField campoCPF = new JTextField(usuario.getCpf());
            campoCPF.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoCPF.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoCPF.getPreferredSize().height));
            campoCPF.setEnabled(false);
        JLabel rotuloEndereco = new JLabel("Endereço:");
            final JTextField campoEndereco = new JTextField(usuario.getEndereco());
            campoEndereco.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoEndereco.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoEndereco.getPreferredSize().height));
        JCheckBox checkBox = new JCheckBox("Administrador", usuario.isAdmin());


        JButton botao1 = new JButton("Salvar usuario");
        JButton botaoVoltar = new JButton("Voltar");
        botao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                   Dados.editarUsuario(campoNome.getText(), campoEmail.getText(), campoSenha.getText(), campoEndereco.getText(), campoCPF.getText(), checkBox.isSelected());
                    JOptionPane.showMessageDialog(tela, "Usuario editado com sucesso!");
                    desenhaTelaAdmin();
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(tela, e.getMessage());
                }
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                desenhaTelaAdmin();
            }
        });

        painelInput.add(rotuloNome);
        painelInput.add(campoNome);
        painelInput.add(rotuloEmail);
        painelInput.add(campoEmail);
        painelInput.add(rotuloSenha);
        painelInput.add(campoSenha);
        painelInput.add(rotuloCpf);
        painelInput.add(campoCPF);
        painelInput.add(rotuloEndereco);
        painelInput.add(campoEndereco);
        painelInput.add(checkBox);
        painelInput.add(botao1);
        painelInput.add(botaoVoltar);
        painel.add(painelInput, BorderLayout.CENTER);

        tela.getContentPane().removeAll();
        tela.getContentPane().add(painel, BorderLayout.CENTER);
        tela.revalidate();
        tela.repaint();

    }

    public JPanel desenhaPainelPedidos(){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Lista de pedidos"));
        painel.setPreferredSize(new Dimension(WIDTH/2, 500));

        DefaultListModel<Pedido> listModel = new DefaultListModel<>();
        for(Pedido p : Dados.listaPedidos){
            listModel.addElement(p);
        }
        painel.setLayout(new BorderLayout());
        JList listaPedidos = new JList<>(listModel);

        JPanel painelBotoes = new JPanel();
            painelBotoes.setPreferredSize(new Dimension(WIDTH/2, 25));
            JButton botaoVisualizar = new JButton("Visualizar pedido");
            JButton botaoMarcarEntregue = new JButton("Pedido entregue");
            JButton botaoCancelarPedido = new JButton("Cancelar pedido");
            botaoVisualizar.setPreferredSize(new Dimension(150, 25));
            botaoMarcarEntregue.setPreferredSize(new Dimension(150, 25));
            botaoCancelarPedido.setPreferredSize(new Dimension(150, 25));
            painelBotoes.add(botaoVisualizar, BorderLayout.WEST);
            painelBotoes.add(botaoCancelarPedido, BorderLayout.WEST);
            painelBotoes.add(botaoMarcarEntregue, BorderLayout.WEST);


        botaoVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pedidoAux != null) {
                    desenhaTelaPedido(pedidoAux);
                }else{
                    JOptionPane.showMessageDialog(tela, "Selecione um pedido");
                }
            }
        });
        botaoMarcarEntregue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pedidoAux != null) {
                    Dados.pedidoEntregue(pedidoAux);
                    desenhaTelaAdmin();
                }else{
                    JOptionPane.showMessageDialog(tela, "Selecione um pedido");
                }
            }
        });
        botaoCancelarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pedidoAux != null) {
                    Dados.cancelarPedido(pedidoAux);
                    desenhaTelaAdmin();
                }else{
                    JOptionPane.showMessageDialog(tela, "Selecione um pedido");
                }
            }
        });

        listaPedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList<?> list = (JList<?>) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int index = list.locationToIndex(evt.getPoint());
                    Pedido pedidoSelecionado = listModel.getElementAt(index);
                    selecionaPedido(pedidoSelecionado);
                }
            }
        });


        painel.add(new JScrollPane(listaPedidos), BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);
        return painel;

    }

    public void desenhaTelaPedido(Pedido pedido){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Edição de pedido"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JPanel painelInput = new JPanel();
        painelInput.setLayout(new BoxLayout(painelInput, BoxLayout.Y_AXIS));
        JLabel rotuloNome = new JLabel("Nome:");
            final JTextField campoNome = new JTextField(pedido.concatenaNomeProdutos());
            campoNome.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoNome.getPreferredSize().height));
        JLabel rotuloEmail = new JLabel("Email:");
            final JTextField campoEmail = new JTextField(pedido.getClienteEmail());
            campoEmail.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoEmail.getPreferredSize().height));
        JLabel rotuloEndereco = new JLabel("Endereço:");
            final JTextField campoEndereco = new JTextField(pedido.getEnderecoEntrega());
            campoEndereco.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoEndereco.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoEndereco.getPreferredSize().height));
        JLabel rotuloValorTotal = new JLabel("Valor total:");
            JFormattedTextField campoValorTotal = new JFormattedTextField(NumberFormat.getNumberInstance());
            campoValorTotal.setValue(pedido.getValorTotal());
            campoValorTotal.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoValorTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoValorTotal.getPreferredSize().height));
            campoValorTotal.setEnabled(false);
        JLabel rotuloData = new JLabel("Data:");
            final JTextField campoData = new JTextField(String.valueOf(pedido.getDataCriacao()));
            campoData.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoData.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoEndereco.getPreferredSize().height));
        JLabel rotuloStatus = new JLabel("Status:");
            final JTextField campoStatus = new JTextField(String.valueOf(pedido.getStatus()));
            campoStatus.setPreferredSize(new Dimension(WIDTH -150, 25));
            campoStatus.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoStatus.getPreferredSize().height));

        JButton botao1 = new JButton("Salvar pedido");
        JButton botaoVoltar = new JButton("Voltar");

        painelInput.add(rotuloNome);
        painelInput.add(campoNome);
        painelInput.add(rotuloEmail);
        painelInput.add(campoEmail);
        painelInput.add(rotuloValorTotal);
        painelInput.add(campoValorTotal);
        painelInput.add(rotuloData);
        painelInput.add(campoData);
        painelInput.add(rotuloEndereco);
        painelInput.add(campoEndereco);
        painelInput.add(rotuloStatus);
        painelInput.add(campoStatus);
        painelInput.add(botao1);
        painelInput.add(botaoVoltar);

        painel.add(painelInput, BorderLayout.CENTER);

        botao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    Dados.editarPedido(pedido.getId(), campoEmail.getText(), campoEndereco.getText(), (BigDecimal) campoValorTotal.getValue(),campoStatus.getText());
                    JOptionPane.showMessageDialog(tela, "Usuario editado com sucesso!");
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(tela, e.getMessage());
                }
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                desenhaTelaAdmin();
            }
        });


        tela.getContentPane().removeAll();
        tela.getContentPane().add(painel, BorderLayout.CENTER);
        tela.revalidate();
        tela.repaint();

    }


    //////////////////////////////
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

