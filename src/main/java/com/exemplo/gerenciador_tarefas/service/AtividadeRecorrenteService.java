package com.exemplo.gerenciador_tarefas.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AtividadeRecorrenteService {

    private final Map<DayOfWeek, Set<String>> atividadesRecorrentes = new HashMap<>();

    public void adicionarAtividadeRecorrente(DayOfWeek diaDaSemana, String descricao) {
        atividadesRecorrentes.computeIfAbsent(diaDaSemana, k -> new HashSet<>()).add(descricao);
    }

    public Set<String> obterAtividadesRecorrentes(DayOfWeek diaDaSemana) {
        return atividadesRecorrentes.getOrDefault(diaDaSemana, new HashSet<>());
    }
}
