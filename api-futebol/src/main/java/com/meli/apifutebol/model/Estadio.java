package com.meli.apifutebol.model;

public class Estadio {

    private String nomeEstadio;

    public Estadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }

    public Estadio() {

    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }
}
