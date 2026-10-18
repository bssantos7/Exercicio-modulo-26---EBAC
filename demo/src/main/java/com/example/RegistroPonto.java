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

    private String codigo;
    private LocalDate dia;
    private LocalTime entrada;
    private LocalTime saida;
    private boolean completo;
    private TipoRegistroPonto tipoAtual;
    private String observacao;


    public RegistroPonto(String dia, String hora, String key){
        // quando cria o primeiro registro, ja passa a data e hora de entrada e set tipoRegistroPonto como saida
        this.codigo=key+"-"+dia;
        this.dia=LocalDate.parse(dia,formatoData);
        this.entrada=LocalTime.parse(hora,formatoHora);
        this.saida=null;
        this.tipoAtual=TipoRegistroPonto.SAIDA;
        this.completo=false;
        this.observacao="não há observações sobre este registro";
        RegistroPonto.setDigito();
        
    }

    public RegistroPonto(){
        // cria um novo registro autmoaticamente após completar o registro anterior para ser preenchido posterioemente começando pela entrada
        this.codigo=null;
        this.dia=null;
        this.entrada=null;
        this.saida=null;
        this.tipoAtual=TipoRegistroPonto.ENTRADA;
        this.completo=false;
        this.observacao="não há observações sobre este registro";
    }

    public void atualizaRegistroEntrada(String dia, String hora, String key, String mensagem){
        
        this.codigo=key+"-"+dia;
        this.dia=LocalDate.parse(dia,formatoData);
        this.entrada=LocalTime.parse(hora,formatoHora);
        this.saida=null;
        this.tipoAtual=TipoRegistroPonto.SAIDA;
        this.completo=false;
        if(mensagem.isEmpty()||mensagem.equals("")){
            this.observacao="não há observações sobre este registro";
        }else{
            this.observacao=mensagem;
        }
        RegistroPonto.setDigito();

    }

    public RegistroPonto atualizaRegistroEntrada2(String dia, String hora, String key, String mensagem){
        
        //RegistroPonto registroAtualizado = new RegistroPonto();
        this.codigo=key+"-"+dia;
        this.dia=LocalDate.parse(dia,formatoData);
        this.entrada=LocalTime.parse(hora,formatoHora);
        this.saida=null;
        this.tipoAtual=TipoRegistroPonto.SAIDA;
        this.completo=false;
        if(mensagem.isEmpty()||mensagem.equals("")){
            this.observacao="não há observações sobre este registro";
        }else{
            this.observacao=mensagem;
        }
        RegistroPonto.setDigito();

        return this;

    }


    public static int getDigito(){
        return RegistroPonto.digito;
    }

    public static void setDigito(){
        RegistroPonto.digito++;
    }

    
    
    public static DateTimeFormatter getFormatoData() {
        return formatoData;
    }

    public static DateTimeFormatter getFormatoHora() {
        return formatoHora;
    }

    public static boolean confereFormatoDia(String dia)throws DateTimeParseException{
        
        try {
            LocalDate diaD = LocalDate.parse(dia, formatoData);
            return true;
        } catch (DateTimeParseException e) {
           throw new DateTimeParseException("o dia digitado não está no formato correto HH:MM", e.getParsedString(),e.getErrorIndex(),e);
        }
    }

    public static boolean confereFormatoHora(String hora) throws DateTimeParseException{
        try {
            LocalTime horaH = LocalTime.parse(hora, formatoHora);
            return true;
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("a hora digitada não está no formato correto HH:MM", e.getParsedString(),e.getErrorIndex(),e);
        }
    }

    public static boolean confereFormato(String dia, String hora){
        boolean resposta = false;
        LocalDate diaD=null;
        LocalTime horaH=null;
        try {
            if(confereFormatoDia(dia)==true&&confereFormatoHora(hora)==true)
            diaD=LocalDate.parse(dia,RegistroPonto.getFormatoData());
            horaH=LocalTime.parse(hora,RegistroPonto.getFormatoHora());
            System.out.println("Dia:"+diaD+" hora:"+horaH);
            resposta=true; 
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            resposta=false;
        }
        return resposta;
    }

    public LocalDate converteData(String data){
        LocalDate novaData = LocalDate.parse(data, formatoData);
        return novaData;
    }

    public LocalTime converteHora(String hora){
        LocalTime novaHora = LocalTime.parse(hora, formatoHora);
        return novaHora;
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


    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    public LocalTime getSaida() {
        return saida;
    }

    public void setSaida(LocalTime saida) {
        this.saida = saida;
    }

    public String getObservacao() {
        return observacao;
    }

 

    @Override
    public String toString() {
        return "RegistroPonto [codigo=" + codigo + ", dia=" + dia + ", entrada=" + entrada + ", saida=" + saida
                + ", completo=" + completo + ", tipoAtual=" + tipoAtual + ", observacao=" + observacao + "]";
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
