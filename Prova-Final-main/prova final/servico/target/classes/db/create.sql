CREATE TABLE tech4lavagem.servico(
    id text UNIQUE PRIMARY KEY NOT NULL,
    tipo text NOT NULL,
    data_servico DATE NOT NULL,
    endereco text NOT NULL,
    valor real NOT NULL,
    sofa_id text NOT NULL,

    CONSTRAINT fk_servico_sofa FOREIGN KEY(sofa_id) REFERENCES tech4lavagem.sofa(id)
);