package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
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

    public static int continuarOperacao(Scanner scanner){
        boolean repetir = true;
        int continuar=0;
        do{
            System.out.println("Deseja continuar a operação? Digite:");
            System.out.println("1 - continuar");
            System.out.println("0 - Finalizar");
            try {
                continuar=Integer.parseInt(scanner.nextLine());
                if(continuar==0 || continuar==1){
                    repetir=false;
                }else{
                    repetir=true;
                }
            }catch(NumberFormatException e){
                //number format extends ilegal arguiment. é mais específico e só funciona se vier primeiro
                System.out.println("O valor digitado precisa ser um número");
            }catch (IllegalArgumentException e) {
                System.out.println("Número invalido! O valor precisa ser 1 ou 0");
            }
        }while(repetir==true);
        return continuar;
    }

    public static Funcionario cadastrarFuncionario(Scanner scanner){
        Funcionario novoFuncionario = null;
        int continuar=1;
        do{
            System.out.println("Escolha o cargo do funcionario");
            for(TipoFuncionario item:TipoFuncionario.geTipoFuncionarios()){
                System.out.println(item.getCodTipo()+" - "+item+" - "+item.getDescricao());
            }

            int codTipo;
            try {
                codTipo=Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("o valor precisa ser um número.");
                continuar=continuarOperacao(scanner); //1 para sim e 0 para não. este método já fez o tratamenot da exceção dentro e não precisa fazer try-catch aqui
                if(continuar==1){
                    continue;
                }else{
                    break;
                }
            }

            TipoFuncionario tipoFuncionario;
            try {
                tipoFuncionario=TipoFuncionario.consultaTipoPorCodigo(codTipo);
            } catch (IllegalArgumentException e) {
                System.out.println("Numero invalido. Escolha uma das opções disponíveis");
                continuar=continuarOperacao(scanner);
                if(continuar==1){
                    continue;
                }else{
                    break;
                }
            }

            String nome;
            System.out.println("Digite o nome do funcionário: ");
            try {
                nome=scanner.nextLine().trim();
                validarNome(nome);
            } catch (StringVaziaException e) {
                System.out.println(e.getMessage());
                continuar=continuarOperacao(scanner);
                if(continuar==1){
                    continue;
                }else{
                    break;
                }
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

    public static void lsitarEquipe(){
        int posicao=1;
        for(Map.Entry<String, Funcionario> dados:equipe.entrySet()){
            String chave = dados.getKey();
            Funcionario item=dados.getValue();
            System.out.println(posicao+" - "+item.toString());
            posicao++;
        }
    }

    public static String arrayEquipe(int escolha){
        String[] lista = new String[equipe.size()];
        int i=0;
        for(Map.Entry<String, Funcionario> dados:equipe.entrySet()){
            String chave = dados.getKey();
            lista[i]=chave;
            i++;
        }
        for(int x=0;x<lista.length;x++){
            System.out.println(x+" - "+lista[x]);
        }
        return lista[escolha];
    }

    

    
    public static void incluirFuncionarioNaEquipe(Funcionario funcioarioAtual){
        equipe.put(funcioarioAtual.getCodigo(), funcioarioAtual);

    }


    public static void removerFuncionario(Scanner scanner){
        int continuar=1;
        do{
            System.out.println("Selecione um método para exclusão:");
            System.out.println("1 - para escolher um funcionário na lista");
            System.out.println("2 - Para excluir um funcionário através do seu código");
            System.out.println("0 - voltar ao menu anterior");
            try {
                int metodo = Integer.parseInt(scanner.nextLine());
                if(metodo==1){
                    removerFuncionarionaLista(scanner);
                    lsitarEquipe();
                    continuar=1;
                }else if(metodo==2){
                    System.out.println("Digite o código do funcionario: ");
                    String key=scanner.nextLine();
                    removerFuncionarioPorKey(key, scanner);
                    lsitarEquipe();
                    continuar=1;
                }
                else if(metodo==0){
                    System.out.println("Cancelado pelo usuário");
                    continuar=0;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Numero invalido");
                continuar=continuarOperacao(scanner);
            }
        }while(continuar==1);
        
    }
    
    public static void removerFuncionarioPorKey(String key, Scanner scanner){
        if(!equipe.containsKey(key)){
            System.out.println("O código do funcionário esta errado ou já foi excluído do sistema");
        }else{
            Funcionario funcionarioExcluir = equipe.get(key);
            System.out.println("O seguinte funcioário será exclido do sistema: "+funcionarioExcluir.toString());
            int continuar=continuarOperacao(scanner);
            if(continuar==1){
                equipe.remove(key);
                System.out.println("Funcionário excluido com sucesso;");
            }
        }
    }
    
    public static void  removerFuncionarionaLista(Scanner scanner){
        int continuar=1;
        System.out.println("Selecione o funcionário que deseja excluir: ");
        lsitarEquipe();
        do{
            try {
                int escolha= Integer.parseInt(scanner.nextLine())-1;
                if(escolha<0){
                    System.out.println("o numero deve ser maior ou igual a zero");
                    continuar=continuarOperacao(scanner);
                }else{
                    String key=arrayEquipe(escolha);
                    removerFuncionarioPorKey(key, scanner);
                }
            } catch (NumberFormatException e) {
                //trata a exceção quando não é um numero
                System.out.println("O valor digitado deve ser um número");
                continuar=continuarOperacao(scanner);
            }catch(IllegalFormatException e){
                //trata a exceção quando não é uma opção válida na lista
                System.out.println("Numero invalido. escolha um número na lista");
                continuar=continuarOperacao(scanner);
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("numero invalido. O array não possui essa posição");
                continuar=continuarOperacao(scanner);
            }
        }while(continuar==1);
    }

    public static Funcionario selecionarFuncionario(Scanner scanner){
        int continuar=1;
        Funcionario funcionarioAual=null;
        
        do{
            System.out.println("Selecione o funcionário a partir do numero da lista abaixo: ");
            lsitarEquipe();
            try {
                int escolha=Integer.parseInt(scanner.nextLine())-1;
                if(escolha<0){
                    System.out.println("numero invalido. o valor digitado deve ser superior a zero 0");
                    continuar=1;
                }else{
                    String key = arrayEquipe(escolha);
                    funcionarioAual=equipe.get(key);
                    System.out.println("Funcionario selecionado: "+funcionarioAual.toString());
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Valor inválido. O array não possui esta posição");
                continuar=continuarOperacao(scanner);
            }catch(NumberFormatException e){
                System.out.println("O valor digitado deve ser um númeor.");
                continuar=continuarOperacao(scanner);
            }catch(IllegalArgumentException e){
                System.out.println("Valor invalido. escolha uma das opções na lista");
                continuar=continuarOperacao(scanner);
            }
        }while(continuar==1);
        return funcionarioAual;
    }

    public static void registrarPonto(Scanner scanner){
        Funcionario funcionarioAtual = selecionarFuncionario(scanner);
        funcionarioAtual.
        if(funcionarioAtual instanceof Estagiario || funcionarioAtual instanceof Gerente){

        }
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
                
                case 0:
                    System.out.println("Programa finalizado pelo usuário");
                    continuar=false;
                    break;
                
                case 1:
                    funcionarioAtual=cadastrarFuncionario(scanner);
                    if(funcionarioAtual==null){
                        System.out.println("Operacao cancelada. ");
                        break;
                    }else{
                        incluirFuncionarioNaEquipe(funcionarioAtual);
                        System.out.println("Novo funcionário incluido com sucesso");
                        break;
                    }    

                case 2:
                    removerFuncionario(scanner);
                    break;

                case 3:
                   lsitarEquipe();
                   break;

                case 4:
                    //registrar ponto funcionario
                    break;
            
                default:
                    System.out.println("Opção invalida. tente novamente");
                    continuar=true;
                    break;
            }

        }while(continuar==true);
      

    }
}
