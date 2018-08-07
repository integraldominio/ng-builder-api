CREATE TABLE `artefato` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `projeto_id` bigint(20) ,
  `tipo` varchar(255) NOT NULL ,
  `nome` varchar(255) NOT NULL ,
  `resource_name` varchar(255) NOT NULL ,
  `class_name` varchar(255) NOT NULL ,
  `class_folder` varchar(255) NOT NULL ,
  `crud_estilo` varchar(255) NOT NULL ,
  `pagina_home`  bit(1) ,
  `template_ts` varchar(255) ,
  `template_html` varchar(255) ,
  `template_css` varchar(255) ,
  PRIMARY KEY (`id`)
  , FOREIGN KEY (`projeto_id`) REFERENCES projeto(`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8MB4;
