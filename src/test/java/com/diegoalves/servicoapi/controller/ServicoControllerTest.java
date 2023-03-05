package com.diegoalves.servicoapi.controller;

import com.diegoalves.servicoapi.controllers.ServicoController;
import com.diegoalves.servicoapi.dto.ClienteDTO;
import com.diegoalves.servicoapi.dto.ServicoDTO;
import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.models.Equipamento;
import com.diegoalves.servicoapi.models.Servico;
import com.diegoalves.servicoapi.repositories.ServicoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServicoController.class)
public class ServicoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ServicoRepository servicoRepository;

    Cliente cliente = new Cliente();
    Equipamento equipamento = new Equipamento();

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void givenServiceExists_whenFindById_thenReturn200() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "teste", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findById(1L))
                .thenReturn(Optional.of(servico));

        mvc.perform(get("/servicos/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenServiceDontExists_whenFindById_thenReturn404() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "teste", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findById(1L))
                .thenReturn(Optional.of(servico));

        mvc.perform(get("/servicos/3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenAllServiceExists_thenReturn200() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "teste", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findAll())
                .thenReturn(List.of(servico));

        mvc.perform(get("/servicos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAllServiceDontExists_thenReturn404() throws Exception {
        Mockito.when(servicoRepository.findAll())
                .thenReturn(List.of());

        mvc.perform(get("/servicos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenAllServiceExists_whenFindByPending_thenReturn200() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "Pendente", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findByStatus("Pendente"))
                .thenReturn(List.of(servico));

        mvc.perform(get("/servicos/pendentes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAllServiceDontExists_whenFindByPending_thenReturn404() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "Pendente", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findByStatus("teste"))
                .thenReturn(List.of(servico));

        mvc.perform(get("/servicos/pendentes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenAllServiceExists_whenFindByActive_thenReturn200() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "Ativo", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findByStatus("Ativo"))
                .thenReturn(List.of(servico));

        mvc.perform(get("/servicos/ativos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAllServiceDontExists_whenFindByActive_thenReturn404() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "Pendente", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findByStatus("teste"))
                .thenReturn(List.of(servico));

        mvc.perform(get("/servicos/ativos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenAllServiceExists_whenFindByPaused_thenReturn200() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "Pausada", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findByStatus("Pausado"))
                .thenReturn(List.of(servico));

        mvc.perform(get("/servicos/pausados")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

    @Test
    public void givenAllServiceDontExists_whenFindByPaused_thenReturn404() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "Pausada", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findByStatus("teste"))
                .thenReturn(List.of(servico));

        mvc.perform(get("/servicos/pausados")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenAllServiceExists_whenFindByFinalized_thenReturn200() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "Finalizado", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findByStatus("Finalizado"))
                .thenReturn(List.of(servico));

        mvc.perform(get("/servicos/finalizados")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAllServiceDontExists_whenFindByFinalized_thenReturn200() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        Servico servico = new Servico(1L, "Finalizado", "teste", "teste", "teste", "teste", cliente, equipamento);

        Mockito.when(servicoRepository.findByStatus("teste"))
                .thenReturn(List.of(servico));

        mvc.perform(get("/servicos/finalizados")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addNewService_thenReturn201() throws Exception {
        Cliente cliente = new Cliente(1L, "Diego", "teste", "teste", "teste");
        Equipamento equipamento = new Equipamento(1L, "Impressora", "HP", "Quebrado");
        ServicoDTO servicoDTO = new ServicoDTO(1L, "Finalizado", "teste", "teste", "teste", "teste", cliente, equipamento);
        var servico = servicoDTO.newServico();

        Mockito.when(servicoRepository.save(servico))
                .thenReturn(servico);

        mvc.perform(post("/servicos/new")
                        .content(asJsonString(servico))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
