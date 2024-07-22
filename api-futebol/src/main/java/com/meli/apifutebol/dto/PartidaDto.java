package com.meli.apifutebol.dto;

import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.model.Estadio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class PartidaDto {

    private UUID uuid;
    private Clube clubeCasa;
    private Clube clubeVisitante;
    private int resultadoClubeCasa;
    private int resultadoClubeVisitante;
    private Estadio estadio;
    private LocalDate dataHora;

    public PartidaDto() {
    }

    public PartidaDto(UUID uuid, Clube clubeCasa, Clube clubeVisitante, LocalDate dataHora, Estadio estadio, int resultadoClubeVisitante, int resultadoClubeCasa) {
        this.uuid = uuid;
        this.clubeCasa = clubeCasa;
        this.clubeVisitante = clubeVisitante;
        this.dataHora = dataHora;
        this.estadio = estadio;
        this.resultadoClubeVisitante = resultadoClubeVisitante;
        this.resultadoClubeCasa = resultadoClubeCasa;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Clube getClubeCasa() {
        return clubeCasa;
    }

    public void setClubeCasa(Clube clubeCasa) {
        this.clubeCasa = clubeCasa;
    }

    public Clube getClubeVisitante() {
        return clubeVisitante;
    }

    public void setClubeVisitante(Clube clubeVisitante) {
        this.clubeVisitante = clubeVisitante;
    }

    public int getResultadoClubeCasa() {
        return resultadoClubeCasa;
    }

    public void setResultadoClubeCasa(int resultadoClubeCasa) {
        this.resultadoClubeCasa = resultadoClubeCasa;
    }

    public int getResultadoClubeVisitante() {
        return resultadoClubeVisitante;
    }

    public void setResultadoClubeVisitante(int resultadoClubeVisitante) {
        this.resultadoClubeVisitante = resultadoClubeVisitante;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

}
