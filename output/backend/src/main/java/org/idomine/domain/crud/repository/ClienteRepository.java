package org.idomine.domain.crud.repository;

import org.idomine.domain.crud.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface ClienteRepository extends  CrudRepository<Cliente, Long>
{

    Cliente findByNome(String nome);
    
    Cliente findByNomeIgnoreCase(String nome);
    
    Cliente findByNomeOrId(String nome, Long id);
    
    Cliente findByNomeIgnoreCaseOrId(String nome, Long id);
 
}

