package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.model.Tarefa;
import com.exemplo.gerenciador_tarefas.service.PDFService;
import com.exemplo.gerenciador_tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final PDFService pdfService;
    private final TarefaService tarefaService;

    @Autowired
    public RelatorioController(PDFService pdfService, TarefaService tarefaService) {
        this.pdfService = pdfService;
        this.tarefaService = tarefaService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> gerarRelatorio(@RequestParam("data") String data) {
        try {
            LocalDate localDate = LocalDate.parse(data);
            List<Tarefa> tarefas = buscarTarefasPorData(localDate);

            byte[] pdf = pdfService.gerarRelatorio(localDate, tarefas);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "relatorio.pdf");

            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private List<Tarefa> buscarTarefasPorData(LocalDate data) {

        return tarefaService.obterTarefas(data);
    }
}
