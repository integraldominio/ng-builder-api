package org.idomine.domain.crud.reporitory;

import org.idomine.domain.crud.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

 
public interface ProjetoRepository extends  JpaRepository<Projeto, Long>
{

}
