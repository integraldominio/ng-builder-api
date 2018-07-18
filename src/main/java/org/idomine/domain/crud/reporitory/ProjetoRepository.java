package org.idomine.domain.crud.reporitory;

import org.idomine.domain.crud.model.Projeto;
import org.springframework.data.repository.CrudRepository;


public interface ProjetoRepository extends  CrudRepository<Projeto, Long>
{
    Projeto findByNome(String nome);
    
    Projeto findByNomeIgnoreCase(String nome);
    
    Projeto findByNomeOrId(String nome, Long id);
    
    Projeto findByNomeIgnoreCaseOrId(String nome, Long id);
}

