package org.idomine.domain.crud.reporitory;

import org.idomine.domain.crud.model.Menu;
import org.idomine.domain.crud.model.Projeto;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Long>
{
    Projeto findByNome(String nome);
    
    Projeto findByNomeIgnoreCase(String nome);
    
    Projeto findByNomeOrId(String nome, Long id);
    
    Projeto findByNomeIgnoreCaseOrId(String nome, Long id); 
}
