package com.meli.apifutebol.dto;

import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.model.Partida;

public class AdversarioRetrospectoDto {

    private String adversarioNome;
    private int totalVitorias;
    private int totalEmpates;
    private int totalDerrotas;
    private int golsFeitos;
    private int golsSofridos;

    public AdversarioRetrospectoDto() {

    }

    public AdversarioRetrospectoDto(String adversarioNome, int totalEmpates, int totalVitorias, int totalDerrotas, int golsFeitos, int golsSofridos) {
        this.adversarioNome = adversarioNome;
        this.totalEmpates = totalEmpates;
        this.totalVitorias = totalVitorias;
        this.totalDerrotas = totalDerrotas;
        this.golsFeitos = golsFeitos;
        this.golsSofridos = golsSofridos;
    }

    public String getAdversarioNome() {
        return adversarioNome;
    }

    public void setAdversarioNome(String adversarioNome) {
        this.adversarioNome = adversarioNome;
    }

    public int getTotalVitorias() {
        return totalVitorias;
    }

    public void setTotalVitorias(int totalVitorias) {
        this.totalVitorias = totalVitorias;
    }

    public int getTotalEmpates() {
        return totalEmpates;
    }

    public void setTotalEmpates(int totalEmpates) {
        this.totalEmpates = totalEmpates;
    }

    public int getGolsFeitos() {
        return golsFeitos;
    }

    public void setGolsFeitos(int golsFeitos) {
        this.golsFeitos = golsFeitos;
    }

    public int getTotalDerrotas() {
        return totalDerrotas;
    }

    public void setTotalDerrotas(int totalDerrotas) {
        this.totalDerrotas = totalDerrotas;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    public void setGolsSofridos(int golsSofridos) {
        this.golsSofridos = golsSofridos;
    }
}
