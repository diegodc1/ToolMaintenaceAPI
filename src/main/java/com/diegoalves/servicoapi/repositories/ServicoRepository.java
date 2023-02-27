package com.diegoalves.servicoapi.repositories;

import com.diegoalves.servicoapi.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
