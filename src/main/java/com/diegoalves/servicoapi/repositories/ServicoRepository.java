package com.diegoalves.servicoapi.repositories;

import com.diegoalves.servicoapi.models.Equipamento;
import com.diegoalves.servicoapi.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import java.util.Date;
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
    @Query("update Servico s set s.inicio = ?1 where s.id = ?2")
    void updateDateStart(String date, Long id);

    @Transactional
    @Modifying
    @Query("update Servico s set s.termino = ?1 where s.id = ?2")
    void updateDateFinish(String date, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Servico s SET s.status = ?1, s.termino = ?2, s.detalhes = ?3 WHERE s.id = ?4")
    void finishService(String status, String date, String detalhes, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Servico s SET s.notas = ?1 WHERE s.id = ?2")
    void updateNote(String notas, Long id);


}
