package com.meli.apifutebol.dto;

public class EstadioDto {

    private String nomeEstadio;

    public EstadioDto(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }

    public EstadioDto() {

    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }
}
