CREATE OR REPLACE VIEW VW_LIST_TRANSACAO AS
  SELECT
    ID,
    TIPO,
    VALOR,
    DESCRICAO
  FROM
    TRANSACAO;
