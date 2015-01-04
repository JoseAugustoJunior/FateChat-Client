package br.com.fatechatclient.contratos;

public interface Conversor<T, E> {
    
    E converter(T obj) throws Exception;
    
}
