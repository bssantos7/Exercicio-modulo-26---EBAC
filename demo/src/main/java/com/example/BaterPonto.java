package com.example;

import java.util.Scanner;

public interface BaterPonto {

    //1 - verificar se a folha de ponto esta vazia. se verdadeiro então chama criarRegistro em seguida registrarEntrada()
    //2 - se não estiver vazia, vai procurar na folha de ponto qual o registro está com atributo completo==false e selecionar este RegistroPonto
    //3 - Com o registro selecionado vai chamar registrarSaida()

    public void registrarPonto(Scanner scanner);
    public void registrarEntrada();
    public void registrarSaida();
}
