package com.exemplo.gerenciador_tarefas.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AtividadeRecorrenteService {

    private final Map<DayOfWeek, Set<String>> atividadesRecorrentes = new HashMap<>();

    public void adicionarAtividade(String diaSemana, String descricao) {
        DayOfWeek dia = DayOfWeek.valueOf(diaSemana.toUpperCase());
        atividadesRecorrentes
            .computeIfAbsent(dia, k -> new HashSet<>())
            .add(descricao);
    }

    public List<String> obterAtividades(String diaSemana) {
        DayOfWeek dia = DayOfWeek.valueOf(diaSemana.toUpperCase());
        return atividadesRecorrentes.getOrDefault(dia, new HashSet<>())
            .stream()
            .collect(Collectors.toList());
    }

    public void removerAtividade(String diaSemana, String descricao) {
        DayOfWeek dia = DayOfWeek.valueOf(diaSemana.toUpperCase());
        Set<String> atividades = atividadesRecorrentes.get(dia);
        if (atividades != null) {
            atividades.remove(descricao);
        }
    }

    public List<String> obterAtividadesRecorrentes(DayOfWeek dia) {
        return atividadesRecorrentes.getOrDefault(dia, new HashSet<>())
            .stream()
            .collect(Collectors.toList());
    }
}
