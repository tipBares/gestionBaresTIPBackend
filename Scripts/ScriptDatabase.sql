-- CREATE DATABASE tipbares;
USE tipbares;

insert into mesa(id, abierta, nro_mesa) values (1, true, 1);
insert into mesa(id, abierta, nro_mesa) values (2, false, 2);
commit;

SELECT * FROM tipbares.mesa;