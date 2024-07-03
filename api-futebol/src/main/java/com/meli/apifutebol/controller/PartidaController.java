package com.meli.apifutebol.controller;

import com.meli.apifutebol.dto.PartidaDto;
import com.meli.apifutebol.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("partida")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;


    @PostMapping
    public ResponseEntity salvaPartida(@RequestBody PartidaDto partidaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partidaService.salvar(partidaDto));
    }

    @PutMapping
    public ResponseEntity atualizaPartida(@RequestBody PartidaDto partidaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(partidaService.atualizarPartida(partidaDto));
    }

//    @DeleteMapping("/{id}")
//    public ResponsyEntity deletarPartida(@PathVariable long id) {
//        return RespomseEntity.status(HttpStatus.NO_CONTENT).body(partidaRepository.deleteById(id));
//    }


//    @GetMapping("/{id}")
//    public ResponseEntity<PartidaDto> buscaPartida(@PathVariable Long id) {
//        return partidaRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @GetMapping
//    public ResponseEntity<Page<Partida>> listarPartidas(
//            @RequestParam(required = false) Clube  clubeCasa,
//            @RequestParam(required = false) Clube clubeVisitante,
//            @RequestParam(required = false) String estadio,
//            Pageable pageable) {
//
//        Page<Partida> partidas = partidaService.buscarPartidas(clubeCasa, clubeVisitante, estadio, pageable);
////        return ResponseEntity.ok(partidas);
//    }






}
