
package br.com.fatechatclient.contratos;

public interface Validador<E> {
    
    boolean isValido(E obj);
    
}
