CREATE TABLE `artefato` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_folder` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `pagina_home` bit(1) NOT NULL,
  `resource_name` varchar(255) DEFAULT NULL,
  `template_after_delete` varchar(255) DEFAULT NULL,
  `template_after_insert` varchar(255) DEFAULT NULL,
  `template_after_update` varchar(255) DEFAULT NULL,
  `template_before_delete` varchar(255) DEFAULT NULL,
  `template_before_insert` varchar(255) DEFAULT NULL,
  `template_before_update` varchar(255) DEFAULT NULL,
  `template_css` varchar(255) DEFAULT NULL,
  `template_html` varchar(255) DEFAULT NULL,
  `template_ts` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `projeto_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;