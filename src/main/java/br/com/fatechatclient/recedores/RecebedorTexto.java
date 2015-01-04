package br.com.fatechatclient.recedores;

import br.com.fatechatclient.abstracoes.Recebedor;
import br.com.fatechatclient.beans.Cliente;
import br.com.fatechatclient.beans.Mensagem;
import br.com.fatechatclient.contratos.Conversor;
import br.com.fatechatclient.contratos.MostradorDeMensagens;
import br.com.fatechatclient.conversores.ConversorJson2Mensagem;
import java.io.IOException;
import javax.swing.JOptionPane;

public class RecebedorTexto extends Recebedor {

    private final MostradorDeMensagens janela;
    private final Conversor<String, Mensagem> j2m;

    public RecebedorTexto(Cliente cliente, MostradorDeMensagens janela)
            throws IOException {
        super(cliente);
        this.janela = janela;
        this.j2m = new ConversorJson2Mensagem();
    }

    @Override
    public void run() {

        try {
            String json;

            while ((json = cliente.getLeitor().nextLine()) != null) {
                Mensagem msg = j2m.converter(json);
                janela.setMensagem(msg);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Aconteceu um erro, favor reinicializar o chat.\n"
                    + "Não use 2 instancias do chat.\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
            System.exit(0);
        }
    }

}
