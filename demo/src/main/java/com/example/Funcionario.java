package com.example;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Iterator;
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
            if (!registro.isCompleto()) { // no hashmap apenas o registro atual deve ter status false. todos os outros são true
                return registro;
            }
        }
        throw new RegistroIncompletoException("Erro no construtor e manipulação dos registros. não há registros incompletos na folha de ponto ");
    }


    
    //transferir método RegistarPonto para interface e implemnetar
    @Override 
    public void registrarPonto(Scanner scanner) throws OperacaoAbortadaException, RegistroIncompletoException,DateTimeParseException,ArithmeticException {
        boolean formatoOk=false;
        String inputDia=null;
        String inputHora=null;
        
        if(this.folhaPonto.isEmpty()){
            System.out.println("A folha de ponto do funcionário esta vazia. este será o primeiro registro");
            
            do{
                System.out.println("Digite a data do registro no formato DD/MM/AAAA");
                inputDia = scanner.nextLine().trim();
                System.out.println("Digite a hora no formato HH:MM");
                inputHora=scanner.nextLine().trim();
                formatoOk=RegistroPonto.confereFormato(inputDia, inputHora);
                if(formatoOk==false){
                    System.out.println("Formato da data e hora invalido. tente novamente");
                }
            }while(formatoOk==false);

            RegistroPonto novoPonto = new RegistroPonto(inputDia, inputHora, this.getCodigo());
            this.adicionarRegistroNaFolha(novoPonto);
        }else{ //folha de ponto não esta vazia
            try {
                RegistroPonto registroAtual=selecionarRegistroPonto(); // seleciona o registro que não está completo (false)
                System.out.println("Registro de Ponto Atual: "+registroAtual.toString());
                //cadastrar ponto de saída
                if(registroAtual.getTipoRegistroPonto()==TipoRegistroPonto.SAIDA){
                    registroAtual=this.registrarSaida(registroAtual, scanner);
                    //verifica as exceções e se estiver tudo ok atualiza a folha de ponto
                    if(registroAtual.getSaida()==null){
                       throw new OperacaoAbortadaException("Erro ao fazer o registro de ponto: hora saida é <= a hora de entrada ou a hora de saida> 22:00 ou formato hora invalido!");
                    }else{
                        this.adicionarRegistroNaFolha(registroAtual);//se for novo, cria, se chave já existe, atualiza
                        
                        System.out.println(registroAtual.toString());
                        RegistroPonto novoRegistroPonto= new RegistroPonto();
                        this.adicionarRegistroNaFolha(novoRegistroPonto);
                    }
                }else if(registroAtual.getTipoRegistroPonto()==TipoRegistroPonto.ENTRADA){
                    RegistroPonto comparativo = new RegistroPonto();
                    RegistroPonto registroPendente = registroAtual;
                    System.out.println("comparativo entrada");
                    System.out.println("padrão:"+comparativo.toString());
                    System.out.println("atual: "+registroAtual.toString());
                    registroAtual=this.registrarEntrada(registroAtual, scanner, this.getCodigo());
                    System.out.println("comparativo saida");
                    System.out.println("padrão:"+comparativo.toString());
                    System.out.println("atual: "+registroAtual.toString());
                    if(registroAtual.getCodigo()==null){
                        throw new OperacaoAbortadaException("Erro ao fazer o registro de ponto: hora de entrada anterior à 06 da manha ou formato data/hora invalido.");
                    }else if(this.folhaPonto.containsKey(registroAtual.getCodigo())){
                        throw new OperacaoAbortadaException("Já existe um registro de ponto para a data "+registroAtual.getDia());
                    }else{
                        removerRegistroPendente(registroPendente);
                        this.adicionarRegistroNaFolha(registroAtual);
                        System.out.println("Registro adicionado com sucesso!");
                        System.out.println(registroAtual.toString());
                        this.listarRegistroPonto();
                    }
                }
            } catch (RegistroIncompletoException e) {
                throw new RegistroIncompletoException(e.getMessage());
            }catch(DateTimeParseException e){
                throw new DateTimeParseException(e.getMessage(),e.getParsedString(),e.getErrorIndex(),e);
            }catch(DateTimeException e){
                throw new DateTimeException(e.getMessage());
            }catch(ArithmeticException e){
                throw new ArithmeticException(e.getMessage());
            }
        }
    }

    private void removerRegistroPendente(RegistroPonto registroPendente){
        Iterator<Map.Entry<String, RegistroPonto>> registros = this.folhaPonto.entrySet().iterator();
        while(registros.hasNext()){
            if(registros.next().getValue() == registroPendente){
                registros.remove();
                return;
            }
        }
        throw new IllegalStateException("O registro de ponto pendente não foi encontrado na folha.");
    }
}
