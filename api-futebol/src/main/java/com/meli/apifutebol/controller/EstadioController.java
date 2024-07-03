package com.meli.apifutebol.controller;

import com.meli.apifutebol.dto.EstadioDto;
import com.meli.apifutebol.service.EstadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("estadio")
public class EstadioController {

    @Autowired
    private EstadioService estadioService;

    @PostMapping
    public ResponseEntity salvaEstadio(@RequestBody EstadioDto estadioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadioService.salvar(estadioDto));
    }

    @PutMapping
    public ResponseEntity atualizaEstadio(@RequestBody EstadioDto estadioDto) {
        return ResponseEntity.status(HttpStatus.OK).body(estadioService.atualizarEstadio(estadioDto));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EstadioDto> buscaEstadio(@PathVariable Long id) {
//        return estadioRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @GetMapping
//    public ResponseEntity<Page<Estadio>> listarEstadios(
//            @RequestParam(required = false) String  nomeEstadio,
//            Pageable pageable) {
//
//        Page<Estagios> estadios = estadioService.buscarEstadios(nomeEstadio, pageable);
////        return ResponseEntity.ok(estagios);
//    }

}
