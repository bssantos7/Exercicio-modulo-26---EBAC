package com.example;

import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    static int digito=0;

    static void setDigito(){
        App.digito++;
    }

    static int getDigito(){
        return App.digito;
    }

    static Funcionario cadastrarFuncionario(Scanner scanner){
        System.out.println("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();
        Funcionario novofuFuncionario= new Funcionario(nome, App.getDigito());


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
                    
                    break;
            
                default:
                    break;
            }

        }while(continuar==true);
      

    }
}
