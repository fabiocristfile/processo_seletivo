package br.com.processo_seletivo.processo_seletivo.servicos;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID>  {
	
	public T save(T entity);

	public Optional<T> findById(ID id);

	public List<T> findAll();

	public void deleteById(ID id);
}