package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    public static int digito=0;
    public static HashMap<String, Funcionario> equipe = new HashMap<>();

    public static void aumentaDigito(){
        App.digito++;
    }

    public static void reduzDigito(){
        App.digito--;
    }

    public static int getDigito(){
        return App.digito;
    }

    public static void validarNome(String nome) throws StringVaziaException{
        if(nome.isBlank()||nome==null||nome.equals("")){
            throw new StringVaziaException("o nome não pode ser vazio");
        }
    }

    public static Funcionario cadastrarFuncionario(Scanner scanner){
        Funcionario novoFuncionario = null;
        do{
            System.out.println("Escolha o cargo do funcionario");
            for(TipoFuncionario item:TipoFuncionario.geTipoFuncionarios()){
                System.out.println(item.getCodTipo()+" - "+item+" - "+item.getDescricao());
            }

            int codTipo;
            try {
                codTipo=Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("o valor precisa ser um número. tente novamente");
                continue;
            }

            TipoFuncionario tipoFuncionario;
            try {
                tipoFuncionario=TipoFuncionario.consultaTipoPorCodigo(codTipo);
            } catch (IllegalArgumentException e) {
                System.out.println("Numero invalido. tente novamente");
                continue;
            }

            String nome;
            System.out.println("Digite o nome do funcionário: ");
            try {
                nome=scanner.nextLine().trim();
                validarNome(nome);
            } catch (StringVaziaException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (tipoFuncionario) {
                case ESTAGIARIO:
                    aumentaDigito();
                    novoFuncionario=new Estagiario(nome, getDigito(), tipoFuncionario);
                    System.out.println("Funcionário criado com sucesso. Código: "+novoFuncionario.getCodigo());
                    break;
                case ASSISTENTE:
                    aumentaDigito();
                    novoFuncionario=new Assistente(nome, getDigito(), tipoFuncionario);
                    System.out.println("Funcionário criado com sucesso. Código: "+novoFuncionario.getCodigo());
                    break;
                case ANALISTA:
                    aumentaDigito();
                    novoFuncionario=new Analista(nome, getDigito(), tipoFuncionario);
                    System.out.println("Funcionário criado com sucesso. Código: "+novoFuncionario.getCodigo());
                    break;
                case COORDENADOR:
                    aumentaDigito();
                    novoFuncionario=new Coordenador(nome, getDigito(), tipoFuncionario);
                    System.out.println("Funcionário criado com sucesso. Código: "+novoFuncionario.getCodigo());
                    break;
                case GERENTE:
                    aumentaDigito();
                    novoFuncionario=new Gerente(nome, getDigito(), tipoFuncionario);
                    System.out.println("Funcionário criado com sucesso. Código: "+novoFuncionario.getCodigo());
                    break;
                default:
                    System.out.println("Opção invalida. Tente Novamente");
                    novoFuncionario=null;
                    break;
            }
        }while(novoFuncionario==null);
        return novoFuncionario;
    }

    public static void incluirNovoFuncionario(){

    }
    

    public static void main( String[] args ){
        
        Funcionario funcionarioAtual=null;
        Scanner scanner  = new Scanner(System.in);
        boolean continuar = true;
        int opcao=9;
        

        do{
            System.out.println("PROGRMA GERENCIADOR DE PONTO");
            System.out.println("============================");
            System.out.println("Digite uma das opções:");
            System.out.println("1 - Cadastrar Novo Funcionário");
            System.out.println("2 - Excluir Funcionário");
            System.out.println("3 - Listar registros de Funcionário");
            System.out.println("4 - Registrar Ponto de Funcionário");
            System.out.println("0 - Sair do Programa");
            
            try {
                opcao=Integer.parseInt(scanner.nextLine().trim());    
            } catch (NumberFormatException e) {
                System.out.println("O valor digitado precisa ser um numero. tente novamente");
                opcao=9;
                continuar=true;
                continue;
            }
            
            switch (opcao) {
                case 1:
                    funcionarioAtual=cadastrarFuncionario(scanner);
                    equipe.put(funcionarioAtual.getCodigo(), funcionarioAtual);
                    System.out.println("Novo funcionário incluido com sucesso");
                    break;

                case 2:
                    

                case 3:
                    for(Map.Entry<String, Funcionario> dados:equipe.entrySet()){
                        String chave = dados.getKey();
                        Funcionario item=dados.getValue();
                        System.out.println(item.toString());
                    }
            
                default:
                    System.out.println("Opção invalida. tente novamente");
                    continuar=true;
                    break;
            }

        }while(continuar==true);
      

    }
}
