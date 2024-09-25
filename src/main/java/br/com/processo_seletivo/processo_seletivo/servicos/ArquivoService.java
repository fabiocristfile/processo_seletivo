package br.com.processo_seletivo.processo_seletivo.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processo_seletivo.processo_seletivo.entidades.Arquivo;
import br.com.processo_seletivo.processo_seletivo.repositorios.ArquivoRepository;

@Service
public class ArquivoService implements CrudService<Arquivo, Long> {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Override
    public Arquivo save(Arquivo entity) {
        return arquivoRepository.save(entity);
    }

    @Override
    public Optional<Arquivo> findById(Long id) {
        return arquivoRepository.findById(id);
    }
    
    @Override
    public List<Arquivo> findAll() {
        return arquivoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        arquivoRepository.deleteById(id);
    }

    public List<Arquivo> findByDiretorioId(Long diretorioId) {
        return arquivoRepository.findByDiretorioId(diretorioId);
    }
}