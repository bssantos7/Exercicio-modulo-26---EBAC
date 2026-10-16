package com.example;

public enum TipoRegistroPonto {

    //as constantes são obrigatoriamente public static e final. não precisa escrever
    ENTRADA(1, "Registro de entrada para um novo dia de trabalho"),
    SAIDA(2, "Registro de saida para finalizar dia de trabalho");

    private final int valor;
    private final String descricao;

    private TipoRegistroPonto(int valor, String descricao){
        this.valor=valor;
        this.descricao=descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    
}
