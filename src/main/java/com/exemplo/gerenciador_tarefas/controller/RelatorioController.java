package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.model.Tarefa;
import com.exemplo.gerenciador_tarefas.service.PDFService;
import com.exemplo.gerenciador_tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
@CrossOrigin(origins = "http://localhost:3000")
public class RelatorioController {

    private final PDFService pdfService;
    private final TarefaService tarefaService;

    @Autowired
    public RelatorioController(PDFService pdfService, TarefaService tarefaService) {
        this.pdfService = pdfService;
        this.tarefaService = tarefaService;
    }

    @PostMapping("/gerar")
    public ResponseEntity<byte[]> gerarRelatorio(@RequestParam("data") String data) {
        try {
            LocalDate localDate = LocalDate.parse(data);
            List<Tarefa> tarefas = tarefaService.obterTarefas(localDate);

            byte[] pdfBytes = pdfService.gerarRelatorio(localDate, tarefas);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
