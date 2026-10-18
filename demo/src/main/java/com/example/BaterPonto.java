package com.example;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public interface BaterPonto {

    //1 - verificar se a folha de ponto esta vazia. se verdadeiro então chama criarRegistro em seguida registrarEntrada()
    //2 - se não estiver vazia, vai procurar na folha de ponto qual o registro está com atributo completo==false e selecionar este RegistroPonto
    //3 - Com o registro selecionado vai chamar registrarSaida()

    public void registrarPonto(Scanner scanner) throws OperacaoAbortadaException, RegistroIncompletoException, DateTimeParseException, ArithmeticException;

    public default RegistroPonto registrarEntrada(RegistroPonto registroAtual, Scanner scanner, String codFuncionario){
        RegistroPonto registroAtualizado = null;
        System.out.println("Digite o dia no formato DD/MM/AAAA: ");
        String inputDia = scanner.nextLine().trim();
        System.out.println("Digite o horario de entrada no formato HH:MM. Não pode ser anteriror à 06:00: ");
        String inputHora = scanner.nextLine().trim();
        if(RegistroPonto.confereFormato(inputDia, inputHora)){
            //se o formato do dia e hora estiver ok
            System.out.println("Formato data e hora ok");
            LocalTime horaEntrada=LocalTime.parse(inputHora,RegistroPonto.getFormatoHora());
            if(horaEntrada.isAfter(LocalTime.of(6, 0))){
                //se for posteriro à 06:oo
                System.out.println("hora entrada é posterior à 06:00");
                registroAtualizado=new RegistroPonto(inputDia, inputHora, codFuncionario);
            }else{
                //se for anterior à 06:00
                registroAtualizado=new RegistroPonto();
            }
        }else{
            //se o formato do dia e hora não estiver ok
            registroAtualizado=new RegistroPonto();
        }
        return registroAtualizado;
    }

    public RegistroPonto registrarSaida(RegistroPonto registroAtual, Scanner scanner);
}
