package com.meli.apifutebol.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meli.apifutebol.enums.Estados;
import com.meli.apifutebol.enums.StatusClube;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_clube")
public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(unique = true)
    @Size(min = 3)
    @NonNull
    private String nome;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Estados estados;

    @NonNull
    @Past
    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    @NonNull
    private StatusClube ativo;

    @JsonBackReference
    @OneToMany(mappedBy = "clubeCasa", cascade = CascadeType.ALL)
    private List<Partida> partidasComoCasa;

    @JsonBackReference
    @OneToMany(mappedBy = "clubeVisitante", cascade = CascadeType.ALL)
    private List<Partida> partidasComoVisitante;

    @JsonBackReference
    @OneToMany(mappedBy = "clubeVencedor", cascade = CascadeType.ALL)
    private List<Partida> clubeVencedor;

    @ManyToOne
    @JoinColumn(name = "estadio_id")
    private Estadio estadio;

    public Clube(List<Partida> clubeVencedor){

        this.clubeVencedor = clubeVencedor;
    }

    public Clube(UUID uuid, String nome, Estados estados, LocalDate dataCriacao, StatusClube ativo, List<Partida> partidasComoCasa, List<Partida> partidasComoVisitante, List<Partida> clubeVencedor, Estadio estadio) {
        this.uuid = uuid;
        this.nome = nome;
        this.estados = estados;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
        this.partidasComoCasa = partidasComoCasa;
        this.partidasComoVisitante = partidasComoVisitante;
        this.clubeVencedor = clubeVencedor;
        this.estadio = estadio;
    }

    public Clube() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
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

    public List<Partida> getPartidasComoCasa() {
        return partidasComoCasa;
    }

    public void setPartidasComoCasa(List<Partida> partidasComoCasa) {
        this.partidasComoCasa = partidasComoCasa;
    }

    public List<Partida> getPartidasComoVisitante() {
        return partidasComoVisitante;
    }

    public void setPartidasComoVisitante(List<Partida> partidasComoVisitante) {
        this.partidasComoVisitante = partidasComoVisitante;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }
}
