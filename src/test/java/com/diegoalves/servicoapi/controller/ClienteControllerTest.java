package com.diegoalves.servicoapi.controller;


import com.diegoalves.servicoapi.controllers.ClienteController;
import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void givenClientExists_whenFindById_thenReturn200() throws Exception {
        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente(1L, "teste", "teste", "teste", "teste")));

        mvc.perform(get("/clientes/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenClientDontExists_whenFindById_thenReturn404() throws Exception {
        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente(1L, "teste", "teste", "teste", "teste")));

        mvc.perform(get("/clientes/7")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenClientExists_whenFindByEmail_thenReturn200() throws Exception {
        Mockito.when(clienteRepository.findByEmail("diego@alves.com"))
                .thenReturn(List.of(new Cliente(1L, "teste", "teste", "teste", "diego@alves.com")));

        mvc.perform(get("/clientes/buscaemail/diego@alves.com")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

    @Test
    public void givenClientDontExists_whenFindByEmail_thenReturn404() throws Exception {
        Mockito.when(clienteRepository.findByEmail("diegodc12@hotmail.com"))
                .thenReturn(List.of(new Cliente(1L, "teste", "teste", "teste", "diegodc12@hotmail.com")));

        mvc.perform(get("/clientes/buscaemail/diego@alves.com")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }


    @Test
    public void givenClientExists_whenFindByName_thenReturn200() throws Exception {
        Mockito.when(clienteRepository.findByNome("Diego"))
                .thenReturn(List.of(new Cliente(1L, "Diego", "teste", "teste", "diego@alves.com")));

        mvc.perform(get("/clientes/buscanome/Diego")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

}
