package br.com.fatechatclient.validadores;

import br.com.fatechatclient.contratos.Validador;

public class ValidadorTamanhoNome implements Validador<String> {

    @Override
    public boolean isValido(String nome) {

        return !((nome.length() > 12)
                || nome.isEmpty());
    }

}
