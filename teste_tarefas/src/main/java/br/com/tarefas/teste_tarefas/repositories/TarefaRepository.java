package br.com.tarefas.teste_tarefas.repositories;

import br.com.tarefas.teste_tarefas.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query("SELECT t FROM Tarefa t WHERE t.pessoa IS NULL ORDER BY t.prazo ASC")
    List<Tarefa> findTarefasSemPessoaAlocadaOrderByPrazo();

    // Consulta para obter a contagem de pessoas para cada departamento
    // Consulta para obter a contagem de tarefas para cada departamento
    @Query("SELECT t.departamento, COUNT(t) AS quantidadeTarefas " +
            "FROM Tarefa t " +
            "GROUP BY t.departamento")
    List<Object[]> contarTarefasPorDepartamento();

    @Query("SELECT t FROM Tarefa t WHERE t.pessoa.id = :pessoaId AND t.data BETWEEN :dataInicio AND :dataFim")
    List<Tarefa> findByPessoaAndPeriodo(@Param("pessoaId") Integer pessoaId, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}
