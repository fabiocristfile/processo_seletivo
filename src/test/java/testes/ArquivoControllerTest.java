package testes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.processo_seletivo.processo_seletivo.controlers.ArquivoController;
import br.com.processo_seletivo.processo_seletivo.entidades.Arquivo;
import br.com.processo_seletivo.processo_seletivo.servicos.ArquivoService;

public class ArquivoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ArquivoService arquivoService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private ArquivoController arquivoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(arquivoController).build();
    }

    @Test
    public void testCreateArquivo() throws Exception {
        Arquivo arquivo = new Arquivo();
        arquivo.setNome("Arquivo1");
        arquivo.setExtensao("txt");
        arquivo.setTamanho(1024L);

        when(arquivoService.save(any(Arquivo.class))).thenReturn(arquivo);

        mockMvc.perform(post("/api/arquivos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(arquivo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Arquivo1"));
    }

    @Test
    public void testAtualizarArquivo() throws Exception {
        Arquivo arquivoExistente = new Arquivo();
        arquivoExistente.setId(1L);
        arquivoExistente.setNome("Arquivo1");
        arquivoExistente.setExtensao("txt");
        arquivoExistente.setTamanho(1024L);

        Arquivo arquivoAtualizado = new Arquivo();
        arquivoAtualizado.setNome("Arquivo1 Atualizado");
        arquivoAtualizado.setExtensao("txt");
        arquivoAtualizado.setTamanho(2048L);

        when(arquivoService.findById(1L)).thenReturn(Optional.of(arquivoExistente));
        when(arquivoService.save(any(Arquivo.class))).thenReturn(arquivoAtualizado);

        mockMvc.perform(put("/api/arquivos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(arquivoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Arquivo1 Atualizado"));
    }

    @Test
    public void testGetArquivoById() throws Exception {
        Arquivo arquivo = new Arquivo();
        arquivo.setId(1L);
        arquivo.setNome("Arquivo1");

        when(arquivoService.findById(1L)).thenReturn(Optional.of(arquivo));

        mockMvc.perform(get("/api/arquivos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Arquivo1"));
    }

    @Test
    public void testDeletarArquivo() throws Exception {
        mockMvc.perform(delete("/api/arquivos/1"))
                .andExpect(status().isNoContent());

        verify(arquivoService, times(1)).deleteById(1L);
    }
}