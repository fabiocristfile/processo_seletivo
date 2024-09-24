package br.com.processo_seletivo.processo_seletivo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.processo_seletivo.processo_seletivo.entidades.Diretorio;


public interface DiretorioRepository extends JpaRepository<Diretorio, Long> {
	
}