package org.idomine.domain.crud.repository;

import org.idomine.domain.crud.model.${artefato.className};
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface ${artefato.className}Repository extends  CrudRepository<${artefato.className}, Long>
{

    ${artefato.className} findByNome(String nome);
    
    ${artefato.className} findByNomeIgnoreCase(String nome);
    
    ${artefato.className} findByNomeOrId(String nome, Long id);
    
    ${artefato.className} findByNomeIgnoreCaseOrId(String nome, Long id);
 
}

