package com.diegoalves.servicoapi.repositories;

import com.diegoalves.servicoapi.models.Cliente;
import com.diegoalves.servicoapi.models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("SELECT * FROM cliente c WHERE c.nome LIKE '%?1%'")
    public List<Cliente> findByName(String nome);

}
