package br.com.processo_seletivo.processo_seletivo.controlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.servicos.DiretorioService;

@RestController
@RequestMapping("/api/diretorios")
@CrossOrigin(origins = "http://localhost:3000") // Permitindo acesso do frontend
public class DiretorioController {

    @Autowired
    private DiretorioService diretorioService;

    @GetMapping
    public List<Diretorio> getAllDiretorios() {
        return diretorioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretorio> getDiretorioById(@PathVariable Long id) {
        return diretorioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Diretorio> getDiretorioByNome(@PathVariable String nome) {
        Optional<Diretorio> diretorioOpt = diretorioService.findByNome(nome);
        if (diretorioOpt.isPresent()) {
            return ResponseEntity.ok(diretorioOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Diretorio> createDiretorio(@RequestBody Diretorio diretorio) {
        Diretorio savedDiretorio = diretorioService.save(diretorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDiretorio);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiretorio(@PathVariable Long id) {
        diretorioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}