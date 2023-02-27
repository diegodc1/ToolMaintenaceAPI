package com.diegoalves.servicoapi.controllers;

import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.repositories.ClienteRepository;
import com.diegoalves.servicoapi.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> findAll(){
        List<Cliente> result = clienteRepository.findAll();
        return result;
    }

    @GetMapping("/clientes/{id}")
    public Cliente findById(@PathVariable Long id){
        Cliente result = clienteRepository.findById(id).get();
        return result;
    }

    @PostMapping("/clientes/new")
    public void newCliente(@RequestBody ClienteDTO requisicao){
        Cliente cliente = requisicao.newCliente();
        clienteRepository.save(cliente);
    }
}
