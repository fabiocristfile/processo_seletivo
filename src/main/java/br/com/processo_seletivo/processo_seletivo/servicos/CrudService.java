package br.com.processo_seletivo.processo_seletivo.servicos;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);  
    List<T> findAll();
    void deleteById(ID id);
}