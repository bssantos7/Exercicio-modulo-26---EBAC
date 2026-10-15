package com.example;

public enum TipoFuncionario {
    //CONSTANTES
    ESTAGIARIO(1, "Cargo temporário não registra ponto"), 
    ASSISTENTE(2, "Cargo efetivo inicial. Registra ponto / 3 horas extras max. "),
    ANALISTA(3, "Cargo efetivo avançado. Registra ponto / 3 horas extras max. "),
    COORDENADOR(4, "Cargo de Chefia inicial. Registro de ponto obrigatório / 5 horas Extras max"),
    GERENTE(5, "Cargo de chefia avançado. Não registra ponto");

    //ATRIBUTOS
    private final int codTipo;
    private final String descricao;

    TipoFuncionario(int codTipo, String descricao){
        this.codTipo=codTipo;
        this.descricao=descricao;

    }

    public int getCodTipo(){
        return codTipo;
    }

    public String getDescricao(){
        return descricao;
    }

    public static TipoFuncionario consultaTipoPorCodigo(int codTipo){
        for(TipoFuncionario tipoFuncionario:TipoFuncionario.values()){
            if(tipoFuncionario.getCodTipo()==codTipo){
                return tipoFuncionario;
            }
        }
        throw new IllegalArgumentException("o valor digitado ("+codTipo+") é invalido.");
    }

    public static TipoFuncionario[] geTipoFuncionarios(){
        return TipoFuncionario.values();
    }
}
