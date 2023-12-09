package org.example.view;

import org.example.Dados;
import org.example.Pedido;
import org.example.produtos.Produto;
import org.example.exceptions.UsuarioNaoEncontradoException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import org.example.produtos.Bebida;
import org.example.produtos.Hamburguer;
import org.example.produtos.Produto;
import org.example.produtos.Sorvete;

public class InterfaceGrafica extends JFrame {

    private JFrame tela;
    private final int WIDTH = 1050;
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
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
        tela.setLocationRelativeTo( null );
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
                    if (email.equals(""))
                        if (senha.equals(""))
                            throw new Exception("Digite seu e-mail e sua senha!");
                        else
                            throw new Exception("Digite seu e-mail");
                    if (senha.equals(""))
                        throw new Exception("Digite sua senha!");

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

                } catch (Exception ex) {
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
                    if (campoNome.getText().equals("") || campoEmail.getText().equals("") || campoSenha.getText().equals("") || campoCPF.getText().equals("")|| campoEndereco.getText().equals(""))
                        throw new Exception("Algum campo obrigatorio nao está preenchido, preencha!");
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
        JButton botaoIniciarPedido = new JButton("Iniciar Pedido");
        
        botaoIniciarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaTelaFazerPedido();
            }
        });
        

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
        painelPai.add(botaoIniciarPedido, BorderLayout.NORTH);
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
            JButton botaoLimparPedidos = new JButton("X");
            botaoLimparPedidos.getFont().deriveFont(Font.BOLD);
            botaoLimparPedidos.setForeground(Color.RED);
            botaoVisualizar.setPreferredSize(new Dimension(140, 25));
            botaoMarcarEntregue.setPreferredSize(new Dimension(140, 25));
            botaoCancelarPedido.setPreferredSize(new Dimension(140, 25));
            botaoLimparPedidos.setPreferredSize(new Dimension(50,25));
            painelBotoes.add(botaoVisualizar, BorderLayout.WEST);
            painelBotoes.add(botaoCancelarPedido, BorderLayout.WEST);
            painelBotoes.add(botaoMarcarEntregue, BorderLayout.WEST);
            painelBotoes.add(botaoLimparPedidos, BorderLayout.WEST);

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
                    pedidoAux = null;
                }else{
                    JOptionPane.showMessageDialog(tela, "Selecione um pedido");
                }
            }
        });

        botaoLimparPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(tela,
                        "Tem certeza de que deseja excluir todos os pedidos?",
                        "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    Dados.limparPedidos();
                    desenhaTelaAdmin();
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

    public void desenhaTelaClientes() {
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Bem vindo ao Orientado a Xtudo!"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JTable tabelaPedidos = criarTabelaClientes();
        tabelaPedidos.setPreferredSize(new Dimension(WIDTH -50, HEIGHT/2));
        JScrollPane scrollPane = new JScrollPane(tabelaPedidos);
        scrollPane.setPreferredSize(new Dimension(WIDTH -50, HEIGHT/2));
        painel.add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton botao1 = new JButton("Iniciar novo pedido");
        JButton botao2 = new JButton("Logout");

        painelBotoes.add(botao1);
        painelBotoes.add(botao2);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        botao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaTelaFazerPedido();
            }
        });

        botao2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dados.logout();
                desenhaLogin();
            }
        });
        
        
        tabelaPedidos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Obtém a linha selecionada
                    int linhaSelecionada = tabelaPedidos.getSelectedRow();
                    System.err.println(linhaSelecionada);
                }
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
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));


        JPanel painelContainer =  new JPanel();
        painelContainer.setPreferredSize(new Dimension(WIDTH, 400));
        painelContainer.setBorder(BorderFactory.createEtchedBorder());

        painelContainer.setLayout(new BoxLayout(painelContainer, BoxLayout.X_AXIS));

        //SORVETES E BEBIDAS
        JPanel painelSorvetesBebidas = new JPanel();
        painelSorvetesBebidas.setBorder(BorderFactory.createTitledBorder("Bebidas e sobremesas"));
        painelSorvetesBebidas.setPreferredSize(new Dimension(WIDTH/3, 400));
        painelSorvetesBebidas.setLayout(new BoxLayout(painelSorvetesBebidas, BoxLayout.Y_AXIS));

        //BEBIDAS
        JPanel painelBebidas = new JPanel();
        painelBebidas.setBorder(BorderFactory.createTitledBorder("Bebidas"));
        painelBebidas.setPreferredSize(new Dimension(WIDTH/3, 200));
        painelBebidas.setLayout(new BoxLayout(painelBebidas, BoxLayout.Y_AXIS));

        JLabel rotuloBebida = new JLabel("Selecione a bebida:");
        JComboBox comboBoxBebida = new JComboBox<>(Bebida.bebidas);
        comboBoxBebida.setMaximumSize(new Dimension( WIDTH/3, 25));

        JLabel rotuloTamanhoBebida = new JLabel("Selecione o tamanho do seu copo: (ml)");
        JComboBox comboBoxTamanhoBebida = new JComboBox<>(Bebida.copos);
        comboBoxTamanhoBebida.setMaximumSize(new Dimension( WIDTH/3, 25));

        JPanel painelBotaoBebidas = new JPanel();
        painelBotaoBebidas.setPreferredSize(new Dimension(WIDTH/3, 50));
        painelBotaoBebidas.setLayout(new BoxLayout(painelBotaoBebidas, BoxLayout.X_AXIS));

        JButton botaoPrecoBebida = new JButton("Valor");
        JButton botaoIncluirBebida = new JButton("Adicionar bebida");

        painelBotaoBebidas.add(botaoPrecoBebida);
        painelBotaoBebidas.add(botaoIncluirBebida);

        painelBebidas.add(rotuloBebida);
        painelBebidas.add(comboBoxBebida);
        painelBebidas.add(rotuloTamanhoBebida);
        painelBebidas.add(comboBoxTamanhoBebida);
        painelBebidas.add(painelBotaoBebidas);

        botaoPrecoBebida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bebida bebida = new Bebida(
                        comboBoxBebida.getSelectedItem().toString(),
                        comboBoxTamanhoBebida.getSelectedItem().toString()
                );
                JOptionPane.showMessageDialog(tela, "R$" + bebida.calcularValorItem());
            }
        });
        botaoIncluirBebida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bebida bebida = new Bebida(
                        comboBoxBebida.getSelectedItem().toString(),
                        comboBoxTamanhoBebida.getSelectedItem().toString()
                );
                bebida.setValor(bebida.calcularValorItem());
                listaProdutosAux.add(bebida);
                desenhaTelaFazerPedido();
            }
        });

        //Sorvete
        JPanel painelSorvete = new JPanel();

        painelSorvete.setLayout(new BoxLayout(painelSorvete, BoxLayout.Y_AXIS));
        painelSorvete.setPreferredSize(new Dimension(WIDTH/3, 200));
        painelSorvete.setBorder(BorderFactory.createTitledBorder("Sorvetes"));


        JLabel rotuloTamanho = new JLabel("Selecione o tamanho:");
        JComboBox comboBoxTamanho = new JComboBox<>(Sorvete.tamanhos);
        comboBoxTamanho.setMaximumSize(new Dimension( WIDTH/3, 25));

        JLabel rotuloCobertura = new JLabel("Selecione a cobertura:");
        JComboBox comboBoxCobertura = new JComboBox<>(Sorvete.coberturas);
        comboBoxCobertura.setMaximumSize(new Dimension( WIDTH/3, 25));

        JPanel painelBotaoSorvete = new JPanel();
        painelBotaoSorvete.setLayout(new BoxLayout(painelBotaoSorvete, BoxLayout.X_AXIS));
        painelBotaoSorvete.setPreferredSize(new Dimension(WIDTH/3, 50));

        JButton botaoPreco = new JButton("Valor");
        JButton botaoIncluirSorvete = new JButton("Adicionar sobremesa");

        painelBotaoSorvete.add(botaoPreco);
        painelBotaoSorvete.add(botaoIncluirSorvete);


        painelSorvete.add(rotuloTamanho);
        painelSorvete.add(comboBoxTamanho);
        painelSorvete.add(rotuloCobertura);
        painelSorvete.add(comboBoxCobertura);
        painelSorvete.add(painelBotaoSorvete);

        botaoPreco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorvete sorvete = new Sorvete(
                        comboBoxTamanho.getSelectedItem().toString(),
                        comboBoxCobertura.getSelectedItem().toString()
                );
                JOptionPane.showMessageDialog(tela, "R$" + sorvete.calcularValorItem());
            }
        });
        botaoIncluirSorvete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorvete sorvete = new Sorvete(
                        comboBoxTamanho.getSelectedItem().toString(),
                        comboBoxCobertura.getSelectedItem().toString()
                );
                sorvete.setValor(sorvete.calcularValorItem());

                listaProdutosAux.add(sorvete);
                desenhaTelaFazerPedido();
            }

        });
        painelSorvetesBebidas.add(painelBebidas, BorderLayout.CENTER);
        painelSorvetesBebidas.add(painelSorvete, BorderLayout.CENTER);


        //HAMBURGUER
        JPanel painelHamburguer = new JPanel();
        painelHamburguer.setLayout(new BoxLayout(painelHamburguer, BoxLayout.Y_AXIS));
        painelHamburguer.setPreferredSize(new Dimension(WIDTH/3, 400));
        painelHamburguer.setMaximumSize(new Dimension(WIDTH/3, 400));

        JLabel rotuloSaladas = new JLabel("Quantas porções de salada?");
        JSpinner spinnerSaladas = new JSpinner();
        spinnerSaladas.setValue(0);
        spinnerSaladas.setMaximumSize(new Dimension( WIDTH/3, 25));

        JLabel rotuloCarnes = new JLabel("Quantas carnes?");
        JSpinner spinnerCarnes = new JSpinner();
        spinnerCarnes.setValue(0);
        spinnerCarnes.setMaximumSize(new Dimension( WIDTH/3, 25));

        JLabel rotuloQueijos = new JLabel("Quantas porções de queijo?");
        JSpinner spinnerQueijos = new JSpinner();
        spinnerQueijos.setValue(0);
        spinnerQueijos.setMaximumSize(new Dimension( WIDTH/3, 25));

        JLabel rotuloPresunto = new JLabel("Quantas porções de Presunto?");
        JSpinner spinnerPresunto = new JSpinner();
        spinnerPresunto.setValue(0);
        spinnerPresunto.setMaximumSize(new Dimension( WIDTH/3, 25));

        JLabel rotuloBacon = new JLabel("Quantas porções de Bacon?");
        JSpinner spinnerBacon = new JSpinner();
        spinnerBacon.setValue(0);
        spinnerBacon.setMaximumSize(new Dimension( WIDTH/3, 25));

        JLabel rotuloOvos = new JLabel("Quantos ovos?");
        JSpinner spinnerOvos = new JSpinner();
        spinnerOvos.setValue(0);
        spinnerOvos.setMaximumSize(new Dimension( WIDTH/3, 25));

        JPanel painelBotaoHamburguer = new JPanel();
        painelBotaoHamburguer.setLayout(new BoxLayout(painelBotaoHamburguer, BoxLayout.X_AXIS));
        painelBotaoHamburguer.setPreferredSize(new Dimension(WIDTH/3, 50));

        JButton botaoPrecosHamburguer = new JButton("Valor");
        JButton botaoIncluirHamburguer = new JButton("Adicionar hamburguer");

        painelBotaoHamburguer.add(botaoPrecosHamburguer);
        painelBotaoHamburguer.add(botaoIncluirHamburguer);

        painelHamburguer.add(rotuloSaladas);
        painelHamburguer.add(spinnerSaladas);
        painelHamburguer.add(rotuloCarnes);
        painelHamburguer.add(spinnerCarnes);
        painelHamburguer.add(rotuloQueijos);
        painelHamburguer.add(spinnerQueijos);
        painelHamburguer.add(rotuloPresunto);
        painelHamburguer.add(spinnerPresunto);
        painelHamburguer.add(rotuloBacon);
        painelHamburguer.add(spinnerBacon);
        painelHamburguer.add(rotuloOvos);
        painelHamburguer.add(spinnerOvos);
        painelHamburguer.add(painelBotaoHamburguer);

        botaoPrecosHamburguer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hamburguer hamburguer = new Hamburguer(
                        (int) spinnerSaladas.getValue(),
                        (int) spinnerCarnes.getValue(),
                        (int) spinnerQueijos.getValue(),
                        (int) spinnerPresunto.getValue(),
                        (int) spinnerBacon.getValue(),
                        (int) spinnerOvos.getValue()
                );
                JOptionPane.showMessageDialog(tela, "valor: R$" + hamburguer.calcularValorItem());

            }
        });
        botaoIncluirHamburguer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hamburguer hamburguer = new Hamburguer(
                        (int) spinnerSaladas.getValue(),
                        (int) spinnerCarnes.getValue(),
                        (int) spinnerQueijos.getValue(),
                        (int) spinnerPresunto.getValue(),
                        (int) spinnerBacon.getValue(),
                        (int) spinnerOvos.getValue()
                );
                hamburguer.setValor(hamburguer.calcularValorItem());
                listaProdutosAux.add(hamburguer);
                desenhaTelaFazerPedido();
            }
        });

        //Lista de itens do pedido

        JPanel painelItensPedido = new JPanel();
        painelItensPedido.setLayout(new BoxLayout(painelItensPedido, BoxLayout.Y_AXIS));
        painelItensPedido.setPreferredSize(new Dimension(WIDTH/3, 400));
        painelItensPedido.setMaximumSize(new Dimension(WIDTH/3, 400));


        DefaultListModel<Produto> listModel = new DefaultListModel<>();
        if(listaProdutosAux.size() > 0) {
            for (Produto p : listaProdutosAux) {
                listModel.addElement(p);
            }
        }

        JList listaPedidos = new JList<>(listModel);
        listaPedidos.setPreferredSize(new Dimension(WIDTH/3, 350));
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));
        painelBotoes.setPreferredSize(new Dimension(WIDTH/3, 25));
        JButton botaoRemoverItem = new JButton("Remover item");
        botaoRemoverItem.setPreferredSize(new Dimension(200, 25));
        JButton botaoLimparCarrinho = new JButton("Limpar Carrinho");
        botaoRemoverItem.setPreferredSize(new Dimension(200, 25));
        painelBotoes.add(botaoRemoverItem, BorderLayout.WEST);
        painelBotoes.add(botaoLimparCarrinho, BorderLayout.WEST);

        botaoLimparCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(tela,
                        "Tem certeza de que deseja limpar o carrinho?",
                        "Confirmação", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    for(int i = listaProdutosAux.size()-1; i >= 0; i--){
                        listaProdutosAux.remove(i);
                    }
                    System.out.println("Carrinho limpo!");
                    desenhaTelaFazerPedido();
                }
            }
        });

        botaoRemoverItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(produtoAux != null) {
                    for(Produto p : listaProdutosAux){
                        if(produtoAux.equals(p)){
                            listaProdutosAux.remove(p);
                            desenhaTelaFazerPedido();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(tela, "Selecione um pedido para remover");
                }
            }
        });

        painelItensPedido.add(new JScrollPane(listaPedidos), BorderLayout.CENTER);
        painelItensPedido.add(painelBotoes, BorderLayout.SOUTH);
        listaPedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList<?> list = (JList<?>) evt.getSource();
                if (evt.getClickCount() == 1) {
                    int index = list.locationToIndex(evt.getPoint());
                    Produto produtoSelecionado = listModel.getElementAt(index);
                    selecionaProduto(produtoSelecionado);
                }
            }
        });

        JPanel painelBotoesSubmit = new JPanel();
        painelBotoesSubmit.setLayout(new BoxLayout(painelBotoesSubmit, BoxLayout.X_AXIS));
        painelBotoesSubmit.setPreferredSize(new Dimension(WIDTH, 50));
        painelBotoesSubmit.setMaximumSize(new Dimension(WIDTH, 50));
        JButton botaoFazerPedido = new JButton("Fazer pedido");
        JButton botaoVoltar = new JButton("Voltar");

        botaoFazerPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listaProdutosAux.isEmpty()) {
                    Dados.cadastrarPedido(new Pedido(Dados.getUsuarioLogado(), listaProdutosAux));
                    if(Dados.getUsuarioLogado().isAdmin())
                        desenhaTelaAdmin();
                    else{
                        desenhaTelaClientes();
                    }
                }else{
                    JOptionPane.showMessageDialog(tela, "Adicione ao menos um item ao carrinho.");
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Dados.getUsuarioLogado().isAdmin())
                    desenhaTelaAdmin();
                else
                    desenhaTelaClientes();

                System.out.println("Seu carrinho de compras continuará salvo :D");
            }
        });

        painelBotoesSubmit.add(botaoFazerPedido, BorderLayout.CENTER);
        painelBotoesSubmit.add(botaoVoltar, BorderLayout.CENTER);


        painelContainer.add(painelSorvetesBebidas, BorderLayout.CENTER);
        painelContainer.add(painelHamburguer, BorderLayout.CENTER);
        painelContainer.add(painelItensPedido, BorderLayout.CENTER);

        painel.add(painelContainer, BorderLayout.CENTER);
        painel.add(painelBotoesSubmit, BorderLayout.CENTER);


        tela.getContentPane().removeAll();
        tela.getContentPane().add(painel, BorderLayout.CENTER);
        tela.revalidate();
        tela.repaint();
    }


    private JTable criarTabelaClientes() {
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
        return tabela;
    }

}
