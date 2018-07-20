package org.idomine.domain.crud.reporitory;

import org.idomine.domain.crud.model.Elemento;
import org.idomine.domain.crud.model.Projeto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface ElementoRepository extends CrudRepository<Elemento, Long>
{
    Projeto findByNome(String nome);
    
    Projeto findByNomeIgnoreCase(String nome);
    
    Projeto findByNomeOrId(String nome, Long id);
    
    Projeto findByNomeIgnoreCaseOrId(String nome, Long id);
}
 