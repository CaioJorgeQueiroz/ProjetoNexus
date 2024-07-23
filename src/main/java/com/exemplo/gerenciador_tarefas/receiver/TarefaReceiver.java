package com.exemplo.gerenciador_tarefas.receiver;

import org.springframework.stereotype.Component;

@Component
public class TarefaReceiver {

    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}

