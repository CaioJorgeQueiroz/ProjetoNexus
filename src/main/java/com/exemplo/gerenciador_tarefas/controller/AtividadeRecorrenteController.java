package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.service.AtividadeRecorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/atividades-recorrentes")
@CrossOrigin(origins = "http://localhost:3000")
public class AtividadeRecorrenteController {

    private final AtividadeRecorrenteService atividadeRecorrenteService;

    @Autowired
    public AtividadeRecorrenteController(AtividadeRecorrenteService atividadeRecorrenteService) {
        this.atividadeRecorrenteService = atividadeRecorrenteService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarAtividadeRecorrente(
            @RequestParam("diaSemana") String diaSemana,
            @RequestParam("descricao") String descricao) {
        try {
            atividadeRecorrenteService.adicionarAtividade(diaSemana, descricao);
            return new ResponseEntity<>("Atividade recorrente adicionada com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao adicionar atividade recorrente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obter")
    public ResponseEntity<List<String>> obterAtividadesRecorrentes(@RequestParam("diaSemana") String diaSemana) {
        try {
            List<String> atividades = atividadeRecorrenteService.obterAtividades(diaSemana);
            return new ResponseEntity<>(atividades, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remover")
    public ResponseEntity<String> removerAtividadeRecorrente(
            @RequestParam("diaSemana") String diaSemana,
            @RequestParam("descricao") String descricao) {
        try {
            atividadeRecorrenteService.removerAtividade(diaSemana, descricao);
            return new ResponseEntity<>("Atividade recorrente removida com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao remover atividade recorrente", HttpStatus.BAD_REQUEST);
        }
    }
}
