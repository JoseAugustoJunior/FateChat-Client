package br.com.fatechatclient.enuns;

import br.com.fatechatclient.validadores.ValidadorNomeIgualServidor;
import br.com.fatechatclient.validadores.ValidadorTamanhoNome;
import br.com.fatechatclient.contratos.Validador;


public enum ValidadoresNomeEnum {
    
    NOME_IGUAL_DO_SERVIDOR {
        @Override
        public Validador get() {
            return new ValidadorNomeIgualServidor();
        }
    },
    TAMANHO_DO_NOME {
        @Override
        public Validador get() {
            return new ValidadorTamanhoNome();
        }
    };
      
    public abstract Validador get();
    
}
