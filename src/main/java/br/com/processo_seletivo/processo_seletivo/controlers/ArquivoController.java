package br.com.processo_seletivo.processo_seletivo.controlers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

@RestController
@RequestMapping("/api/arquivos")
@CrossOrigin(origins = "http://localhost:3000") // Permitindo acesso do frontend
public class ArquivoController {

	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@PostMapping
	public ResponseEntity<Arquivo> createArquivo(@Valid @RequestBody Arquivo arquivo) {
	    Arquivo savedArquivo = arquivoService.save(arquivo);
	    messagingTemplate.convertAndSend("/topic/arquivos", savedArquivo); // Envia o arquivo criado
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedArquivo);
	}
	@GetMapping("/diretorio/{diretorioId}")
	public List<Arquivo> getArquivosByDiretorioId(@PathVariable Long diretorioId) {
		return arquivoService.findByDiretorioId(diretorioId);
	}

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
	    
	    // Envia o arquivo atualizado
	    messagingTemplate.convertAndSend("/topic/arquivos", arquivo);
	    
	    return ResponseEntity.ok(arquivo);
	}
	@GetMapping
	public List<Arquivo> getAllArquivos() {
		return arquivoService.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Arquivo> getArquivoById(@PathVariable Long id) {
		Optional<Arquivo> arquivoOpt = arquivoService.findById(id);
		if (arquivoOpt.isPresent()) {
			return ResponseEntity.ok(arquivoOpt.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletarArquivo(@PathVariable Long id) {
	    System.out.println("Tentando deletar arquivo com ID: " + id);
	    arquivoService.deleteById(id);
	    System.out.println("Arquivo com ID: " + id + " deletado com sucesso");
	    
	    // Enviar notificação via WebSocket
	    messagingTemplate.convertAndSend("/topic/arquivos", "DELETED:" + id); 
	    return ResponseEntity.noContent().build();
	}
}