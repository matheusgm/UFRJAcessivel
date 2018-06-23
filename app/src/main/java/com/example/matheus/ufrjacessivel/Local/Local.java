package com.example.matheus.ufrjacessivel.Local;

public class Local {

    private int id;
    private String name;
    private String Tipo;
    private String NumLibraUp;
    private String NumLibraDown;
    private String CorLibras;
    private String NumRampaUp;
    private String NumRampaDown;
    private String CorRampas;
    private String NumInterpretesUp;
    private String NumInterpretesDown;
    private String CorInterpretes;
    private String temMapa;

    public String getCorLibras() {
        return CorLibras;
    }

    public void setCorLibras(String corLibras) {
        CorLibras = corLibras;
    }

    public String getCorRampas() {
        return CorRampas;
    }

    public void setCorRampas(String corRampas) {
        CorRampas = corRampas;
    }

    public String getCorInterpretes() {
        return CorInterpretes;
    }

    public void setCorInterpretes(String corInterpretes) {
        CorInterpretes = corInterpretes;
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

    public String getNumLibraUp() {
        return NumLibraUp;
    }

    public void setNumLibraUp(String numLibraUp) {
        NumLibraUp = numLibraUp;
    }

    public String getNumLibraDown() {
        return NumLibraDown;
    }

    public void setNumLibraDown(String numLibraDown) {
        NumLibraDown = numLibraDown;
    }

    public String getNumRampaUp() {
        return NumRampaUp;
    }

    public void setNumRampaUp(String numRampaUp) {
        NumRampaUp = numRampaUp;
    }

    public String getNumRampaDown() {
        return NumRampaDown;
    }

    public void setNumRampaDown(String numRampaDown) {
        NumRampaDown = numRampaDown;
    }

    public String getNumInterpretesUp() {
        return NumInterpretesUp;
    }

    public void setNumInterpretesUp(String numInterpretesUp) {
        NumInterpretesUp = numInterpretesUp;
    }

    public String getNumInterpretesDown() {
        return NumInterpretesDown;
    }

    public void setNumInterpretesDown(String numInterpretesDown) {
        NumInterpretesDown = numInterpretesDown;
    }

    public String getTemMapa() {
        return temMapa;
    }

    public void setTemMapa(String temMapa) {
        this.temMapa = temMapa;
    }
}
