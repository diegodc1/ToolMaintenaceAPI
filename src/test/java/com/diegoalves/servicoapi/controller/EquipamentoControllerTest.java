package com.diegoalves.servicoapi.controller;

import com.diegoalves.servicoapi.controllers.ClienteController;
import com.diegoalves.servicoapi.controllers.EquipamentoController;
import com.diegoalves.servicoapi.dto.ClienteDTO;
import com.diegoalves.servicoapi.dto.EquipamentoDTO;
import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.models.Equipamento;
import com.diegoalves.servicoapi.repositories.EquipamentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;
import java.util.Optional;

@WebMvcTest(EquipamentoController.class)
public class EquipamentoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private EquipamentoRepository equipamentoRepository;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void givenEquipmentExists_whenFindById_thenReturn200() throws Exception{
        Mockito.when(equipamentoRepository.findById(1L)).thenReturn(Optional.of(new Equipamento(1L, "Impressora", "HP", "Não imprime")));

        mvc.perform(get("/equipamentos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void givenEquipmentDontExists_whenFindById_thenReturn404() throws Exception{
        Mockito.when(equipamentoRepository.findById(1L)).thenReturn(Optional.of(new Equipamento(1L, "Impressora", "HP", "Não imprime")));

        mvc.perform(get("/equipamentos/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void givenEquipmentExists_whenFindByType_thenReturn200() throws Exception{
        Mockito.when(equipamentoRepository.findByTipo("Impressora")).thenReturn(List.of(new Equipamento(1L, "Impressora", "HP", "Não imprime")));

        mvc.perform(get("/equipamentos/buscatipo/Impressora")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenEquipmentDontExists_whenFindByType_thenReturn404() throws Exception{
        Mockito.when(equipamentoRepository.findByTipo("computador")).thenReturn(List.of(new Equipamento(1L, "computador", "HP", "Não imprime")));

        mvc.perform(get("/equipamentos/buscatipo/Impressora")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenEquipmentExists_whenFindByBrand_thenReturn200() throws Exception{
        Mockito.when(equipamentoRepository.findByMarca("HP")).thenReturn(List.of(new Equipamento(1L, "Impressora", "HP", "Não imprime")));

        mvc.perform(get("/equipamentos/buscamarca/HP")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenEquipmentDontExists_whenFindByBrand_thenReturn404() throws Exception{
        Mockito.when(equipamentoRepository.findByMarca("Sansung")).thenReturn(List.of(new Equipamento(1L, "Sansung", "HP", "Não imprime")));

        mvc.perform(get("/equipamentos/buscamarca/HP")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenAllEquipmentsExists_thenReturn200() throws Exception {
        Mockito.when(equipamentoRepository.findAll())
                .thenReturn(List.of(new Equipamento(1L, "Sansung", "HP", "Não imprime")));

        mvc.perform(get("/equipamentos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAllEquipmentsDontExists_thenReturn404() throws Exception {
        Mockito.when(equipamentoRepository.findAll())
                .thenReturn(List.of());

        mvc.perform(get("/equipamentos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addNewEquipment_thenReturn201() throws Exception {
        EquipamentoDTO equipamentoDTO = new EquipamentoDTO(1L, "Sansung", "HP", "Não imprime");
        var equipamento = equipamentoDTO.newEquipamento();
        Mockito.when(equipamentoRepository.save(equipamento))
                .thenReturn(equipamento);

        mvc.perform(post("/equipamentos/new")
                        .content(asJsonString(equipamento))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}
