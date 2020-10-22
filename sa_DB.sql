create database if not exists  sa_DB;
use sa_DB;
delimiter $$
create  trigger  tr_insert_usuario after insert on usuario for each row
begin


insert into usuario_permissao(usuario_id,permissao_id) values (new.id,1);



end $$


delimiter ;


select * from usuario;
select * from usuario_permissao;
select * from permissao;