package br.com.processo_seletivo.processo_seletivo.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.processo_seletivo.processo_seletivo.entidades.Arquivo;
import br.com.processo_seletivo.processo_seletivo.repositorios.ArquivoRepository;

@RestController
@RequestMapping("/api/arquivos") 
public class ArquivoController {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @PostMapping
    public Arquivo createArquivo(@RequestBody Arquivo arquivo) {
        return arquivoRepository.save(arquivo);
    }

    @GetMapping("/diretorio/{diretorioId}")
    public List<Arquivo> getArquivosByDiretorioId(@PathVariable Long diretorioId) {
        return arquivoRepository.findByDiretorioId(diretorioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArquivo(@PathVariable Long id) {
        arquivoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}