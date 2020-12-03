SET SQL_SAFE_UPDATES = 0;
create database if not exists  sa_DB;
use sa_DB;
delimiter $$

create  trigger tr_insert_usuario after insert on usuario for each row
begin
declare vExiste int;
select id into vExiste from usuario where id = new.id;

if vExiste != 0 then
insert into usuario_permissao(usuario_id,permissao_id) values (new.id,1);
end if;



end $$

create  trigger tr_delete_usuario before delete on usuario for each row
begin


delete from usuario_permissao where usuario_id = old.id;



end $$

delimiter ;
select * from usuario;
select * from usuario_permissao;
select * from permissao;

show tables ;