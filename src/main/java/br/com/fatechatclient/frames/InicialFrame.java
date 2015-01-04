package br.com.fatechatclient.frames;

import br.com.fatechatclient.execoes.ValidacaoException;
import br.com.fatechatclient.beans.Cliente;
import br.com.fatechatclient.contratos.Validador;
import br.com.fatechatclient.enuns.ValidadoresNomeEnum;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InicialFrame extends JFrame {

    private JTextField txtNome;
    private JFormattedTextField txtIP;
    private JButton btnConectar;

    public InicialFrame() {
        iniciarComponentes();
        iniciarAcoes();
    }

    private void iniciarAcoes() {
        btnConectar.addActionListener(e -> btnConectarClick());
    }

    private void iniciarComponentes() {

        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Conectar ao servidor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(276, 163);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(12, 23, 55, 15);
        getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(67, 21, 184, 19);
        getContentPane().add(txtNome);
        txtNome.setColumns(10);

        JLabel lblIp = new JLabel("IP:");
        lblIp.setBounds(12, 54, 45, 15);
        getContentPane().add(lblIp);

        txtIP = new JFormattedTextField();
        txtIP.setBounds(67, 52, 184, 19);
        getContentPane().add(txtIP);

        btnConectar = new JButton("Conectar");
        btnConectar.setBounds(46, 86, 184, 25);
        getContentPane().add(btnConectar);
    }

    private void btnConectarClick() {

        try {

            String nome = txtNome.getText();
            String ip = txtIP.getText();
            
            validar(nome);

            Cliente cliente = Cliente.getInstancia(ip, Cliente.PORTA_PADRAO, nome);
            ChatFrame janelaChat = new ChatFrame(cliente);
            dispose();
            
        }catch(ValidacaoException ex2){       
            JOptionPane.showMessageDialog(null, ex2.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro na inicialização do cliente: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    private void validar(String nome) throws ValidacaoException {
        ValidadoresNomeEnum[] validadores = ValidadoresNomeEnum.values();

        for (ValidadoresNomeEnum validadorEnum : validadores) {
            Validador<String> validador = validadorEnum.get();
            if (!validador.isValido(nome)) {
                throw new ValidacaoException(validador.getClass()
                        .getSimpleName() + " -  " + nome
                        + " Rejeitado na validaçao");
            }
        }
    }

}
