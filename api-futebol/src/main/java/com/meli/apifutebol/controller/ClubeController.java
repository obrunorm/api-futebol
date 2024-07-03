package com.meli.apifutebol.controller;

import com.meli.apifutebol.dto.ClubeDto;
import com.meli.apifutebol.service.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("clube")
public class ClubeController {

//    @Autowired
//    private ClubeRespository clubeRespository;

    @Autowired
    private ClubeService clubeService;

    @PostMapping
    public ResponseEntity salvaClube(@RequestBody ClubeDto clubeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clubeService.salvar(clubeDto));
    }

    @PutMapping
    public ResponseEntity atualizaClube(@RequestBody ClubeDto clubeDto) {
        return ResponseEntity.status(HttpStatus.OK).body(clubeService.atualizarClube(clubeDto));
    }

    @DeleteMapping("/{clubeDto}")
    public ResponseEntity inativaClube(@PathVariable ClubeDto clubeDto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clubeService.deletarClube(clubeDto));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ClubeDto> getById(@PathVariable long id) {
//
//        return clubeService.buscarClubeId(id).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

//    @GetMapping
//    public ResponseEntity<Page<Clube>> listarClubles(
//            @RequestParam(required = false) String nome,
//            @RequestParam(required = false) String estado,
//            @RequestParam(required = false) Boolean ativo,
//            Pageable pageable) {
//
//        Page<Clube> clubes = clubeService.buscarClubles(nome, estado, ativo, pageable);
//        return ResponseEntity.ok(clubes);
//    }
}
