CREATE TABLE `Portal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `template_ts` varchar(255) DEFAULT NULL,
  `template_css` varchar(255) DEFAULT NULL,
  `template_html` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8MB4;
