package org.idomine.domain.crud.repository;

import org.idomine.domain.crud.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface ProdutoRepository extends  CrudRepository<Produto, Long>
{

    Produto findByNome(String nome);
    
    Produto findByNomeIgnoreCase(String nome);
    
    Produto findByNomeOrId(String nome, Long id);
    
    Produto findByNomeIgnoreCaseOrId(String nome, Long id);
 
}

