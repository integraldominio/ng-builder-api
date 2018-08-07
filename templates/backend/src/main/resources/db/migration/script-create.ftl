CREATE TABLE `${artefato.className?uncap_first}` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  <#list artefato.elementos as e >
  <#if e.persistence >
  ${e.tipoMySQL()} ,
  </#if>  
  </#list>  
  PRIMARY KEY (`id`)
  <#list artefato.elementos as e >
  <#if e.persistence >
  <#if e.selectDB() >
  , FOREIGN KEY (`${e.fkMySQL()}`) REFERENCES ${e.nome?uncap_first}(`id`)
  </#if>  
  </#if>
  </#list>  
  <#list artefato.elementos as e >
  <#if e.persistence >
  <#if e.uniqueKey >
  , CONSTRAINT UK_${e.nome?upper_case}_IN_${artefato.nome?upper_case} UNIQUE (`${e.nameMySQL()}`)
  </#if>  
  </#if>
  </#list>  
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8MB4;
