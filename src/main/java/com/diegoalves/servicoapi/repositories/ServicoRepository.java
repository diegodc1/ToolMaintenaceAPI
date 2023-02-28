package com.diegoalves.servicoapi.repositories;

import com.diegoalves.servicoapi.models.Equipamento;
import com.diegoalves.servicoapi.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    public List<Servico> findByStatus(String status);

    public List<Servico> findByEquipamentoIdAndStatus(Long id, String status);

    @Transactional
    @Modifying
    @Query("update Servico s set s.status = ?1 where s.id = ?2")
    void updateStatus(String status, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Servico s SET s.notas = ?1 WHERE s.id = ?2")
    void updateNote(String notas, Long id);


}
