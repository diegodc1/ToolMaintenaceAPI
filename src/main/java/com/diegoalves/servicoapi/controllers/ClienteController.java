package com.diegoalves.servicoapi.controllers;

import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.repositories.ClienteRepository;
import com.diegoalves.servicoapi.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    //Lista todos os usuários
    @GetMapping("/clientes")
    public ResponseEntity<Object> findAll(){
        List<Cliente> resultado = clienteRepository.findAll();

        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma cliente foi encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    //Busca por um cliente pelo id
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Optional<Cliente> resultado = clienteRepository.findById(id);

        if (!resultado.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum cliente com este ID foi encontrado!");
        }
        var cliente = resultado.get();
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    //Busca por um clinte pelo email
    @GetMapping("/clientes/buscaemail/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable String email){
        List<Cliente> resultado = clienteRepository.findByEmail(email);

        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum cliente com este EMAIL foi encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }


//    Busca por um cliente pelo nome
    @GetMapping("/clientes/buscanome/{nome}")
    public ResponseEntity<Object> findByName(@PathVariable @Validated String nome){
        List<Cliente> resultado = clienteRepository.findByNome(nome);

        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum cliente com este EMAIL foi encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    //Cadastra um novo cliente
    @PostMapping("/clientes/new")
    public ResponseEntity<Object> newCliente(@RequestBody ClienteDTO requisicao){
        Cliente cliente = requisicao.newCliente();
        clienteRepository.save(cliente);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastro com sucesso!");
    }
}
