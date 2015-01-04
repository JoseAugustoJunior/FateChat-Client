package br.com.fatechatclient.conversores;

import br.com.fatechatclient.beans.Mensagem;
import br.com.fatechatclient.contratos.Conversor;
import com.google.gson.Gson;

public class ConversorJson2Mensagem implements Conversor<String, Mensagem>{

    @Override
    public Mensagem converter(String json) throws Exception {
        
        Gson gson = new Gson();
        
        Mensagem msg = gson.fromJson(json, Mensagem.class);
        
        return msg;
    }
    
    
    
}
