package com.meli.apifutebol.model;

import com.meli.apifutebol.enums.Estados;
import com.meli.apifutebol.enums.StatusClube;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_clube")
public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(unique = true)
    @Size(min = 3, max = 50)
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

    public Clube(){

    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
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
}
