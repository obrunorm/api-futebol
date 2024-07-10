package com.meli.apifutebol.dto;


import com.meli.apifutebol.enums.Estados;
import com.meli.apifutebol.enums.StatusClube;

import java.time.LocalDate;
import java.util.UUID;

public class ClubeDto {

    private UUID id;
    private String nome;
    private Estados estados;
    private LocalDate dataCriacao;
    private StatusClube ativo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
