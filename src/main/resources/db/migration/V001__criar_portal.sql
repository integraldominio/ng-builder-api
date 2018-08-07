CREATE TABLE `portal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `template_css` varchar(255) DEFAULT NULL,
  `template_html` varchar(255) DEFAULT NULL,
  `template_ts` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT UK_NOME UNIQUE (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
