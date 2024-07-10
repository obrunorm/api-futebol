package com.meli.apifutebol.model;

import java.time.LocalDateTime;


public class Partida {


    private Clube clubeCasa;
    private Clube clubeVisitante;
    private int resultadoClubeCasa;
    private int resultadoClubeVisitante;
    private Estadio estadio;
    private LocalDateTime dataHora;

    public Partida(Clube clubeCasa, Clube clubeVisitante, LocalDateTime dataHora, Estadio estadio, int resultadoClubeVisitante, int resultadoClubeCasa) {
        this.clubeCasa = clubeCasa;
        this.clubeVisitante = clubeVisitante;
        this.dataHora = dataHora;
        this.estadio = estadio;
        this.resultadoClubeVisitante = resultadoClubeVisitante;
        this.resultadoClubeCasa = resultadoClubeCasa;
    }

    public Partida() {

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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
