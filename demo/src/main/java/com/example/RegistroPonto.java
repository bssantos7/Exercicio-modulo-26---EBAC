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
    private String observacao;


    public RegistroPonto(String dia, String hora, String key){
        this.codigo=key+"/"+dia+"-"+RegistroPonto.getDigito();
        this.dia=LocalDate.parse(dia,formatoData);
        this.entrada=LocalTime.parse(hora,formatoHora);
        this.saida=null;
        this.tipoAtual=TipoRegistroPonto.SAIDA;
        this.completo=false;
        this.observacao="não há observações sobre este registro";
        RegistroPonto.setDigito();
        
    }

    public static int getDigito(){
        return RegistroPonto.digito;
    }

    public static void setDigito(){
        RegistroPonto.digito++;
    }
    
    private static boolean confereFormatoDia(String dia){
        
        try {
            LocalDate diaD = LocalDate.parse(dia, formatoData);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean confereFormatoHora(String hora){
        try {
            LocalTime horaH = LocalTime.parse(hora, formatoHora);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean confereFormato(String dia, String hora){
        if(confereFormatoDia(dia)==true&&confereFormatoHora(hora)==true){
            return true;
        }else{
            return false;
        }
    }


    public String getCodigo(){
        return this.codigo;
    }

    public void setObservacao(String mensagem){
        this.observacao=mensagem;
    }

    public TipoRegistroPonto getTipoRegistroPonto(){
        return this.tipoAtual;
    }

    public void setTipoRegistroPonto(){
        if(this.tipoAtual.getValor()==1){
            this.tipoAtual=TipoRegistroPonto.SAIDA;
        }else{
            this.tipoAtual=TipoRegistroPonto.ENTRADA;
        }
    }

    public boolean isCompleto(){
        return this.completo;
    }

    public void finalizarRegistro(){
        this.completo=true;
    }

    @Override
    public String toString() {
        return "RegistroPonto [codigo=" + codigo + ", dia=" + dia + ", entrada=" + entrada + ", saida=" + saida
                + ", completo=" + completo + ", observacao=" + observacao + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((dia == null) ? 0 : dia.hashCode());
        result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
        result = prime * result + ((saida == null) ? 0 : saida.hashCode());
        result = prime * result + (completo ? 1231 : 1237);
        result = prime * result + ((tipoAtual == null) ? 0 : tipoAtual.hashCode());
        result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RegistroPonto other = (RegistroPonto) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (dia == null) {
            if (other.dia != null)
                return false;
        } else if (!dia.equals(other.dia))
            return false;
        if (entrada == null) {
            if (other.entrada != null)
                return false;
        } else if (!entrada.equals(other.entrada))
            return false;
        if (saida == null) {
            if (other.saida != null)
                return false;
        } else if (!saida.equals(other.saida))
            return false;
        if (completo != other.completo)
            return false;
        if (tipoAtual != other.tipoAtual)
            return false;
        if (observacao == null) {
            if (other.observacao != null)
                return false;
        } else if (!observacao.equals(other.observacao))
            return false;
        return true;
    }

    



}
