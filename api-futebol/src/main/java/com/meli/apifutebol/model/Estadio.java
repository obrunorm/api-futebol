package com.meli.apifutebol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_estadio")
public class Estadio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(unique = true)
    @Size(min = 3)
    private String nomeEstadio;

    @OneToMany(mappedBy = "estadio", cascade = CascadeType.ALL)
    private List<Partida> partidas;

    @OneToMany(mappedBy = "estadio", cascade = CascadeType.ALL)
    private List<Clube> clubes;

    public Estadio() {

    }

    public Estadio(UUID uuid, String nomeEstadio) {
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
