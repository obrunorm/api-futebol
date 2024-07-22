package com.meli.apifutebol.dto;


import java.util.UUID;

public class EstadioDto {


    private UUID uuid;
    private String nomeEstadio;

    public EstadioDto() {

    }

    public EstadioDto(UUID uuid, String nomeEstadio) {
        this.uuid = uuid;
        this.nomeEstadio = nomeEstadio;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }
}
