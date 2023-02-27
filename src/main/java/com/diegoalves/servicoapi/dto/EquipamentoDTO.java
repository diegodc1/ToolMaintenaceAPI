package com.diegoalves.servicoapi.dto;

import com.diegoalves.servicoapi.models.Equipamento;

public class EquipamentoDTO {
    private Long id;

    private String tipo;

    private String marca;

    private String problema;

    public EquipamentoDTO(Long id, String tipo, String marca, String problema) {
        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.problema = problema;
    }

    public EquipamentoDTO(Equipamento equipamento){
        id = equipamento.getId();
        tipo = equipamento.getTipo();
        marca = equipamento.getMarca();
        problema = equipamento.getProblema();
    }

    public Equipamento newEquipamento(){
        Equipamento equipamento = new Equipamento();
        equipamento.setTipo(this.tipo);
        equipamento.setMarca(this.marca);
        equipamento.setProblema(this.problema);

        return equipamento;
    }
}
