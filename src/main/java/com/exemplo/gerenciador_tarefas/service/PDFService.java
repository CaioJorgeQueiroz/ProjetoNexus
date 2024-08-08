package com.exemplo.gerenciador_tarefas.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.exemplo.gerenciador_tarefas.model.Tarefa;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class PDFService {

    public byte[] gerarRelatorio(LocalDate data, List<Tarefa> tarefas) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            document.add(new Paragraph("Relatório de Tarefas"));
            document.add(new Paragraph("Data: " + data.toString()));

            for (Tarefa tarefa : tarefas) {
                document.add(new Paragraph("Tarefa: " + tarefa.getDescricao()));
            }

            document.close();
        }

        return outputStream.toByteArray();
    }
}
