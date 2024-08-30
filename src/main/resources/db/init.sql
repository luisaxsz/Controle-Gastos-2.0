alter session set "_ORACLE_SCRIPT"=true;
select 'Iniciando script' from dual;

CREATE USER CONTROLEGASTOS IDENTIFIED BY CONTROLEGASTOS;
GRANT CONNECT TO CONTROLEGASTOS;
GRANT RESOURCE TO CONTROLEGASTOS;
GRANT CREATE VIEW TO CONTROLEGASTOS;
GRANT CREATE PROCEDURE TO CONTROLEGASTOS;
GRANT CREATE JOB TO CONTROLEGASTOS;
ALTER USER CONTROLEGASTOS DEFAULT ROLE CONNECT, RESOURCE;
COMMIT;
EXIT;