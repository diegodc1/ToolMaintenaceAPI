package com.diegoalves.servicoapi.controllers;

import com.diegoalves.servicoapi.dto.EquipamentoDTO;
import com.diegoalves.servicoapi.models.Equipamento;
import com.diegoalves.servicoapi.repositories.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;


    @GetMapping("/equipamentos")
    public List<Equipamento> findAll() {
        List<Equipamento> result = equipamentoRepository.findAll();
        return result;
    }

    @PostMapping("/equipamentos/new")
    public void newEquipamento(@RequestBody EquipamentoDTO requisicao){
        Equipamento equipamento = requisicao.newEquipamento();
        equipamentoRepository.save(equipamento);
    }
}
