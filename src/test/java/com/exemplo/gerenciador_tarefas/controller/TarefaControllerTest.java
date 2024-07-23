package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.sender.TarefaSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TarefaController.class)
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaSender tarefaSender;

    @InjectMocks
    private TarefaController tarefaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tarefaController).build();
    }

    @Test
    public void enviarTarefa() throws Exception {
        String mensagem = "Comprar leite";

        doNothing().when(tarefaSender).sendMessage(mensagem);

        mockMvc.perform(post("/tarefas/enviar")
                .contentType("application/json")
                .content(mensagem))
                .andExpect(status().isOk());
    }
}
