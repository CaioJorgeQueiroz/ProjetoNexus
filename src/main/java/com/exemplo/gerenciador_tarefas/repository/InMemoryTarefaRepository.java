package com.exemplo.gerenciador_tarefas.repository;

import com.exemplo.gerenciador_tarefas.model.Tarefa;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class InMemoryTarefaRepository implements TarefaRepository {

    private final Map<LocalDate, List<Tarefa>> tarefasPorDia = new HashMap<>();

    @Override
    public void adicionarTarefa(LocalDate data, Tarefa tarefa) {
        tarefasPorDia.computeIfAbsent(data, k -> new ArrayList<>()).add(tarefa);
    }

    @Override
    public List<Tarefa> obterTarefas(LocalDate data) {
        return tarefasPorDia.getOrDefault(data, new ArrayList<>());
    }

    @Override
    public void atualizarTarefa(LocalDate data, Tarefa tarefa) {
        List<Tarefa> tarefas = tarefasPorDia.get(data);
        if (tarefas != null) {
            for (int i = 0; i < tarefas.size(); i++) {
                if (tarefas.get(i).equals(tarefa)) {
                    tarefas.set(i, tarefa);
                    return;
                }
            }
        }
    }

    @Override
    public void removerTarefa(LocalDate data, Tarefa tarefa) {
        List<Tarefa> tarefas = tarefasPorDia.get(data);
        if (tarefas != null) {
            tarefas.remove(tarefa);
        }
    }
}
