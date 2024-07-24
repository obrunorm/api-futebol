package com.meli.apifutebol.dto;

public class RetrospectoDto {
    private int totalVitorias;
    private int totalEmpates;
    private int totalDerrotas;
    private int golsFeitos;
    private int golsSofridos;

    public RetrospectoDto(){

    }

    public RetrospectoDto(int totalVitorias, int totalEmpates, int totalDerrotas, int golsFeitos, int golsSofridos) {
        this.totalVitorias = totalVitorias;
        this.totalEmpates = totalEmpates;
        this.totalDerrotas = totalDerrotas;
        this.golsFeitos = golsFeitos;
        this.golsSofridos = golsSofridos;
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

    public int getTotalDerrotas() {
        return totalDerrotas;
    }

    public void setTotalDerrotas(int totalDerrotas) {
        this.totalDerrotas = totalDerrotas;
    }

    public int getGolsFeitos() {
        return golsFeitos;
    }

    public void setGolsFeitos(int golsFeitos) {
        this.golsFeitos = golsFeitos;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    public void setGolsSofridos(int golsSofridos) {
        this.golsSofridos = golsSofridos;
    }
}