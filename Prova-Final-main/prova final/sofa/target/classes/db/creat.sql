
CREATE SCHEMA tech4lavagem;

CREATE TABLE tech4lavagem.sofa(
    id text UNIQUE PRIMARY KEY NOT NULL,
    tamanho text NOT NULL,
    tecido text NOT NULL,
    cor text NOT NULL

);