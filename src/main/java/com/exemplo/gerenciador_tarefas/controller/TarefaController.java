package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.service.AtividadeRecorrenteService;
import com.exemplo.gerenciador_tarefas.service.PDFService;
import com.exemplo.gerenciador_tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final PDFService pdfService;
    private final TarefaService tarefaService;
    private final AtividadeRecorrenteService atividadeRecorrenteService;

    @Autowired
    public TarefaController(PDFService pdfService, TarefaService tarefaService, AtividadeRecorrenteService atividadeRecorrenteService) {
        this.pdfService = pdfService;
        this.tarefaService = tarefaService;
        this.atividadeRecorrenteService = atividadeRecorrenteService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarTarefa(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestBody String tarefa) {
        tarefaService.adicionarTarefa(data, tarefa);
        return ResponseEntity.ok("Tarefa enviada: " + tarefa);
    }

    @PostMapping("/enviarRecorrente")
    public ResponseEntity<String> enviarTarefaRecorrente(
            @RequestParam DayOfWeek dia,
            @RequestBody String tarefa) {
        atividadeRecorrenteService.adicionarAtividadeRecorrente(dia, tarefa);
        return ResponseEntity.ok("Tarefa recorrente enviada: " + tarefa + " para " + dia);
    }

    @GetMapping("/relatorio")
    public ResponseEntity<byte[]> gerarRelatorio(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<String> tarefas = tarefaService.obterTarefas(data);
        byte[] pdf = pdfService.gerarRelatorio(data, tarefas);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio-" + data + ".pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }

    @GetMapping("/tarefasRecorrentes")
    public ResponseEntity<List<String>> obterTarefasRecorrentes(
            @RequestParam DayOfWeek dia) {
        List<String> tarefas = atividadeRecorrenteService.obterAtividadesRecorrentes(dia);
        return ResponseEntity.ok(tarefas);
    }
}
