package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

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
        folhaPonto.s
    }

    @Override
    public String toString() {
        return "Funcionario [codigo=" + codigo + ", nome=" + nome + "]";
    }

    public void adicionarRegistroNaFolha(RegistroPonto novPonto){
        this.folhaPonto.put(novPonto.getCodigo(), novPonto);
    }
    
    public void registrarPonto(Scanner scanner){
        boolean formatoOk=false;
        String inputDia=null;
        String inputHora=null;
        if(folhaPonto.isEmpty()){
            while (formatoOk==false) {
                System.out.println("A folha de ponto do funcionário esta vazia. este será o primeiro registro");
                System.out.println("Digite a data do registro no formato DD/MM/AAAA");
                inputDia = scanner.nextLine().trim();
                System.out.println("Digite a hora no formato HH:MM");
                inputHora=scanner.nextLine().trim();
                if(RegistroPonto.confereFormatoDia(inputDia)==true&& RegistroPonto.confereFormatoHora(inputHora)==true){
                    formatoOk=true;
                }else{
                    System.out.println("Formado do dia e/ou hora está inválido. tente novamente");
                    formatoOk=false;
                }
            }

            RegistroPonto novoPonto = new RegistroPonto(inputDia, inputHora, this.getCodigo());
            this.adicionarRegistroNaFolha(novoPonto);
        }else{
            //faz se a folha de ponto não estiver vazia
        }
    }

    



}
