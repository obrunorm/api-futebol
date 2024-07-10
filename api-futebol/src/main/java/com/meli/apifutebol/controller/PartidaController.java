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

}
