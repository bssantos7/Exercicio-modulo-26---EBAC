package com.example;

import java.util.Scanner;

public class Estagiario extends Funcionario{

    public Estagiario(String nome, int digito, TipoFuncionario tipoFuncionario) {
        super(nome, digito, tipoFuncionario);
    }

   
    @Override 
    public void registrarPonto(Scanner scanner){
        System.out.println("aos Estagiários não é permitido registrar ponto!");
    }


    @Override
    public RegistroPonto registrarEntrada(RegistroPonto registroAtual, Scanner scanner, String codFuncionario) {
        System.out.println("Aos estágiários não é permitido bater ponto!");
        return null;
    }


    @Override
    public RegistroPonto registrarSaida(RegistroPonto registroAtual, Scanner scanner) {
        System.out.println("Aos estágiários não é permitido bater ponto!");
        return null;
    }

    

    

}
