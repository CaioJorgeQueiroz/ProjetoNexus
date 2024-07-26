package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.service.AtividadeRecorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.Set;

@RestController
@RequestMapping("/atividades-recorrentes")
public class AtividadeRecorrenteController {

    private final AtividadeRecorrenteService atividadeRecorrenteService;

    @Autowired
    public AtividadeRecorrenteController(AtividadeRecorrenteService atividadeRecorrenteService) {
        this.atividadeRecorrenteService = atividadeRecorrenteService;
    }

    @PostMapping
    public void adicionarAtividadeRecorrente(@RequestParam("diaDaSemana") DayOfWeek diaDaSemana,
                                             @RequestParam("descricao") String descricao) {
        atividadeRecorrenteService.adicionarAtividadeRecorrente(diaDaSemana, descricao);
    }

    @GetMapping
    public Set<String> obterAtividadesRecorrentes(@RequestParam("diaDaSemana") DayOfWeek diaDaSemana) {
        return atividadeRecorrenteService.obterAtividadesRecorrentes(diaDaSemana);
    }
}
