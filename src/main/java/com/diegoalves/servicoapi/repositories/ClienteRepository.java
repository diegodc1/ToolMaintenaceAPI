package com.diegoalves.servicoapi.repositories;

import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findByEmail(String email);

    public List<Cliente> findByNome(String nome);

}
