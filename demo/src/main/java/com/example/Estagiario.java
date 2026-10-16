package com.example;

public class Estagiario extends Funcionario{

    public Estagiario(String nome, int digito, TipoFuncionario tipoFuncionario) {
        super(nome, digito, tipoFuncionario);
    }

    @Override
    public void registrarEntrada() {
        System.out.println("aos Estagiários não é permitido registrar ponto!");
        
    }

    @Override
    public void registrarSaida() {
        // TODO Auto-generated method stub
        
    }

    

}
