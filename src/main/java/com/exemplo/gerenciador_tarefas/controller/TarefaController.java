package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.sender.TarefaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaSender tarefaSender;

    @PostMapping("/enviar")
    public void enviarTarefa(@RequestBody String mensagem) {
        tarefaSender.sendMessage(mensagem);
    }
}
