CREATE TABLE `projeto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `portal_id` bigint(20) ,
  `nome` varchar(255) NOT NULL ,
  `descricao` varchar(255) NOT NULL ,
  `nome_backend_app` varchar(255) NOT NULL ,
  `nome_front_end_app` varchar(255) NOT NULL ,
  `icone_app` varchar(255) NOT NULL ,
  `image_app` varchar(255) NOT NULL ,
  `server_lang` varchar(255) NOT NULL ,
  `database_flavor` varchar(255) NOT NULL ,
  `server_host` varchar(255) NOT NULL ,
  `server_port` bigint(20) NOT NULL ,
  `front_host` varchar(255) NOT NULL ,
  `front_port` bigint(20) NOT NULL ,
  `use_login`  bit(1) NOT NULL ,
  `use_roles`  bit(1) NOT NULL ,
  `output_directory` varchar(255) NOT NULL ,
  PRIMARY KEY (`id`)
  , FOREIGN KEY (`portal_id`) REFERENCES portal(`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8MB4;
