package com.meli.apifutebol.dto;

public class RankingClubeDto {
    private String nomeClube;
    private int totalJogos;
    private int totalVitorias;
    private int totalEmpates;
    private int totalGols;
    private int totalPontos;

    public RankingClubeDto(String nomeClube, int totalJogos, int totalVitorias, int totalEmpates, int totalGols, int totalPontos) {
        this.nomeClube = nomeClube;
        this.totalJogos = totalJogos;
        this.totalVitorias = totalVitorias;
        this.totalEmpates = totalEmpates;
        this.totalGols = totalGols;
        this.totalPontos = totalPontos;
    }

    public String getNomeClube() {
        return nomeClube;
    }

    public void setNomeClube(String nomeClube) {
        this.nomeClube = nomeClube;
    }

    public int getTotalJogos() {
        return totalJogos;
    }

    public void setTotalJogos(int totalJogos) {
        this.totalJogos = totalJogos;
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

    public int getTotalGols() {
        return totalGols;
    }

    public void setTotalGols(int totalGols) {
        this.totalGols = totalGols;
    }

    public int getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(int totalPontos) {
        this.totalPontos = totalPontos;
    }
}

