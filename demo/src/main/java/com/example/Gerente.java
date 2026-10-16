package com.example;

public final class Gerente extends Funcionario{
    
    public Gerente(String nome, int digito, TipoFuncionario tipoFuncionario){
        super(nome, digito, tipoFuncionario);
    }

    @Override
    public void registrarEntrada() {
        // gerente não bate ponto
        System.out.println("O cargo de gerente não é permitido registrar entrada no sistema de ponto");
        
    }

    @Override
    public void registrarSaida() {
        // gerente não bate ponto
        System.out.println("O cargo de gerente não é permitido registrar saida no sistema de ponto");
        
    }

    

}
