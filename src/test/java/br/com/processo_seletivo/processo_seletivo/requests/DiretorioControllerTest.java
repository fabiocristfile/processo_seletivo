package br.com.processo_seletivo.processo_seletivo.requests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.processo_seletivo.processo_seletivo.controlers.DiretorioController;
import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.servicos.DiretorioService;

@WebMvcTest(DiretorioController.class)
public class DiretorioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DiretorioService diretorioService;

    @InjectMocks
    private DiretorioController diretorioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarDiretorios() throws Exception {
        // Simular retorno do serviço
        Diretorio root = new Diretorio();
        root.setId(1L);
        root.setNome("Root");

        when(diretorioService.getAllDiretorios()).thenReturn(Collections.singletonList(root));

        // Fazer a chamada GET e verificar o resultado
        mockMvc.perform(get("/api/diretorios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Root"));

        verify(diretorioService, times(1)).getAllDiretorios();
    }
}