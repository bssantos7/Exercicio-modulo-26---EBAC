package com.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    public static int digito=0;

    public static void setDigito(){
        App.digito++;
    }

    public static int getDigito(){
        return App.digito;
    }

    public static void cadastrarFuncionario(Scanner scanner){
        
        Funcionario novoFuncionario = null;
        ArrayList<TipoFuncionario> lista = new ArrayList<>();
        TipoFuncionario tipo = TipoFuncionario.ANALISTA;
        

        do{
            System.out.println("Digite o nome do funcionário: ");
            String nome = scanner.nextLine();
            
            System.out.println("Digite o tipo de funcionário que deseja cadastrar:");
            for(TipoFuncionario item: TipoFuncionario.geTipoFuncionarios()){
                System.out.println(item.getCodTipo()+" - "+item+" - "+item.getDescricao());
            }
            int tipo=Integer.parseInt(scanner.nextLine());
            try {
                TipoFuncionario tipoFuncionario=TipoFuncionario.consultaTipoPorCodigo(tipo);
                switch (tipoFuncionario) {
                    case ESTAGIARIO:
                        
                        break;
                    case TipoFuncionario.
                
                    default:
                        break;
                }
            } catch (IllegalArgumentException e) {
                // TODO: handle exception
            }

        }while(novoFuncionario==null);
        


        
        //Funcionario novofuFuncionario= new Funcionario(nome, App.getDigito());


    }

    public static void main( String[] args ){

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
            opcao=Integer.parseInt(scanner.nextLine());
            
            switch (opcao) {
                case 1:
                    cadastrarFuncionario(scanner);
                    break;
            
                default:
                    System.out.println("Programa finalizado pelo usuário");
                    continuar=false;
                    break;
            }

        }while(continuar==true);
      

    }
}
