CREATE OR REPLACE VIEW VW_LIST_CONTA AS
  SELECT
    ID,
    NOME,
    SOBRENOME,
    TOTAL
  FROM
    CONTA;
