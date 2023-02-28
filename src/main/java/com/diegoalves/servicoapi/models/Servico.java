package com.diegoalves.servicoapi.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String status;


    private String inicio;


    private String termino;

    private String detalhes;


    private String notas;


    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    public Servico() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}
