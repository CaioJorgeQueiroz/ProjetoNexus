package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.model.Tarefa;
import com.exemplo.gerenciador_tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    // Adiciona uma nova tarefa
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarTarefa(
            @RequestParam("data") String data,
            @RequestParam("descricao") String descricao,
            @RequestParam("prioridade") String prioridade) {
        try {
            LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
            Tarefa tarefa = new Tarefa(descricao, false, localDate, prioridade);
            tarefaService.adicionarTarefa(localDate, tarefa);
            return new ResponseEntity<>("Tarefa adicionada com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao adicionar tarefa", HttpStatus.BAD_REQUEST);
        }
    }

    // Obtém todas as tarefas para uma data específica
    @GetMapping("/obter")
    public ResponseEntity<List<Tarefa>> obterTarefas(@RequestParam("data") String data) {
        try {
            LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
            List<Tarefa> tarefas = tarefaService.obterTarefas(localDate);
            return new ResponseEntity<>(tarefas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Atualiza uma tarefa
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarTarefa(
            @RequestParam("data") String data,
            @RequestParam("descricao") String descricao,
            @RequestParam("concluida") boolean concluida,
            @RequestParam("prioridade") String prioridade) {
        try {
            LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
            Tarefa tarefa = new Tarefa(descricao, concluida, localDate, prioridade);
            tarefaService.atualizarTarefa(localDate, tarefa);
            return new ResponseEntity<>("Tarefa atualizada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar tarefa", HttpStatus.BAD_REQUEST);
        }
    }

    // Remove uma tarefa
    @DeleteMapping("/remover")
    public ResponseEntity<String> removerTarefa(
            @RequestParam("data") String data,
            @RequestParam("descricao") String descricao) {
        try {
            LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
            Tarefa tarefa = new Tarefa(descricao, false, localDate, "");
            tarefaService.removerTarefa(localDate, tarefa);
            return new ResponseEntity<>("Tarefa removida com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao remover tarefa", HttpStatus.BAD_REQUEST);
        }
    }

    // Marca uma tarefa como concluída
    @PostMapping("/marcar-concluida")
    public ResponseEntity<String> marcarComoConcluida(
            @RequestParam("data") String data,
            @RequestParam("descricao") String descricao) {
        try {
            LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
            tarefaService.marcarComoConcluida(localDate, descricao);
            return new ResponseEntity<>("Tarefa marcada como concluída com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao marcar tarefa como concluída", HttpStatus.BAD_REQUEST);
        }
    }
}
