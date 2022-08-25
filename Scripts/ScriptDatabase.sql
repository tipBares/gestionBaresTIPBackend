-- CREATE DATABASE tipbares;
USE tipbares;

insert into mesa(id, abierta, ticket_id, nro_mesa) values (1, true, null, 1);
insert into mesa(id, abierta, ticket_id, nro_mesa) values (2, false, null, 2);
commit;

SELECT * FROM tipbares.mesa;