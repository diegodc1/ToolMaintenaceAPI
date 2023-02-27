package com.diegoalves.servicoapi.repositories;

import com.diegoalves.servicoapi.models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}
