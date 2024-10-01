package br.com.processo_seletivo.processo_seletivo.controlers;

import java.util.List;
import java.util.Optional;

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

import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.servicos.DiretorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/diretorios")
@CrossOrigin(origins = "http://localhost:3000") // Permitindo acesso do frontend
public class DiretorioController {

	    @Autowired
	    private DiretorioService diretorioService;

	    @Autowired
	    private SimpMessagingTemplate messagingTemplate;
	    
	    @Operation(summary = "Criar um novo Diretorio")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Diretorio criado com sucesso"),
	        @ApiResponse(responseCode = "400", description = "Erro na criacao do diretorio")
	    })

	    @PostMapping
	    public ResponseEntity<Diretorio> criarDiretorio(@RequestBody Diretorio entity) {
	        Diretorio novoDiretorio = diretorioService.save(entity);
	        messagingTemplate.convertAndSend("/topic/diretorios", novoDiretorio);
	        return ResponseEntity.status(HttpStatus.CREATED).body(novoDiretorio);
	    }

	    @Operation(summary = "Obter todos os diretorio")
	    @GetMapping
	    public List<Diretorio> getAllDiretorios() {
	        return diretorioService.findAll();
	    }

	    @Operation(summary = "Obter diretorio por ID")
	    @GetMapping("/{id}")
	    public ResponseEntity<Diretorio> getDiretorioById(@PathVariable Long id) {
	        return diretorioService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }
	    
	    @Operation(summary = "Atualizar um diretorio existente")
	    @PutMapping("{id}")
	    public ResponseEntity<Diretorio> atualizarDiretorio(@PathVariable Long id, @RequestBody Diretorio diretorioAtualizado) {
	        Optional<Diretorio> optionalDiretorio = diretorioService.findById(id);

	        if (!optionalDiretorio.isPresent()) {
	            return ResponseEntity.notFound().build();
	        }

	        Diretorio diretorio = optionalDiretorio.get();
	        diretorio.setNome(diretorioAtualizado.getNome());
	        diretorio.setSubDiretorios(diretorioAtualizado.getSubDiretorios());
	        diretorio.setDiretorioPai(diretorioAtualizado.getDiretorioPai());

	        diretorioService.save(diretorio);
	        messagingTemplate.convertAndSend("/topic/diretorios", diretorio);
	        return ResponseEntity.ok(diretorio);
	    }

	    @Operation(summary = "Deletar um diretorio por ID")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletarDiretorio(@PathVariable Long id) {
	        diretorioService.deleteById(id);
	        messagingTemplate.convertAndSend("/topic/diretorios", "DELETED:" + id);
	        return ResponseEntity.noContent().build();
	    }

	    @Operation(summary = "Busca um diretorio por Nome")
	    @GetMapping("/nome/{nome}")
	    public ResponseEntity<Diretorio> getDiretorioByNome(@PathVariable String nome) {
	        Optional<Diretorio> optionalDiretorio = diretorioService.findByNome(nome);
	        return optionalDiretorio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	}