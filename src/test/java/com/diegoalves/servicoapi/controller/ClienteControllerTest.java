package com.diegoalves.servicoapi.controller;


import com.diegoalves.servicoapi.controllers.ClienteController;
import com.diegoalves.servicoapi.dto.ClienteDTO;
import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.repositories.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.util.json.JSONString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private ClienteRepository clienteRepository;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

    @Test
    public void givenClientDontExists_whenFindByName_thenReturn404() throws Exception {
        Mockito.when(clienteRepository.findByNome("Diego Alves"))
                .thenReturn(List.of(new Cliente(1L, "Diego Alves", "teste", "teste", "diego@alves.com")));

        mvc.perform(get("/clientes/buscanome/Diego")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenAllClientExists_thenReturn200() throws Exception {
        Mockito.when(clienteRepository.findAll())
                .thenReturn(List.of(new Cliente(1L, "Diego Alves", "teste", "teste", "diego@alves.com")));

        mvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAllClientDontExists_thenReturn404() throws Exception {
        Mockito.when(clienteRepository.findAll())
                .thenReturn(List.of());

        mvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }



    @Test
    public void addNewClient_thenReturn201() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Diego", "Rua 123", "41999999999", "diego@gmail.com");
        var cliente = clienteDTO.newCliente();
        Mockito.when(clienteRepository.save(cliente))
                .thenReturn(cliente);

        mvc.perform(post("/clientes/new")
                        .content(asJsonString(cliente))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
