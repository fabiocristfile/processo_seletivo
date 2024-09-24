
package br.com.processo_seletivo.processo_seletivo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.processo_seletivo.processo_seletivo.entidades.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
 	
    List<Arquivo> findByDiretorioId(Long diretorioId);
}