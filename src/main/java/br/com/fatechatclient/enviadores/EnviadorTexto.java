package br.com.fatechatclient.enviadores;

import br.com.fatechatclient.abstracoes.Enviador;
import br.com.fatechatclient.beans.Mensagem;
import java.io.IOException;
import java.io.PrintWriter;

public class EnviadorTexto extends Enviador{

    public EnviadorTexto(PrintWriter escritor, Mensagem mensagem)
            throws IOException {
        super(escritor, mensagem);
    }

    @Override
    public void enviar() throws IOException {
        escritor.println(mensagem.toJson());
        escritor.flush();
    }

}
