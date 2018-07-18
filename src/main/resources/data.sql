insert into projeto ( id, nome, use_login, use_roles ) values ( 1, 'projeto 01',0,0 );
insert into projeto ( id, nome, use_login, use_roles ) values ( 2, 'projeto 02',0,0 );
insert into projeto ( id, nome, use_login, use_roles ) values ( 3, 'projeto 03',0,0 );
insert into projeto ( id, nome, use_login, use_roles ) values ( 4, 'projeto 04',0,0 );
insert into projeto ( id, nome, use_login, use_roles ) values ( 5, 'projeto 05',0,0 );
insert into projeto ( id, nome, use_login, use_roles ) values ( 6, 'projeto 06',0,0 );
insert into projeto ( id, nome, use_login, use_roles ) values ( 7, 'projeto 07',0,0 );
insert into projeto ( id, nome, use_login, use_roles ) values ( 8, 'projeto 08',0,0 );
insert into projeto ( id, nome, use_login, use_roles ) values ( 9, 'projeto 09',0,0 );
insert into projeto ( id, nome, use_login, use_roles ) values ( 10, 'projeto 10',0,0 );

insert into artefato( id, projeto_id, nome, resource_name, class_name, tipo ) values ( 1, 1, 'Cadastro de Clientes' , 'clientes', 'Cliente',  'Crud' );

insert into elemento( id, artefato_id, nome, rotulo, tipo_elemento, tipo_field, requerido ) values ( 1, 1, 'id','id', 'Field', 'Integer', 0 );
insert into elemento( id, artefato_id, nome, rotulo, tipo_elemento, tipo_field, requerido ) values ( 2, 1, 'nome','Nome', 'Field', 'String' , 1);