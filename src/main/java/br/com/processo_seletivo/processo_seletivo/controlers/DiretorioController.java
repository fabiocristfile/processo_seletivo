package br.com.processo_seletivo.processo_seletivo.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/diretorios")
public class DiretorioController {

    @Autowired
    private DiretorioService diretorioService;

    // Retorna todos os diretórios
    @GetMapping
    public List<Diretorio> getAllDiretorios() {
        return diretorioService.getAllDiretorios();
    }

    // Retorna um diretório pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Diretorio> getDiretorioById(@PathVariable Long id) {
        Diretorio diretorio = diretorioService.getDiretorioById(id);
        if (diretorio != null) {
            return ResponseEntity.ok(diretorio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria um novo diretório
    @PostMapping
    public Diretorio createDiretorio(@RequestBody Diretorio diretorio) {
        return diretorioService.createDiretorio(diretorio);
    }

    // Deleta um diretório pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiretorio(@PathVariable Long id) {
        diretorioService.deleteDiretorio(id);
        return ResponseEntity.noContent().build();
    }
}