package testes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
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

import br.com.processo_seletivo.processo_seletivo.controlers.DiretorioController;
import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.servicos.DiretorioService;

public class DiretorioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DiretorioService diretorioService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private DiretorioController diretorioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(diretorioController).build();
    }

    @Test
    public void testCriarDiretorio() throws Exception {
        Diretorio diretorio = new Diretorio();
        diretorio.setNome("Diretorio1");

        when(diretorioService.save(any(Diretorio.class))).thenReturn(diretorio);

        mockMvc.perform(post("/api/diretorios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(diretorio)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Diretorio1"));
    }

    @Test
    public void testGetDiretorioById() throws Exception {
        Diretorio diretorio = new Diretorio();
        diretorio.setId(1L);
        diretorio.setNome("Diretorio1");

        when(diretorioService.findById(1L)).thenReturn(Optional.of(diretorio));

        mockMvc.perform(get("/api/diretorios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Diretorio1"));
    }

    @Test
    public void testDeletarDiretorio() throws Exception {
        mockMvc.perform(delete("/api/diretorios/1"))
                .andExpect(status().isNoContent());

        verify(diretorioService, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllDiretorios() throws Exception {
        when(diretorioService.findAll()).thenReturn(Arrays.asList(new Diretorio(), new Diretorio()));

        mockMvc.perform(get("/api/diretorios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}