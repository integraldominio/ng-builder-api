CREATE TABLE `projeto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `front_host` varchar(255) DEFAULT NULL,
  `front_port` varchar(255) DEFAULT NULL,
  `icone_app` varchar(255) DEFAULT NULL,
  `image_app` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `nome_backend_app` varchar(255) DEFAULT NULL,
  `nome_frontend_app` varchar(255) DEFAULT NULL,
  `output_directory` varchar(255) DEFAULT NULL,
  `server_host` varchar(255) DEFAULT NULL,
  `server_port` varchar(255) DEFAULT NULL,
  `use_login` bit(1) NOT NULL,
  `use_roles` bit(1) NOT NULL,
  `portal_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
