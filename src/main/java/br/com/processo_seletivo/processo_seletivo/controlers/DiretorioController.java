package br.com.processo_seletivo.processo_seletivo.controlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.repositorios.DiretorioRepository;
import br.com.processo_seletivo.processo_seletivo.servicos.DiretorioService;

@RestController
@RequestMapping("/api/diretorios")
@CrossOrigin(origins = "http://localhost:3000") // Permitindo acesso do frontend
public class DiretorioController {

	@Autowired
	private DiretorioService diretorioService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@GetMapping
	public List<Diretorio> getAllDiretorios() {
		return diretorioService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Diretorio> getDiretorioById(@PathVariable Long id) {
		return diretorioService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@Autowired
	private DiretorioRepository diretorioRepository;

	@PostMapping
	public ResponseEntity<Diretorio> criarDiretorio(@RequestBody Diretorio entity) {
		Diretorio novoDiretorio = diretorioService.save(entity);
		messagingTemplate.convertAndSend("/topic/diretorios", novoDiretorio); 
		return ResponseEntity.status(HttpStatus.CREATED).body(novoDiretorio);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarDiretorio(@PathVariable Long id) {
		diretorioService.deleteById(id);
		messagingTemplate.convertAndSend("/topic/diretorios", "DELETED:" + id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/nome/{nome}")
	@Transactional(readOnly = true)
	public ResponseEntity<Diretorio> getDiretorioByNome(@PathVariable String nome) {
		Optional<Diretorio> optionalDiretorio = diretorioRepository.findByNome(nome);
		if (optionalDiretorio.isPresent()) {
			Diretorio diretorio = optionalDiretorio.get();
			diretorio.getArquivos().size();
			diretorio.getSubDiretorios().size();
			return ResponseEntity.ok(diretorio);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}