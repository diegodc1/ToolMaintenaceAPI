package com.diegoalves.servicoapi.dto;

import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.models.Equipamento;
import com.diegoalves.servicoapi.models.Servico;

import java.util.Date;

public class ServicoDTO {
    private Long id;
    private String status;
    private String inicio;
    private String termino;
    private String detalhes;
    private String notas;
    private Cliente cliente;
    private Equipamento equipamento;

    public ServicoDTO() {
    }

    public ServicoDTO(String status, String inicio, String termino, String detalhes, String notas, Cliente cliente, Equipamento equipamento) {
        this.status = status;
        this.inicio = inicio;
        this.termino = termino;
        this.detalhes = detalhes;
        this.notas = notas;
        this.cliente = cliente;
        this.equipamento = equipamento;
    }

    public ServicoDTO(Servico servico){
        id = servico.getId();
        status = servico.getStatus();
        inicio = servico.getInicio();
        termino = servico.getTermino();
        detalhes = servico.getDetalhes();
        notas = servico.getNotas();
        cliente = servico.getCliente();
        equipamento = servico.getEquipamento();
    }

    public Servico newServico(){
        Servico servico = new Servico();
        servico.setStatus("Pendente");
        servico.setInicio(this.inicio);
        servico.setTermino(this.termino);
        servico.setDetalhes(this.detalhes);
        servico.setNotas(this.notas);
        servico.setCliente(this.cliente);
        servico.setEquipamento(this.equipamento);

        return servico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public Long getIdEquipamento() {
        return equipamento.getId();
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}
