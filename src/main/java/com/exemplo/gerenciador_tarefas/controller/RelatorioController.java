package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final PDFService pdfService;

    @Autowired
    public RelatorioController(PDFService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> gerarRelatorio(@RequestParam String conteudo) {
        byte[] pdf = pdfService.gerarRelatorio(conteudo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }
}
