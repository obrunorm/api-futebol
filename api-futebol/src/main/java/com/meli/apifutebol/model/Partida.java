package com.meli.apifutebol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clube_casa_id")
    private Clube clubeCasa;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clube_visitante_id")
    private Clube clubeVisitante;

    @NonNull
    private int resultadoClubeCasa;

    @NonNull
    private int resultadoClubeVisitante;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "estadio_id")
    private Estadio estadio;

    @NonNull
    @Past
    private LocalDate dataHora;

    @ManyToOne
    @JoinColumn(name = "clube_vencedor_id")
    private Clube clubeVencedor;

    public Partida(UUID uuid, Clube clubeCasa, Clube clubeVisitante, int resultadoClubeCasa, int resultadoClubeVisitante, Estadio estadio, @NonNull LocalDate dataHora, Clube clubeVencedor) {
        this.uuid = uuid;
        this.clubeCasa = clubeCasa;
        this.clubeVisitante = clubeVisitante;
        this.resultadoClubeCasa = resultadoClubeCasa;
        this.resultadoClubeVisitante = resultadoClubeVisitante;
        this.estadio = estadio;
        this.dataHora = dataHora;
        this.clubeVencedor = clubeVencedor;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public Clube getClubeVencedor() {
        return clubeVencedor;
    }

    public void setClubeVencedor(Clube clubeVencedor) {
        this.clubeVencedor = clubeVencedor;
    }
}
