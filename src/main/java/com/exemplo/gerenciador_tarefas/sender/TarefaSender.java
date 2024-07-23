package com.exemplo.gerenciador_tarefas.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarefaSender {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TarefaSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String mensagem) {
        rabbitTemplate.convertAndSend("nomeDaExchange", "nomeDaRota", mensagem);
    }
}