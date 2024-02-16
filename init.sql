CREATE TABLE clientes
(
    id     SERIAL NOT NULL,
    limite BIGINT NOT NULL,
    saldo  BIGINT,
    CONSTRAINT pk_clientes PRIMARY KEY (id)
);

CREATE TABLE transacoes
(
    id         BIGINT                      NOT NULL,
    valor      BIGINT                      NOT NULL,
    tipo       CHAR                        NOT NULL,
    descricao  VARCHAR(10)                 NOT NULL,
    realizacao TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    cliente_id BIGINT                      NOT NULL,
    CONSTRAINT pk_transacoes PRIMARY KEY (id)
);

ALTER TABLE transacoes
    ADD CONSTRAINT FK_TRANSACOES_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES clientes (id);


DO
$$
    BEGIN
        INSERT INTO clientes (limite, saldo)
        VALUES (1000 * 100, 0),
               (800 * 100, 0),
               (10000 * 100, 0),
               (100000 * 100, 0),
               (5000 * 100, 0);
    END;
$$;