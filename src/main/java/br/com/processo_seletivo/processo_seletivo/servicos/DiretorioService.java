package br.com.processo_seletivo.processo_seletivo.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;
import br.com.processo_seletivo.processo_seletivo.repositorios.DiretorioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DiretorioService implements CrudService<Diretorio, Long> {

	@Autowired
	private DiretorioRepository diretorioRepository;
	
	@Override
	public Diretorio save(Diretorio entity) {
		return diretorioRepository.save(entity);
	}
	
	@Override
	public Optional<Diretorio> findById(Long id) {
		return diretorioRepository.findById(id);
	}

	@Override
	public List<Diretorio> findAll() {
		return diretorioRepository.findAll();
	}
	
    public void deleteById(Long id) {
        Diretorio diretorio = diretorioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diretório com ID " + id + " não encontrado"));

        diretorioRepository.delete(diretorio);
        System.out.println("Diretório com ID " + id + " deletado com sucesso");
    }

    
	public Optional<Diretorio> findByNome(String nome) {
		return diretorioRepository.findByNome(nome); 
	}
}