package com.meli.apifutebol.service;
import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.dto.ClubeDto;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class ClubeService {


//    @Autowired
//    private ClubeRespository clubeRepository;

    public String salvar(ClubeDto clubeDto) {
        if (clubeDto.getNome() == null) {
            return "Nome não pode ser nulo!";
        }

        Clube clube = converter(clubeDto);

//        clubeRepository.save(clube);
        return "Clube Salvo!";
    }

    public String atualizarClube(ClubeDto clubeDto) {
        if (clubeDto.getNome().equals(clubeDto)) {
            Clube clube = converter(clubeDto);
//          clubeRepository.save(clube);
            return "Clube Atualizado com sucesso!";
        } else {
            return "Clube não encontrado";
        }
    }

    public String deletarClube(ClubeDto clubeDto) {
        if (clubeDto.getNome().equals(clubeDto.getNome())) {
            clubeDto.setAtivo(false);
            Clube clube = converter(clubeDto);
            return "Clube Deletado com sucesso!";
        } else {
            return "Clube não encontrado";
        }
    }

//    public Page<Clube> buscarClubles(String nome, String estado, Boolean ativo, Pageable pageable) {
//        if (nome == null) nome = "";
//        if (estado == null) estado = "";
//        if (ativo == null) ativo = true;
//
//        return clubeRepository.findByNomeContainingAndEstadoContainingAndAtivo(nome, estado, ativo, pageable);
//    }


    private Clube converter(ClubeDto clubeDto){
        Clube clube = new Clube();
        clube.setNome(clubeDto.getNome());
        clube.setDataCriacao(clubeDto.getDataCriacao());
        clube.setSiglaEstado(clubeDto.getSiglaEstado());
        clube.setAtivo(clube.isAtivo());
        return clube;
    }

}
