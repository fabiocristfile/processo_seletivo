
package br.com.processo_seletivo.processo_seletivo.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.repositorios.DiretorioRepository;

@Service
public class DiretorioService {

    private final DiretorioRepository diretorioRepository;

    // Construtor padrão usado pelo Spring

    public DiretorioService(DiretorioRepository diretorioRepository) {
        this.diretorioRepository = diretorioRepository;
    }

    // Retorna todos os diretórios
    public List<Diretorio> getAllDiretorios() {
        return diretorioRepository.findAll();
    }

    // Retorna um diretório específico pelo ID
    public Diretorio getDiretorioById(Long id) {
        return diretorioRepository.findById(id).orElse(null);
    }

    // Cria ou atualiza um diretório
    public Diretorio createDiretorio(Diretorio diretorio) {
        return diretorioRepository.save(diretorio);
    }

    // Remove um diretório pelo ID
    public void deleteDiretorio(Long id) {
        diretorioRepository.deleteById(id);
    }
}