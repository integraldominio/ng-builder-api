CREATE TABLE `portal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL ,
  `descricao` varchar(255) NOT NULL ,
  `template_ts` varchar(255) NOT NULL ,
  `template_css` varchar(255) NOT NULL ,
  `template_html` varchar(255) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8MB4;
