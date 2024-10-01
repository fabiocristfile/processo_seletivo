package br.com.processo_seletivo.processo_seletivo.controlers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.processo_seletivo.processo_seletivo.entidades.Arquivo;
import br.com.processo_seletivo.processo_seletivo.servicos.ArquivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/arquivos")
@CrossOrigin(origins = "http://localhost:3000") // Permitindo acesso do frontend
public class ArquivoController {

    private static final Logger logger = LoggerFactory.getLogger(ArquivoController.class);

    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Operation(summary = "Criar um novo arquivo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Arquivo criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na criação do arquivo")
    })
    
    @PostMapping
    public ResponseEntity<Arquivo> createArquivo(@Valid @RequestBody Arquivo arquivo) {
        Arquivo savedArquivo = arquivoService.save(arquivo);
        messagingTemplate.convertAndSend("/topic/arquivos", savedArquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArquivo);
    }
    
    @Operation(summary = "Obter todos os arquivos")
    @GetMapping
    public List<Arquivo> getAllArquivos() {
        return arquivoService.findAll();
    }

    @Operation(summary = "Atualizar um arquivo existente")
    @PutMapping("{id}")
    public ResponseEntity<Arquivo> atualizarArquivo(@PathVariable Long id, @RequestBody Arquivo arquivoAtualizado) {
        Optional<Arquivo> optionalArquivo = arquivoService.findById(id);

        if (!optionalArquivo.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Arquivo arquivo = optionalArquivo.get();
        arquivo.setNome(arquivoAtualizado.getNome());
        arquivo.setTamanho(arquivoAtualizado.getTamanho());

        arquivoService.save(arquivo);
        messagingTemplate.convertAndSend("/topic/arquivos", arquivo);
        return ResponseEntity.ok(arquivo);
    }

    @Operation(summary = "Obter arquivo por ID")
    @GetMapping("{id}")
    public ResponseEntity<Arquivo> getArquivoById(@PathVariable Long id) {
        Optional<Arquivo> arquivoOpt = arquivoService.findById(id);
        return arquivoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar um arquivo por ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarArquivo(@PathVariable Long id) {
        logger.info("Tentando deletar arquivo com ID: {}", id);
        arquivoService.deleteById(id);
        logger.info("Arquivo com ID: {} deletado com sucesso", id);
        messagingTemplate.convertAndSend("/topic/arquivos", "DELETED:" + id);
        return ResponseEntity.noContent().build();
    }
}