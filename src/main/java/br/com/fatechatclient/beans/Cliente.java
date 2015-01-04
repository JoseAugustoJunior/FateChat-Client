package br.com.fatechatclient.beans;

import br.com.fatechatclient.abstracoes.Enviador;
import br.com.fatechatclient.abstracoes.Recebedor;
import br.com.fatechatclient.contratos.MostradorDeMensagens;
import br.com.fatechatclient.enuns.TipoMensagem;
import br.com.fatechatclient.enviadores.EnviadorNome;
import br.com.fatechatclient.enviadores.EnviadorTexto;
import br.com.fatechatclient.recedores.RecebedorTexto;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private static Cliente clienteInstaciado;

    public static final int PORTA_PADRAO = 5000;
    public static final String LOCALHOST = "127.0.0.1";

    private Recebedor recebedorDeDados;
    private final PrintWriter escritor;
    private final Scanner leitor;
    private final String nomeCliente;
    private int quantidadeMensagensEnviadas;

    private Cliente(Socket socket, String nomeCliente) throws IOException {
        
        this.nomeCliente = nomeCliente;
        this.quantidadeMensagensEnviadas = 0;
        
        this.escritor = new PrintWriter(socket.getOutputStream());
        this.leitor = new Scanner(socket.getInputStream());
       
    }

    public PrintWriter getEscritor() {
        return escritor;
    }

    public Scanner getLeitor() {
        return leitor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getQuantidadeMensagensEnviadas() {
        return quantidadeMensagensEnviadas;
    }

    public void enviarMensagem(Mensagem mensagem) throws Exception {

        if (!mensagem.getConteudo().isEmpty()) {
            Enviador enviador = 
                    new EnviadorTexto(escritor, mensagem);
            enviador.enviar();
            quantidadeMensagensEnviadas++;
        }
    }

    public void escutarServidorCom(MostradorDeMensagens janela) throws Exception {

        if (recebedorDeDados == null) {
            recebedorDeDados = new RecebedorTexto(this, janela);
            recebedorDeDados.start();
        }
    }

    public void EnviarNomeAoServidor() throws Exception {
        
        Mensagem msg = new Mensagem(TipoMensagem.NOME, nomeCliente, nomeCliente);
        
        Enviador enviador = new EnviadorNome(escritor, msg);
        enviador.enviar();

    }

    public static Cliente getInstancia(String ip, int porta, String nomeCliente)
            throws Exception {

        if (clienteInstaciado != null) {
            throw new RuntimeException("Cliente já está configurado ...");
        }

        Socket socket = new Socket(ip, porta);
        clienteInstaciado = new Cliente(socket, nomeCliente);
        clienteInstaciado.EnviarNomeAoServidor();

        return clienteInstaciado;
    }

}
