package br.com.fatechatclient.validadores;

import br.com.fatechatclient.contratos.Validador;

public class ValidadorNomeIgualServidor implements Validador<String>{

    @Override
    public boolean isValido(String nome) {
        
        return !nome.equalsIgnoreCase("servidor");
        
    }
    
}
