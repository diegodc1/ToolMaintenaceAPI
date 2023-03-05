package com.diegoalves.servicoapi.controllers;

import com.diegoalves.servicoapi.dto.EquipamentoDTO;
import com.diegoalves.servicoapi.models.Equipamento;
import com.diegoalves.servicoapi.repositories.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;


    //Lista todos os equipamentos
    @GetMapping("/equipamentos")
    public ResponseEntity<Object> findAll() {
        List<Equipamento> resultado = equipamentoRepository.findAll();

        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum equipamento foi encontrado!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    //Cadastro um novo equipamento
    @PostMapping("/equipamentos/new")
    public ResponseEntity<Object> newEquipamento(@RequestBody EquipamentoDTO requisicao){
        Equipamento equipamento = requisicao.newEquipamento();
        equipamentoRepository.save(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Equipamento cadastro com sucesso!");
    }

    //Busca por um equipamento pelo ID
    @GetMapping("/equipamentos/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Optional<Equipamento> resultado = equipamentoRepository.findById(id);
        if (!resultado.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum equipamento com este ID foi encontrado!");
        }
        var equipamento = resultado.get();
        return ResponseEntity.status(HttpStatus.OK).body(equipamento);
    }


    //Busca por um equipamento pelo tipo
    @GetMapping("/equipamentos/buscatipo/{tipo}")
    public ResponseEntity<Object> findByTipo(@PathVariable String tipo){
        List<Equipamento> resultado = equipamentoRepository.findByTipo(tipo);
        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum equipamento deste tipo foi encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    //Busca por um equipamento pela marca
    @GetMapping("/equipamentos/buscamarca/{marca}")
    public ResponseEntity<Object> findByMarca(@PathVariable String marca){
        List<Equipamento> resultado = equipamentoRepository.findByMarca(marca);
        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum equipamento desta marca foi encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}
