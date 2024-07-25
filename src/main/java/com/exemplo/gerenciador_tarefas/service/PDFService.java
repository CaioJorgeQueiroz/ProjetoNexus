package com.exemplo.gerenciador_tarefas.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class PDFService {

    @Cacheable(value = "relatoriosPorDia", key = "#data")
    public byte[] gerarRelatorio(LocalDate data, List<String> tarefas) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter writer = new PdfWriter(baos);
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));
            document.add(new Paragraph("ATIVIDADES DO DIA " + data.toString()));
            for (int i = 0; i < tarefas.size(); i++) {
                document.add(new Paragraph((i + 1) + ". " + tarefas.get(i)));
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
