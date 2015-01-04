package br.com.fatechatclient.abstracoes;

import br.com.fatechatclient.beans.Cliente;
import java.io.IOException;

public abstract class Recebedor extends Thread{
    
    protected final Cliente cliente;

    public Recebedor(Cliente cliente) throws IOException {
        this.cliente = cliente;
       
    }
     
    
    @Override
    public abstract void run();
    
}
