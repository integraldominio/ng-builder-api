CREATE TABLE `${artefato.className}` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
<#list artefato.elementos as e >
<#if e.persistence >
  ${e.tipoMySQL()} DEFAULT NULL,
</#if>  
</#list>  
  PRIMARY KEY (`id`),
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8MB4;
