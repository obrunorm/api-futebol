package com.meli.apifutebol.service;

import com.meli.apifutebol.dto.AdversarioRetrospectoDto;
import com.meli.apifutebol.dto.RankingClubeDto;
import com.meli.apifutebol.dto.RetrospectoDto;
import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.model.Partida;
import com.meli.apifutebol.repository.ClubeRepository;
import com.meli.apifutebol.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RetrospectoService {


    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    ClubeRepository clubeRepository;

    public RetrospectoDto buscarRetrospecto(UUID clubeUuid) {
        List<Partida> partidas = partidaRepository.findByClubeCasa_UuidOrClubeVisitante_Uuid(clubeUuid, clubeUuid);

        int totalVitorias = 0;
        int totalEmpates = 0;
        int totalDerrotas = 0;
        int golsFeitos = 0;
        int golsSofridos = 0;

        for (Partida partida : partidas) {
            if (partida.getClubeVencedor() != null) {
                if (partida.getClubeVencedor().getUuid().equals(clubeUuid)) {
                    totalVitorias++;
                } else {
                    totalDerrotas++;
                }
            } else {
                totalEmpates++;
            }

            golsFeitos += partida.getResultadoClubeCasa();
            golsFeitos += partida.getResultadoClubeVisitante();
            golsSofridos += partida.getResultadoClubeCasa();
            golsSofridos += partida.getResultadoClubeVisitante();
        }

        RetrospectoDto retrospectoDto = new RetrospectoDto();
        retrospectoDto.setTotalVitorias(totalVitorias);
        retrospectoDto.setTotalEmpates(totalEmpates);
        retrospectoDto.setTotalDerrotas(totalDerrotas);
        retrospectoDto.setGolsFeitos(golsFeitos);
        retrospectoDto.setGolsSofridos(golsSofridos);

        return retrospectoDto;
    }

    public List<Partida> buscarConfrontosDiretos(UUID clube1Uuid, UUID clube2Uuid) {
        return partidaRepository.findByClubes(clube1Uuid, clube2Uuid);
    }

    public RetrospectoDto calcularRetrospecto(UUID clube1Uuid, UUID clube2Uuid) {
        List<Partida> confrontos = buscarConfrontosDiretos(clube1Uuid, clube2Uuid);

        int totalVitoriasClube1 = 0;
        int totalEmpates = 0;
        int totalDerrotasClube1 = 0;
        int golsFeitosClube1 = 0;
        int golsSofridosClube1 = 0;

        int totalVitoriasClube2 = 0;
        int totalDerrotasClube2 = 0;
        int golsFeitosClube2 = 0;
        int golsSofridosClube2 = 0;

        for (Partida partida : confrontos) {
            if (partida.getClubeVencedor() != null) {
                if (partida.getClubeVencedor().getUuid().equals(clube1Uuid)) {
                    totalVitoriasClube1++;
                    totalDerrotasClube2++;
                } else {
                    totalVitoriasClube2++;
                    totalDerrotasClube1++;
                }
            } else {
                totalEmpates++;
            }

            if (partida.getClubeCasa().getUuid().equals(clube1Uuid)) {
                golsFeitosClube1 += partida.getResultadoClubeCasa();
                golsSofridosClube1 += partida.getResultadoClubeVisitante();
                golsFeitosClube2 += partida.getResultadoClubeVisitante();
                golsSofridosClube2 += partida.getResultadoClubeCasa();
            } else {
                golsFeitosClube1 += partida.getResultadoClubeVisitante();
                golsSofridosClube1 += partida.getResultadoClubeCasa();
                golsFeitosClube2 += partida.getResultadoClubeCasa();
                golsSofridosClube2 += partida.getResultadoClubeVisitante();
            }
        }

        RetrospectoDto retrospectoClube1 = new RetrospectoDto(totalVitoriasClube1, totalEmpates, totalDerrotasClube1,
                golsFeitosClube1, golsSofridosClube1);

        RetrospectoDto retrospectoClube2 = new RetrospectoDto(totalVitoriasClube2, totalEmpates, totalDerrotasClube2,
                golsFeitosClube2, golsSofridosClube2);

        return retrospectoClube1;
    }

    public List<AdversarioRetrospectoDto> buscarRetrospectoContraTodos(UUID clubeUuid) {
        List<Partida> partidas = partidaRepository.findByClubeCasa_UuidOrClubeVisitante_Uuid(clubeUuid, clubeUuid);
        Map<String, AdversarioRetrospectoDto> retrospectos = new HashMap<>();

        for (Partida partida : partidas) {
            UUID adversarioUuid;
            boolean isCasa = partida.getClubeCasa().getUuid().equals(clubeUuid);
            if (isCasa) {
                adversarioUuid = partida.getClubeVisitante().getUuid();
            } else {
                adversarioUuid = partida.getClubeCasa().getUuid();
            }

            Clube adversario = clubeRepository.findByUuid(adversarioUuid);
            AdversarioRetrospectoDto retrospecto = retrospectos.getOrDefault(adversario.getNome(), new AdversarioRetrospectoDto(adversario.getNome(), 0, 0, 0, 0, 0));

            if (partida.getClubeVencedor() != null) {
                if (partida.getClubeVencedor().getUuid().equals(clubeUuid)) {
                    retrospecto.setTotalVitorias(retrospecto.getTotalVitorias() + 1);
                } else {
                    retrospecto.setTotalDerrotas(retrospecto.getTotalDerrotas() + 1);
                }
            } else {
                retrospecto.setTotalEmpates(retrospecto.getTotalEmpates() + 1);
            }

            if (isCasa) {
                retrospecto.setGolsFeitos(retrospecto.getGolsFeitos() + partida.getResultadoClubeCasa());
                retrospecto.setGolsSofridos(retrospecto.getGolsSofridos() + partida.getResultadoClubeVisitante());
            } else {
                retrospecto.setGolsFeitos(retrospecto.getGolsFeitos() + partida.getResultadoClubeVisitante());
                retrospecto.setGolsSofridos(retrospecto.getGolsSofridos() + partida.getResultadoClubeCasa());
            }

            retrospectos.put(adversario.getNome(), retrospecto);
        }

        return new ArrayList<>(retrospectos.values());
    }

    public List<RankingClubeDto> rankearClubes(String criterio) {
        List<Clube> clubes = clubeRepository.findAll();
        Map<UUID, RankingClubeDto> rankingMap = new HashMap<>();

        for (Clube clube : clubes) {
            List<Partida> partidas = partidaRepository.findByClubeCasa_UuidOrClubeVisitante_Uuid(clube.getUuid(), clube.getUuid());
            int totalJogos = partidas.size();
            int totalVitorias = 0;
            int totalEmpates = 0;
            int totalGols = 0;
            int totalPontos = 0;

            for (Partida partida : partidas) {
                if (partida.getClubeVencedor() != null) {
                    if (partida.getClubeVencedor().getUuid().equals(clube.getUuid())) {
                        totalVitorias++;
                        totalPontos += 3;
                    }
                } else {
                    totalEmpates++;
                    totalPontos += 1;
                }

                if (partida.getClubeCasa().getUuid().equals(clube.getUuid())) {
                    totalGols += partida.getResultadoClubeCasa();
                } else {
                    totalGols += partida.getResultadoClubeVisitante();
                }
            }

            RankingClubeDto rankingClubeDto = new RankingClubeDto(clube.getNome(), totalJogos, totalVitorias, totalEmpates, totalGols, totalPontos);
            rankingMap.put(clube.getUuid(), rankingClubeDto);
        }

        List<RankingClubeDto> rankingList = new ArrayList<>(rankingMap.values());

        switch (criterio) {
            case "totalJogos":
                rankingList = rankingList.stream().filter(clube -> clube.getTotalJogos() > 0).sorted(Comparator.comparingInt(RankingClubeDto::getTotalJogos).reversed()).collect(Collectors.toList());
                break;
            case "totalVitorias":
                rankingList = rankingList.stream().filter(clube -> clube.getTotalVitorias() > 0).sorted(Comparator.comparingInt(RankingClubeDto::getTotalVitorias).reversed()).collect(Collectors.toList());
                break;
            case "totalGols":
                rankingList = rankingList.stream().filter(clube -> clube.getTotalGols() > 0).sorted(Comparator.comparingInt(RankingClubeDto::getTotalGols).reversed()).collect(Collectors.toList());
                break;
            case "totalPontos":
                rankingList = rankingList.stream().filter(clube -> clube.getTotalPontos() > 0).sorted(Comparator.comparingInt(RankingClubeDto::getTotalPontos).reversed()).collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("Critério de ranking inválido");
        }

        return rankingList;
    }
}