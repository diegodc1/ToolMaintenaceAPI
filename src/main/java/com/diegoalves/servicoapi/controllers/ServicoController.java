package com.diegoalves.servicoapi.controllers;

import com.diegoalves.servicoapi.dto.ClienteDTO;
import com.diegoalves.servicoapi.dto.ServicoDTO;
import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.models.Servico;
import com.diegoalves.servicoapi.repositories.ServicoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("/servicos")
    public List<Servico> findAll(){
        List<Servico> resultado = servicoRepository.findAll();
        return resultado;
    }

    @PostMapping("/servicos/new")
    public void newServico(@RequestBody ServicoDTO requisicao){
        Servico servico = requisicao.newServico();
        servicoRepository.save(servico);
    }

    @PutMapping("/servicos/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ServicoDTO requisicao){
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        if (!servicoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de serviço não encontrada!");
        }
        var servico = servicoOptional.get();

        servico.setStatus(requisicao.getStatus());
        servico.setInicio(requisicao.getInicio());
        servico.setTermino(requisicao.getTermino());
        servico.setDetalhes(requisicao.getDetalhes());
        servico.setNotas(requisicao.getNotas());
        servico.setCliente(requisicao.getCliente());
        servico.setEquipamento(requisicao.getEquipamento());


        return ResponseEntity.status(HttpStatus.OK).body(servicoRepository.save(servico));
    }
}

