package br.com.fatechatclient.abstracoes;

import br.com.fatechatclient.beans.Mensagem;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Enviador {
    
    protected PrintWriter escritor;
    protected Mensagem mensagem;

    public Enviador(PrintWriter escritor, Mensagem mensagem) throws IOException {
        this.escritor = escritor;
        this.mensagem = mensagem;
    }
    
    
    public abstract void enviar() throws Exception;
    
    
}
