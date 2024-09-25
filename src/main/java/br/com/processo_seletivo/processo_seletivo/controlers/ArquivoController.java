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

import br.com.processo_seletivo.processo_seletivo.entidades.Arquivo;
import br.com.processo_seletivo.processo_seletivo.servicos.ArquivoService;

@RestController
@RequestMapping("/api/arquivos")
@CrossOrigin(origins = "http://localhost:3000") // Permitindo acesso do frontend
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping
    public ResponseEntity<Arquivo> createArquivo(@RequestBody Arquivo arquivo) {
        Arquivo savedArquivo = arquivoService.save(arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArquivo);
    }
    
    @GetMapping("/diretorio/{diretorioId}")
    public List<Arquivo> getArquivosByDiretorioId(@PathVariable Long diretorioId) {
        return arquivoService.findByDiretorioId(diretorioId);
    }

    @GetMapping
    public List<Arquivo> getAllArquivos() {
        return arquivoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arquivo> getArquivoById(@PathVariable Long id) {
        Optional<Arquivo> arquivoOpt = arquivoService.findById(id); // Agora retorna um Optional
        if (arquivoOpt.isPresent()) {
            return ResponseEntity.ok(arquivoOpt.get()); // Se o arquivo estiver presente, retorna ele
        } else {
            return ResponseEntity.notFound().build(); // Caso contrário, retorna 404
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArquivo(@PathVariable Long id) {
        arquivoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}