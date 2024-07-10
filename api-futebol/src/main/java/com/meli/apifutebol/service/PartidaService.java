package com.meli.apifutebol.service;

import com.meli.apifutebol.dto.PartidaDto;
import com.meli.apifutebol.model.Partida;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {


    public String salvar(PartidaDto partidaDto) {
//        partidaRepository.save(partida);
        Partida partida = converter(partidaDto);
        return "Partida cadastrado com sucesso!";
    }

    public String atualizarPartida(PartidaDto partidaDto) {
        if (partidaDto.getDataHora().equals(partidaDto)) {
            Partida partida = converter(partidaDto);
//          partidaRepository.save(partida);
            return "Partida Atualizado com sucesso!";
        } else {
            return "Partida não encontrado";
        }
    }

//    public Page<Partida> buscarPartidas(Clube  clubeCasa, Clube clubeVisitante, String estadio, Pageable pageable) {
//        if (nome == null) clubeCasa = "";
//        if (estado == null) clubeVisitante = "";
//        if (ativo == null) estadio = "";
//
//        return partidaRepository.findByNomeContainingAndClubeAndEstadio(clubeCasa, clubeVisitante, estadio, pageable);
//    }



    private Partida converter(PartidaDto partidaDto){
        Partida partida = new Partida();
        partida.setClubeCasa(partidaDto.getClubeCasa());
        partida.setClubeVisitante(partidaDto.getClubeVisitante());
        partida.setDataHora(partidaDto.getDataHora());
        partida.setEstadio(partidaDto.getEstadio());
        partida.setResultadoClubeCasa(partidaDto.getResultadoClubeCasa());
        partida.setResultadoClubeVisitante(partida.getResultadoClubeVisitante());
        return partida;
    }
}
