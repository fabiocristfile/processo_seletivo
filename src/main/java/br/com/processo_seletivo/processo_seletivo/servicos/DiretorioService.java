package br.com.processo_seletivo.processo_seletivo.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.repositorios.DiretorioRepository;

@Service
public class DiretorioService {

    @Autowired
    private DiretorioRepository diretorioRepository;

    public List<Diretorio> findAll() {
        return diretorioRepository.findAll();
    }

    public Optional<Diretorio> findById(Long id) {
        return diretorioRepository.findById(id); 
    }

    public Optional<Diretorio> findByNome(String nome) {
        return diretorioRepository.findByNome(nome); // Chama o método do repositório
    }

    public Diretorio save(Diretorio diretorio) {
        return diretorioRepository.save(diretorio);
    }

    public void deleteById(Long id) {
        diretorioRepository.deleteById(id);
    }
}