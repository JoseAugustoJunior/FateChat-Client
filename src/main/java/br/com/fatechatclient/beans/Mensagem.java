package br.com.fatechatclient.beans;

import br.com.fatechatclient.enuns.TipoMensagem;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Mensagem {
    
    @SerializedName("TIPO")
    private TipoMensagem tipo;
    @SerializedName("CONTEUDO")
    private String conteudo;
    @SerializedName("NOME_EMISSOR")
    private String nomeEmissor;

    public Mensagem(TipoMensagem tipo, String conteudo, String nomeEmissor) {
        this.tipo = tipo;
        this.conteudo = conteudo;
        this.nomeEmissor = nomeEmissor;
    }

    public TipoMensagem getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensagem tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getNomeEmissor() {
        return nomeEmissor;
    }

    public void setNomeEmissor(String nomeEmissor) {
        this.nomeEmissor = nomeEmissor;
    }
    
    
    public String toJson(){      
        
        Gson gson = new Gson();  
        String json = gson.toJson(this);
        
        return json;
    }

    
    
}
