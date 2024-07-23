package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.sender.TarefaSender;
import com.exemplo.gerenciador_tarefas.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaSender tarefaSender;
    private final PDFService pdfService;
    private final Map<LocalDate, List<String>> tarefasPorDia = new HashMap<>();

    @Autowired
    public TarefaController(TarefaSender tarefaSender, PDFService pdfService) {
        this.tarefaSender = tarefaSender;
        this.pdfService = pdfService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarTarefa(@RequestBody String tarefa) {
        LocalDate hoje = LocalDate.now();
        tarefasPorDia.putIfAbsent(hoje, new ArrayList<>());
        tarefasPorDia.get(hoje).add(tarefa);
        tarefaSender.sendMessage(tarefa);
        return ResponseEntity.ok("Tarefa enviada: " + tarefa);
    }

    @GetMapping("/relatorio")
    public ResponseEntity<byte[]> gerarRelatorio() {
        LocalDate hoje = LocalDate.now();
        List<String> tarefasDoDia = tarefasPorDia.getOrDefault(hoje, new ArrayList<>());
        byte[] pdf = pdfService.gerarRelatorio(hoje, tarefasDoDia);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio_" + hoje + ".pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }
}
