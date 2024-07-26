package com.exemplo.gerenciador_tarefas.repository;

import com.exemplo.gerenciador_tarefas.model.Tarefa;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository {
    void adicionarTarefa(LocalDate data, Tarefa tarefa);
    List<Tarefa> obterTarefas(LocalDate data);
    void atualizarTarefa(LocalDate data, Tarefa tarefa);
    void removerTarefa(LocalDate data, Tarefa tarefa);
}
