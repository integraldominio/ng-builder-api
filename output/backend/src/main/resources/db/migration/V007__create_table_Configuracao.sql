CREATE TABLE `configuracao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome_empresa` varchar(255) NOT NULL ,
  `site_empresa` varchar(255) ,
  `email_empresa` varchar(255) ,
  `output_directory` varchar(255) ,
  `app_properties` varchar(255) ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8MB4;
