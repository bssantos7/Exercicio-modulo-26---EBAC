package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Map;
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
        
    }

    @Override
    public String toString() {
        return "Funcionario [codigo=" + codigo + ", nome=" + nome + "]";
    }

    public void adicionarRegistroNaFolha(RegistroPonto novPonto){
        if(this.folhaPonto.containsKey(novPonto.getCodigo())){
            this.folhaPonto.put(novPonto.getCodigo(), novPonto);
            System.out.println("Registro de Ponto atualizado com sucesso!");    
        }else{
            this.folhaPonto.put(novPonto.getCodigo(), novPonto);
            System.out.println("Registro de ponto criado com sucesso!");
        }    
    }

    public void listarRegistroPonto(){
        int posicao=1;
        if(this.folhaPonto.isEmpty()){
            System.out.println("Não há registro de pontos para este funcionário!");
        }else{
            for(Map.Entry<String, RegistroPonto> dados: this.folhaPonto.entrySet()){
                System.out.println(posicao+" - "+dados.toString());
                posicao++;
            }
        }
    }
    
    public RegistroPonto selecionarRegistroPonto(String key){
        RegistroPonto registroAtual=null;
        if(this.folhaPonto.containsKey(key)){
             registroAtual = this.folhaPonto.get(key);
        }else{
            System.out.println("O código"+key+" do registro não consta na folha de ponto deste funcionário");
        }
        return registroAtual;
    }

    public RegistroPonto selecionarRegistroPonto() throws RegistroIncompletoException{
        
        for(Map.Entry<String, RegistroPonto> dados: this.folhaPonto.entrySet()){
            String codigo=dados.getKey();
            RegistroPonto registro=dados.getValue();
            if (!registro.isCompleto()) {
                return registro;
            }
        }
        throw new RegistroIncompletoException("Erro no construtor e manipulação dos registros. não há registros incompletos na folha de ponto ");
    }


    
    //transferir método RegistarPonto para interface e implemnetar
    @Override 
    public void registrarPonto(Scanner scanner){
        boolean formatoOk=false;
        String inputDia=null;
        String inputHora=null;
        
        if(this.folhaPonto.isEmpty()){
            while (formatoOk==false) {
                System.out.println("A folha de ponto do funcionário esta vazia. este será o primeiro registro");
                System.out.println("Digite a data do registro no formato DD/MM/AAAA");
                inputDia = scanner.nextLine().trim();
                System.out.println("Digite a hora no formato HH:MM");
                inputHora=scanner.nextLine().trim();
                formatoOk=RegistroPonto.confereFormato(inputDia, inputHora);
                if(formatoOk=false){
                    System.out.println("Formato da data e hora invalido. tente novamente");
                }
            }

            RegistroPonto novoPonto = new RegistroPonto(inputDia, inputHora, this.getCodigo());
            this.adicionarRegistroNaFolha(novoPonto);
        }else{
            //faz se a folha de ponto não estiver vazia
        }
    }

    



}
