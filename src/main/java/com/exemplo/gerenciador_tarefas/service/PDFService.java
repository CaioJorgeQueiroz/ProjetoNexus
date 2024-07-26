package com.exemplo.gerenciador_tarefas.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.exemplo.gerenciador_tarefas.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class PDFService {

    private final AtividadeRecorrenteService atividadeRecorrenteService;

    @Autowired
    public PDFService(AtividadeRecorrenteService atividadeRecorrenteService) {
        this.atividadeRecorrenteService = atividadeRecorrenteService;
    }

    public byte[] gerarRelatorio(LocalDate data, List<Tarefa> tarefas) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("ATIVIDADES DO DIA " + data.toString()));

            // Adicionar tarefas do dia específico
            for (Tarefa tarefa : tarefas) {
                document.add(new Paragraph(tarefa.getDescricao() + " - " + tarefa.getPrioridade() + " - " + (tarefa.isConcluida() ? "Concluída" : "Não Concluída")));
            }

            // Adicionar atividades recorrentes para o dia da semana
            DayOfWeek diaDaSemana = data.getDayOfWeek();
            Set<String> atividadesRecorrentes = atividadeRecorrenteService.obterAtividadesRecorrentes(diaDaSemana);
            if (!atividadesRecorrentes.isEmpty()) {
                document.add(new Paragraph("Atividades Recorrentes:"));
                for (String atividade : atividadesRecorrentes) {
                    document.add(new Paragraph(atividade + " - Recorrente"));
                }
            }

            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
