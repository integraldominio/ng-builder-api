insert into projeto (
	id, nome,descricao, output_directory, use_login, use_roles
) values (
	1,'Controle de Pedidos','Sistema de controle de pedidos', 'output' , 1, 1
);

insert into artefato ( id,projeto_id, tipo, nome) values ( 1,1,'Crud','Pedido');
insert into artefato ( id,projeto_id, tipo, nome) values ( 2,1,'Crud','Cliente');
insert into artefato ( id,projeto_id, tipo, nome) values ( 3,1,'Crud','Catálogo');

insert into elemento
	(id, artefato_id, nome, rotulo, tipo_elemento, tipo_field, persistence, requerido, showcolumn ) 
values 
	(1,2,'nome','Nome','Input', 'String',1,1,1);

insert into elemento
	(id, artefato_id, nome, rotulo, tipo_elemento, tipo_field, persistence, requerido, showcolumn ) 
values 
	(2,2,'endereco','Enredeco','Input', 'String',1,1,1);

insert into elemento
	(id, artefato_id, nome, rotulo, tipo_elemento, tipo_field, persistence, requerido, showcolumn ) 
values 
	(3,2,'email','Email','Input', 'String',1,1,1);
	
insert into Configuracao ( id, nome_empresa ) values (1, 'Integra ldominio')