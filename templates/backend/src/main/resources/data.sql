insert into projeto (
	id, nome,descricao, output_directory, use_login, use_roles
) values (
	1,'Ngx-Builder','Angular and Springboot generation ', 'output' , 1, 1
);

insert into artefato ( id,projeto_id, tipo, nome) values ( 1,1,'Crud','Projetos');
insert into artefato ( id,projeto_id, tipo, nome) values ( 2,1,'Crud','Artefatos');
insert into artefato ( id,projeto_id, tipo, nome) values ( 3,1,'Crud','Elementos');

insert into elemento
	(id, artefato_id, nome, rotulo, tipo_elemento, tipo_field, persistence, requerido, showcolumn ) 
values 
	(1,1,'nome','Nome','Input', 'String',1,1,1);

insert into elemento
	(id, artefato_id, nome, rotulo, tipo_elemento, tipo_field, persistence, requerido, showcolumn ) 
values 
	(2,2,'nome','Nome','Input', 'String',1,1,1);

insert into elemento
	(id, artefato_id, nome, rotulo, tipo_elemento, tipo_field, persistence, requerido, showcolumn ) 
values 
	(3,3,'nome','Nome','Input', 'String',1,1,1);
	
insert into Configuracao ( id, nome_empresa ) values (1, 'Integra ldominio')