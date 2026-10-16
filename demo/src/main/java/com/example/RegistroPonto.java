package com.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.text.DateFormatter;

public class RegistroPonto {
    
    private static int digito=1;
    private static DateTimeFormatter formatoData =DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
    private static DateTimeFormatter formatoHora =DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);

    private final String codigo;
    private LocalDate dia;
    private LocalTime entrada;
    private LocalTime saida;
    private boolean completo;
    private TipoRegistroPonto tipoAtual;


    public RegistroPonto(String dia, String hora, String key){
        this.codigo=key+"/"+dia+"-"+RegistroPonto.getDigito();
        this.dia=LocalDate.parse(dia,formatoData);
        this.entrada=LocalTime.parse(hora,formatoHora);
        this.saida=null;
        this.tipoAtual=TipoRegistroPonto.SAIDA;
        this.completo=false;
    }

    public static int getDigito(){
        return RegistroPonto.digito;
    }

    public static void setDigito(){
        RegistroPonto.digito++;
    }
    
    public static boolean confereFormatoDia(String dia){
        
        try {
            LocalDate diaD = LocalDate.parse(dia, formatoData);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean confereFormatoHora(String hora){
        try {
            LocalTime horaH = LocalTime.parse(hora, formatoHora);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String getCodigo(){
        return this.codigo;
    }


}
