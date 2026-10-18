package com.example;

import java.util.Scanner;

public final class Gerente extends Funcionario{
    
    public Gerente(String nome, int digito, TipoFuncionario tipoFuncionario){
        super(nome, digito, tipoFuncionario);
    }

    @Override 
     public void registrarPonto(Scanner scanner){
        System.out.println("O cargo de gerente não é permitido registrar ponto no sistema de ponto");
     }

    @Override
    public RegistroPonto registrarEntrada(RegistroPonto registroAtual) {
        // gerente não bate ponto
        System.out.println("O cargo de gerente não é permitido registrar entrada no sistema de ponto");
        return null;
        
    }

    @Override
    public RegistroPonto registrarSaida(RegistroPonto registroAtual) {
        // gerente não bate ponto
        System.out.println("O cargo de gerente não é permitido registrar saida no sistema de ponto");
        return null;
        
    }

    

}
