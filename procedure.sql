delimiter //
use cajero //
create procedure sp_nuevoMaeCliente(
	in prtmnombre varchar(50),
	in prtmnumeroTarjeta int,
	in prtmclave varchar(4),
	in prtmsaldo int,
	in prtmultMovMonto int,
	in prtmfechaUlt date,
	in prtmhoraUlt time)
begin
insert into mae_cli(
	nombre,
	numeroTarjeta,
	clave,
	saldo,
	ult_mov_monto,
	fecha_ult,
	hora_ult
	)values(
	prtmnombre,
	prtmnumeroTarjeta,
	prtmclave,
	prtmsaldo,
	prtmultMovMonto,
	prtmfechaUlt,
	prtmhoraUlt
	);
end //
delimiter ;

delimiter //
use cajero//
create procedure sp_VerificarAccesoCliente(in prtmnumeroTarjeta int,in prtmClave varchar(4))
begin
select 
	id,
	nombre,
	numeroTarjeta,
	clave,
	saldo,
	ult_mov_monto,
	fecha_ult,
	hora_ult
from mae_cli where numeroTarjeta=prtmnumeroTarjeta and clave=prtmClave;
end //
delimiter ;	


delimiter //
use cajero//
create procedure sp_DevolverMaeClienteId(in prtmIdCliente int)
begin
select 
	id,
	nombre,
	numeroTarjeta,
	clave,
	saldo,
	ult_mov_monto,
	fecha_ult,
	hora_ult
from mae_cli where id = prtmIdCliente;
end //
delimiter ;	


delimiter //
use cajero//
create procedure sp_EditarMaeCliente(
	in prtmidClientes int,
	in prtmsaldo int,
	in prtmultMovMonto int)
begin
update mae_cli set 
	saldo=prtmsaldo,
	ult_mov_monto=prtmultMovMonto
	where id=prtmidClientes;

end //
delimiter ;
--------------------------------------------Editar 20-06-2017 7:02:21

delimiter //
use cajero//
create procedure sp_EditarMaeCliente(
	in prtmidClientes int,
	in prtmsaldo int,
	in prtmultMovMonto int,
	in prtmfechaUlt varchar(15),
	in prtmhoraUlt varchar(15))
begin
update mae_cli set 
	saldo=prtmsaldo,
	ult_mov_monto=prtmultMovMonto,
	fecha_ult=prtmfechaUlt,
	hora_ult=prtmhoraUlt where id=prtmidClientes;

end //
delimiter ;
-----------------------------------------------------

delimiter //
use cajero//
create procedure sp_EditarMaeCliente(
	in prtmidClientes int,
	in prtmnombre varchar(50),
	in prtmnumeroTarjeta int,
	in prtmclave varchar(4),
	in prtmsaldo int,
	in prtmultMovMonto int,
	in prtmfechaUlt date,
	in prtmhoraUlt time)
begin
update mae_cli set 
	nombre=prtmnombre,
	numeroTarjeta=prtmnumeroTarjeta,
	clave=prtmclave,
	saldo=prtmsaldo,
	ult_mov_monto=prtmultMovMonto,
	fecha_ult=prtmfechaUlt,
	hora_ult=prtmhoraUlt where id=prtmidClientes;

end //
delimiter ;