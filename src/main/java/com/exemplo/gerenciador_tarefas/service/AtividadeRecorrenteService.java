package com.exemplo.gerenciador_tarefas.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class AtividadeRecorrenteService {

    private final Map<DayOfWeek, List<String>> atividadesRecorrentes = new EnumMap<>(DayOfWeek.class);

    public void adicionarAtividadeRecorrente(DayOfWeek dia, String atividade) {
        atividadesRecorrentes.computeIfAbsent(dia, k -> new ArrayList<>()).add(atividade);
    }

    public List<String> obterAtividadesRecorrentes(DayOfWeek dia) {
        return atividadesRecorrentes.getOrDefault(dia, new ArrayList<>());
    }
}
