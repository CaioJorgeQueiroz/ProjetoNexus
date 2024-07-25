package com.exemplo.gerenciador_tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TarefaService {

    private final Map<LocalDate, List<String>> tarefasPorDia = new ConcurrentHashMap<>();
    private final AtividadeRecorrenteService atividadeRecorrenteService;

    @Autowired
    public TarefaService(AtividadeRecorrenteService atividadeRecorrenteService) {
        this.atividadeRecorrenteService = atividadeRecorrenteService;
    }

    public void adicionarTarefa(LocalDate data, String tarefa) {
        tarefasPorDia.computeIfAbsent(data, k -> new ArrayList<>()).add(tarefa);
    }

    public List<String> obterTarefas(LocalDate data) {
        List<String> tarefas = new ArrayList<>(tarefasPorDia.getOrDefault(data, new ArrayList<>()));
        List<String> recorrentes = atividadeRecorrenteService.obterAtividadesRecorrentes(data.getDayOfWeek());
        tarefas.addAll(recorrentes);
        return tarefas;
    }
}
