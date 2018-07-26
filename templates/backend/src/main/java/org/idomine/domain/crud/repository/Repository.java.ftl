/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
 
package org.idomine.domain.crud.repository;

<#if artefato.hasDateType() >
import java.util.Date;
</#if>

<#list artefato.elementos as field >
<#if field.persistence >
<#if field.selectDB() >
import org.idomine.domain.crud.model.${field.nome};
</#if>
</#if>
</#list>	

import org.idomine.domain.crud.model.${artefato.className};
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface ${artefato.className}Repository extends  CrudRepository<${artefato.className}, Long>
{
  <#list artefato.elementos as field >
	<#if field.persistence >
	<#if field.selectDB() >
    // ${artefato.className} findBy${field.nome?cap_first}(${field.nome} ${field.nome});
    <#else>
    ${artefato.className} findBy${field.nome?cap_first}(${field.tipoField} ${field.nome});
    </#if>
    </#if>
  </#list>	
 
}

