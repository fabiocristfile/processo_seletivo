package br.com.processo_seletivo.processo_seletivo.servicos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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

    public void deleteById(Long id) {
        if (arquivoRepository.existsById(id)) {
            arquivoRepository.deleteById(id);
            System.out.println("Arquivo com ID " + id + " deletado com sucesso");
        } else {
            throw new EntityNotFoundException("Arquivo com ID " + id + " não encontrado");
        }
    }

	public List<Arquivo> findByDiretorioId(Long diretorioId) {
		return arquivoRepository.findByDiretorioId(diretorioId);
	}
}