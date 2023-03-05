package com.diegoalves.servicoapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String problema;

    public Equipamento() {
    }

    public Equipamento(Long id, String tipo, String marca, String problema) {
        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.problema = problema;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }
}
