package com.diegoalves.servicoapi.repositories;

import com.diegoalves.servicoapi.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
