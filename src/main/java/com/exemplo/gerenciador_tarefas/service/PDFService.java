package com.exemplo.gerenciador_tarefas.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class PDFService {

    public byte[] gerarRelatorio(LocalDate data, List<String> tarefas) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("ATIVIDADES DO DIA " + data.toString()));

        for (int i = 0; i < tarefas.size(); i++) {
            document.add(new Paragraph((i + 1) + ". " + tarefas.get(i)));
        }

        document.close();
        return baos.toByteArray();
    }
}
