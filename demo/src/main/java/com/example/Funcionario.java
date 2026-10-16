package com.example;

import java.util.HashMap;
import java.util.Random;

public abstract class Funcionario implements BaterPonto{
    private final String codigo;
    private final String nome;
    private HashMap <String, RegistroPonto> folhaPonto;

    public Funcionario(String nome, int digito, TipoFuncionario tipoFuncionario){
        this.nome=nome;
        Random random = new Random();
        this.codigo=random.nextInt(100)+"/"+tipoFuncionario+"-"+digito;
        this.folhaPonto=new HashMap<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Funcionario [codigo=" + codigo + ", nome=" + nome + "]";
    }

    



}
