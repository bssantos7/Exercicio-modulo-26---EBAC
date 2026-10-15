package com.example;

import java.util.HashMap;
import java.util.Random;

public abstract class Funcionario implements BaterPonto{
    private final String codigo;
    private final String nome;
    private HashMap <String, RegistroPonto> folhaPonto;

    public Funcionario(String nome, int numero){
        this.nome=nome;
        Random random = new Random();
        this.codigo=random.nextInt(100)+"/"+nome+"-"+numero;
        this.folhaPonto=new HashMap<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }



}
