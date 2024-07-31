package com.exemplo.gerenciador_tarefas.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Cacheable("dados")
    public String obterDados(String parametro) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Dados para " + parametro;
    }
}
