package com.diegoalves.servicoapi.repositories;

import com.diegoalves.servicoapi.models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    public List<Equipamento> findByTipo(String tipo);
    public List<Equipamento> findByMarca(String marca);
}
