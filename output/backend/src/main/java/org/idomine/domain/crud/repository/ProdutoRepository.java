package org.idomine.domain.crud.reporitory;

import org.idomine.domain.crud.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends  CrudRepository<Produto, Long>
{
 
}

