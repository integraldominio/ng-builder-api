CREATE TABLE `Configuracao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome_empresa` varchar(255) DEFAULT NULL,
  `site_empresa` varchar(255) DEFAULT NULL,
  `email_empresa` varchar(255) DEFAULT NULL,
  `output_directory` varchar(255) DEFAULT NULL,
  `app_properties` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8MB4;
