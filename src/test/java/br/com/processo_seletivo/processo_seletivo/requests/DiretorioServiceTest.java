package br.com.processo_seletivo.processo_seletivo.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.repositorios.DiretorioRepository;
import br.com.processo_seletivo.processo_seletivo.servicos.DiretorioService;

class DiretorioServiceTest {

    @Mock
    private DiretorioRepository diretorioRepository;

    @InjectMocks
    private DiretorioService diretorioService;

    private Diretorio diretorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        diretorio = new Diretorio();
        diretorio.setId(1L);
        diretorio.setNome("Diretorio Teste");
    }

    @Test
    void testGetAllDiretorios() {
        List<Diretorio> lista = new ArrayList<>();
        lista.add(diretorio);

        when(diretorioRepository.findAll()).thenReturn(lista);

        List<Diretorio> result = diretorioService.getAllDiretorios();
        assertEquals(1, result.size());
        assertEquals("Diretorio Teste", result.get(0).getNome());
    }

    @Test
    void testGetDiretorioById() {
        when(diretorioRepository.findById(1L)).thenReturn(Optional.of(diretorio));

        Diretorio result = diretorioService.getDiretorioById(1L);
        assertNotNull(result);
        assertEquals("Diretorio Teste", result.getNome());
    }

    @Test
    void testCreateDiretorio() {
        when(diretorioRepository.save(diretorio)).thenReturn(diretorio);

        Diretorio result = diretorioService.createDiretorio(diretorio);
        assertNotNull(result);
        assertEquals("Diretorio Teste", result.getNome());
    }

    @Test
    void testDeleteDiretorio() {
        // Configurar o comportamento do mock
        doNothing().when(diretorioRepository).deleteById(1L);

        // Executar o método de deleção
        diretorioService.deleteDiretorio(1L);
        
        // Verificar se o método deleteById foi chamado uma vez
        verify(diretorioRepository, times(1)).deleteById(1L);
    }
}
