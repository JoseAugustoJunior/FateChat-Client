package br.com.fatechatclient.frames;

import br.com.fatechatclient.beans.Cliente;
import br.com.fatechatclient.beans.Mensagem;
import br.com.fatechatclient.contratos.MostradorDeMensagens;
import br.com.fatechatclient.enuns.TipoMensagem;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatFrame extends JFrame implements MostradorDeMensagens {

    private JTextField txtParaEnviar;
    private JScrollPane scrRecebido;
    private JButton btnEnviar;
    private JTextArea txaChat;

    private final Cliente cliente;

    public ChatFrame(Cliente cliente) throws Exception {
        this.cliente = cliente;
        this.cliente.escutarServidorCom(this);
        iniciarComponentes();
        definirEventos();
    }

    private void iniciarComponentes() {
        txaChat = new JTextArea();
        txaChat.setFont(new Font("serif", Font.PLAIN, 20));
        txaChat.setEditable(false);

        scrRecebido = new JScrollPane(txaChat);

        txtParaEnviar = new JTextField();
        txtParaEnviar.setFont(new Font("serif", Font.PLAIN, 20));
        txtParaEnviar.requestFocus();

        btnEnviar = new JButton("Enviar");
        btnEnviar.setFont(new Font("serif", Font.PLAIN, 26));
        btnEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Container pnlEnvio = new JPanel();
        pnlEnvio.setLayout(new BorderLayout());
        pnlEnvio.add(BorderLayout.CENTER, txtParaEnviar);
        pnlEnvio.add(BorderLayout.EAST, btnEnviar);

        getContentPane().add(BorderLayout.CENTER, scrRecebido);
        getContentPane().add(BorderLayout.SOUTH, pnlEnvio);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setTitle("Chat: " + cliente.getNomeCliente());
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void definirEventos() {
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnEnviarClick();
            }
        });

        txtParaEnviar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnEnviarClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void btnEnviarClick() {

        try {

            Mensagem msg = new Mensagem(TipoMensagem.TEXTO,
                    txtParaEnviar.getText(), cliente.getNomeCliente());

            cliente.enviarMensagem(msg);
            txtParaEnviar.setText("");
            txtParaEnviar.requestFocus();
        } catch (Exception ex) {
            System.err.println("Erro ao enviar: " + ex.getMessage());
        }

    }

    @Override
    public void setMensagem(Mensagem mensagem) {
        txaChat.append(mensagem.getNomeEmissor() + ": "
                + mensagem.getConteudo() + "\n");
        rolarBarraDeRolagemParaBaixo();
    }

    private void rolarBarraDeRolagemParaBaixo() {
        int valorMaximo = scrRecebido.getVerticalScrollBar().getMaximum();
        scrRecebido.getViewport().setViewPosition(new Point(0, valorMaximo));
    }

}
