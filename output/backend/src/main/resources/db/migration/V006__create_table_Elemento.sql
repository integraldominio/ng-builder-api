CREATE TABLE `elemento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `artefato_id` bigint(20) ,
  `tipo_elemento` varchar(255) NOT NULL ,
  `tipo_field` varchar(255) NOT NULL ,
  `nome` varchar(255) NOT NULL ,
  `rotulo` varchar(255) NOT NULL ,
  `inicial` varchar(255) ,
  `mascara` varchar(255) ,
  `pipe` varchar(255) ,
  `dica` varchar(255) ,
  `tamanho` bigint(20) NOT NULL ,
  `min` bigint(20) ,
  `max` bigint(20) ,
  `linhas` bigint(20) ,
  `ordenation` bigint(20) ,
  `persistence`  bit(1) NOT NULL ,
  `requerido`  bit(1) NOT NULL ,
  `showcolumn`  bit(1) ,
  `unique_key`  bit(1) ,
  `value_prop` varchar(255) ,
  `label_prop` varchar(255) ,
  `options` varchar(255) ,
  `field_group` bigint(20) ,
  `field_size` bigint(20) ,
  PRIMARY KEY (`id`)
  , FOREIGN KEY (`artefato_id`) REFERENCES artefato(`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8MB4;