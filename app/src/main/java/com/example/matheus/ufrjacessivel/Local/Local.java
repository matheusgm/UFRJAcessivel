package com.example.matheus.ufrjacessivel.Local;

public class Local {

    private int id;
    private String name;
    private String Tipo;
    private String NumAudicaoUp;
    private String NumAudicaoDown;
    private String CorAudicao;
    private String NumMobilidadeUp;
    private String NumMobilidadeDown;
    private String CorMobilidade;
    private String NumVisaoUp;
    private String NumVisaoDown;
    private String CorVisao;
    private String temMapa;

    public String getCorAudicao() {
        return CorAudicao;
    }

    public void setCorAudicao(String corAudicao) {
        CorAudicao = corAudicao;
    }

    public String getCorMobilidade() {
        return CorMobilidade;
    }

    public void setCorMobilidade(String corMobilidade) {
        CorMobilidade = corMobilidade;
    }

    public String getCorVisao() {
        return CorVisao;
    }

    public void setCorVisao(String corVisao) {
        CorVisao = corVisao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumAudicaoUp() {
        return NumAudicaoUp;
    }

    public void setNumAudicaoUp(String numAudicaoUp) {
        NumAudicaoUp = numAudicaoUp;
    }

    public String getNumAudicaoDown() {
        return NumAudicaoDown;
    }

    public void setNumAudicaoDown(String numAudicaoDown) {
        NumAudicaoDown = numAudicaoDown;
    }

    public String getNumMobilidadeUp() {
        return NumMobilidadeUp;
    }

    public void setNumMobilidadeUp(String numMobilidadeUp) {
        NumMobilidadeUp = numMobilidadeUp;
    }

    public String getNumMobilidadeDown() {
        return NumMobilidadeDown;
    }

    public void setNumMobilidadeDown(String numMobilidadeDown) {
        NumMobilidadeDown = numMobilidadeDown;
    }

    public String getNumVisaoUp() {
        return NumVisaoUp;
    }

    public void setNumVisaoUp(String numVisaoUp) {
        NumVisaoUp = numVisaoUp;
    }

    public String getNumVisaoDown() {
        return NumVisaoDown;
    }

    public void setNumVisaoDown(String numVisaoDown) {
        NumVisaoDown = numVisaoDown;
    }

    public String getTemMapa() {
        return temMapa;
    }

    public void setTemMapa(String temMapa) {
        this.temMapa = temMapa;
    }
}
