package com.example;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Scanner;

public class Assistente extends Funcionario {

    public Assistente(String nome, int digito, TipoFuncionario tipoFuncionario) {
        super(nome, digito, tipoFuncionario);
    }

    //deve considerar 1 hora de almoço
    //máximo de 3 horas extras por dia
    //não pode ter horario de saida anterior ou igual ao de entrada
    //não pode ter horário de entrada antes de 06 da manha
    // não pode ter horario de saída depois das 22 horas;

 
    @Override
    public RegistroPonto registrarSaida(RegistroPonto registroAtual, Scanner scanner) {
        boolean conferencia=false;
        System.out.println("Digite a hora de saída no formato HH:MM: ");
        String input=scanner.nextLine().trim();
        try {
            if(RegistroPonto.confereFormatoHora(input)){//se o formato da hora estiver ok
                LocalTime horaSaida=registroAtual.converteHora(input);
                if((horaSaida.isAfter(registroAtual.getSaida()))&&(horaSaida.isBefore(registroAtual.converteHora("22:00")))){
                    //hora saida é depois da hora de entrada e antes das 22:00
                    long horaExtra = registroAtual.getEntrada().until(horaSaida, ChronoUnit.HOURS);
                    horaExtra=(horaExtra-9);//8 horas normais de trabalho + 1 hora de almoço
                    if(horaExtra>3){
                        System.out.println("Hora extra realizada ("+horaExtra+") maior que o limite de 3 horas");
                        //para que a hora extra não ultrapasse 3 hroas o cálculo é horasaida=12+horaentrada
                        LocalTime novaHoraSaida = registroAtual.getEntrada().plusHours(12);
                        String mensagem ="Em cumprimento às regras da empresa, para não exceder o limite de 3 horas diárias, sera considerado como hora e saida "+novaHoraSaida+". Compareça ao RH";
                        System.out.println(mensagem);
                        registroAtual.setSaida(novaHoraSaida);
                        registroAtual.setObservacao(mensagem);
                        registroAtual.finalizarRegistro();
                    }else{
                        registroAtual.setSaida(horaSaida);
                        registroAtual.finalizarRegistro();
                    }
                }else{
                    System.out.println("hora saida é igual ou anteior a hora de entrada ou a hora de saida é depois das 22:00");
                    registroAtual=null;
                }//
            }else{
                System.out.println("formato da hora não esta correto");
                registroAtual=null;
            }//}
        } catch (DateTimeParseException e) {
            throw new DateTimeException("problema com o formato da hora digitada.",e);
        }catch(DateTimeException e){
            throw new DateTimeException("Problema ao calcular a hora extra.",e);
        }catch(ArithmeticException e){
            throw new ArithmeticException("Problema ao calulare a hora extra, over flow");
        }
        return registroAtual;
    }

    @Override
    public RegistroPonto registrarEntrada(RegistroPonto registroAtual, Scanner scanner) {
        System.out.println("Digite a hora de entrada no formato HH:MM");
        String input=scanner.nextLine().trim();

        //CONTINUAR DAQUI.
    }

    
    

}
