SET SQL_SAFE_UPDATES = 0;
create database if not exists  sa_DB;
use sa_DB;

INSERT INTO `sa_db`.`permissao` (`nome`) VALUES ('aluno');
INSERT INTO `sa_db`.`permissao` (`nome`) VALUES ('professor');
INSERT INTO `sa_db`.`permissao` (`nome`) VALUES ('diretor');

delimiter $$

create trigger tr_insert_usuario  after insert on usuario for each row
begin
declare vExiste int;
declare vTipo char;
select tipo into vTipo from usuario where id = new.id;
select id into vExiste from usuario where id = new.id;


if  vTipo = 'A' then 
insert into usuario_permissao(usuario_id,permissao_id) values (new.id,1);
end if;

if vTipo = 'D' then
insert into usuario_permissao(usuario_id,permissao_id) values (new.id,3);
end if;


if vTipo = 'P'then
insert into usuario_permissao(usuario_id,permissao_id) values (new.id,2);
end if;



end $$

create  trigger tr_delete_usuario before delete on usuario for each row
begin


delete from usuario_permissao where usuario_id = old.id;



end $$

delimiter ;
