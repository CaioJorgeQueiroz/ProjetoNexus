package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.sender.TarefaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private TarefaSender tarefaSender;

    @Autowired
    public TarefaController(TarefaSender tarefaSender) {
        this.tarefaSender = tarefaSender;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarTarefa(@RequestBody String tarefa) {
        tarefaSender.sendMessage(tarefa);
        return ResponseEntity.ok("Tarefa enviada: " + tarefa);
    }
}