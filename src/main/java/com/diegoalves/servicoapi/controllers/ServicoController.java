package com.diegoalves.servicoapi.controllers;

import com.diegoalves.servicoapi.dto.ClienteDTO;
import com.diegoalves.servicoapi.dto.ServicoDTO;
import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.models.Servico;
import com.diegoalves.servicoapi.repositories.ServicoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;


    //Lista todos os serviços
    @GetMapping("/servicos")
    public ResponseEntity<Object> findAll(){
        List<Servico> resultado = servicoRepository.findAll();
        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma ordem de serviço foi encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }


    //Lista todos os serviços pendentes
    @GetMapping("/servicos/pendentes")
    public ResponseEntity<Object> findAllPending(){
        List<Servico> resultado = servicoRepository.findByStatus("Pendente");
        if(resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma ordem de serviço pendente foi encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    //Lista todos os serviços ativos
    @GetMapping("/servicos/ativos")
    public ResponseEntity<Object> findAllActive(){
        List<Servico> resultado = servicoRepository.findByStatus("Ativo");
        if(resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma ordem de serviço ativa foi encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    //Lista todos os serviços pausados
    @GetMapping("/servicos/pausados")
    public ResponseEntity<Object> findAllPause(){
        List<Servico> resultado = servicoRepository.findByStatus("Pausado");
        if(resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma ordem de serviço pausada foi encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    //Lista todos os serviços finalizados
    @GetMapping("/servicos/finalizados")
    public ResponseEntity<Object> findAllFinish(){
        List<Servico> resultado = servicoRepository.findByStatus("Finalizado");
        if(resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma ordem de serviço finalizada foi encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }


    //Busca um servico especifico pelo id
    @GetMapping("/servicos/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Optional<Servico> resultado = servicoRepository.findById(id);
        if (!resultado.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma ordem de serviço com este ID foi encontrado!");
        }

        var servico = resultado.get();
        return ResponseEntity.status(HttpStatus.OK).body(servico);
    }


    //Iniciar um ordem de serviço
    @PostMapping("/servicos/iniciar/{id}")
    public ResponseEntity<Object> startService(@PathVariable Long id){
        try {
            if (servicoRepository.findById(id).isPresent()){
                servicoRepository.updateStatus("Ativo", id);
                Date dataHoraAtual = new Date();
                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                servicoRepository.updateDateStart(data, id);
                return  ResponseEntity.status(HttpStatus.OK).body("Ordem de serviço iniciada!");
            } else {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de serviço não encontrada! Confira se o id está correto!");
            }
        } catch (DataAccessException ex) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.toString());
        }
    }

    //Finalizar uma ordem de serviço
    @PostMapping("/servicos/finalizar/{id}")
    public ResponseEntity<Object> finishService(@PathVariable Long id, @RequestBody ServicoDTO requiscao){
        try {
            if (servicoRepository.findById(id).isPresent()){
                Date dataHoraAtual = new Date();
                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);

                servicoRepository.finishService("Finalizado", data, requiscao.getDetalhes(), id);

                return  ResponseEntity.status(HttpStatus.OK).body("Ordem de serviço finalizada!");
            } else {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de serviço não encontrada! Confira se o id está correto!");
            }
        } catch (DataAccessException ex) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.toString());
        }
    }


    //Pausar uma ordem de serviço informando o motivo
    @PostMapping("/servicos/pausar/{id}")
    public ResponseEntity<Object> pauseService(@PathVariable Long id, @RequestBody ServicoDTO requisicao){
        try {
            if (servicoRepository.findById(id).isPresent()){
                servicoRepository.updateNote(requisicao.getNotas(), id);
                servicoRepository.updateStatus("Pausado", id);
                return  ResponseEntity.status(HttpStatus.OK).body("Ordem de serviço pausada!");
            } else {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de serviço não encontrada! Confira se o id está correto!");
            }
        } catch (DataAccessException ex) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.toString());
        }
    }


    //Despausar uma ordem de serviço
    @PostMapping("/servicos/reativar/{id}")
    public ResponseEntity<Object> reactivateService(@PathVariable Long id){
        try {
            if (servicoRepository.findById(id).isPresent()){
                servicoRepository.updateNote("", id);
                servicoRepository.updateStatus("Ativo", id);
                return  ResponseEntity.status(HttpStatus.OK).body("Ordem de serviço reativada!");
            } else {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de serviço não encontrada! Confira se o id está correto!");
            }
        } catch (DataAccessException ex) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.toString());
        }
    }


    //Cancelar uma ordem de serviço
    @PostMapping("/servicos/cancelar/{id}")
    public ResponseEntity<Object> cancelService(@PathVariable Long id){
        try {
            if (servicoRepository.findById(id).isPresent()){
                servicoRepository.updateStatus("Cancelada", id);
                return  ResponseEntity.status(HttpStatus.OK).body("Ordem de serviço cancelada!");
            } else {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de serviço não encontrada! Confira se o id está correto!");
            }
        } catch (DataAccessException ex) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.toString());
        }
    }


    //Adiciona um novo serviço
    @PostMapping("/servicos/new")
    public ResponseEntity<Object> newServico(@RequestBody ServicoDTO requisicao){
        Servico servico = requisicao.newServico();

        if (!servicoRepository.findByEquipamentoIdAndStatus(requisicao.getIdEquipamento(), "Ativo").isEmpty() ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Este equipamento já está em uma ordem de serviço ativa ou pendente");
        }
        servicoRepository.save(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ordem de serviço adicionada com sucesso!");
    }


    //Atualiza um serviço pelo id
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

