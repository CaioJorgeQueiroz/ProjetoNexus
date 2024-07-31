package com.exemplo.gerenciador_tarefas.service;

import com.exemplo.gerenciador_tarefas.model.Tarefa;
import com.exemplo.gerenciador_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }


    public void adicionarTarefa(LocalDate data, Tarefa tarefa) {
        tarefaRepository.adicionarTarefa(data, tarefa);
    }

    public List<Tarefa> obterTarefas(LocalDate data) {
        return tarefaRepository.obterTarefas(data);
    }

    public void atualizarTarefa(LocalDate data, Tarefa tarefa) {
        tarefaRepository.atualizarTarefa(data, tarefa);
    }

    public void removerTarefa(LocalDate data, Tarefa tarefa) {
        tarefaRepository.removerTarefa(data, tarefa);
    }

    public void marcarComoConcluida(LocalDate data, String descricao) {
        List<Tarefa> tarefas = tarefaRepository.obterTarefas(data);
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getDescricao().equals(descricao)) {
                tarefa.setConcluida(true);
                tarefaRepository.atualizarTarefa(data, tarefa);
                return;
            }
        }
    }
}
