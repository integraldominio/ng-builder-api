package org.idomine.domain.crud.repository;

import org.idomine.domain.crud.model.${artefato.className};
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface ${artefato.className}Repository extends  CrudRepository<${artefato.className}, Long>
{
  <#list artefato.elementos as field >
	<#if field.tipoElemento == "Field">
    ${artefato.className} findBy${field.nome?cap_first}(${field.tipoField} ${field.nome});
    </#if>
  </#list>	
 
}

