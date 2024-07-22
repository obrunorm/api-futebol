package com.meli.apifutebol.dto;


import com.meli.apifutebol.enums.Estados;
import com.meli.apifutebol.enums.StatusClube;
import com.meli.apifutebol.model.Estadio;


import java.time.LocalDate;
import java.util.UUID;

public class ClubeDto {


    private UUID uuid;
    private String nome;
    private Estados estados;
    private LocalDate dataCriacao;
    private StatusClube ativo;
    private Estadio estadio;

    public ClubeDto(){

    }

    public ClubeDto(UUID uuid, String nome, Estados estados, LocalDate dataCriacao, StatusClube ativo, Estadio estadio) {
        this.uuid = uuid;
        this.nome = nome;
        this.estados = estados;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
        this.estadio = estadio;
    }


    public UUID getId() {
        return uuid;
    }

    public void setId(UUID id) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusClube getAtivo() {
        return ativo;
    }

    public void setAtivo(StatusClube ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

}
