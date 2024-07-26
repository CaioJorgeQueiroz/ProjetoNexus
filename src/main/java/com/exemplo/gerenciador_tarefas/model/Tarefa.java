package com.exemplo.gerenciador_tarefas.model;

import java.time.LocalDate;
import java.util.Objects;

public class Tarefa {
    private String descricao;
    private boolean concluida;
    private LocalDate data;
    private String prioridade;

    // Construtor padrão
    public Tarefa() {
    }

    // Construtor com parâmetros
    public Tarefa(String descricao, boolean concluida, LocalDate data, String prioridade) {
        this.descricao = descricao;
        this.concluida = concluida;
        this.data = data;
        this.prioridade = prioridade;
    }

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(descricao, tarefa.descricao) &&
               Objects.equals(data, tarefa.data) &&
               Objects.equals(prioridade, tarefa.prioridade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, data, prioridade);
    }
}
