CREATE TABLE usuarios (
    id serial PRIMARY KEY,
    usuario varchar(10),
    senha varchar(10)
);

INSERT INTO usuarios(usuario, senha) VALUES ('admin', 'admin');


CREATE TABLE clientes (
    id_cli SERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    email VARCHAR(50),
    orcamentos_aprovados INTEGER,
    CONSTRAINT pk_clientes PRIMARY KEY(id_cli)
);

CREATE TABLE mecanico (
    id_mec SERIAL NOT NULL,
    nome_mec VARCHAR(100) NOT NULL,
    telefone_mec VARCHAR(20) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    email VARCHAR(50),
    salario FLOAT,
	disponibilidade BOOLEAN,
	servicosSendoFeitos INTEGER,
    CONSTRAINT pk_mecanico PRIMARY KEY(id_mec)
);

CREATE TABLE servicos (
    id_servicos SERIAL NOT NULL,
    tipo_servico VARCHAR(100) NOT NULL,
    tempo_estimado VARCHAR(20),
    valor FLOAT,
    mecanico VARCHAR(100),
    complexidade VARCHAR(100),
    CONSTRAINT pk_servicos PRIMARY KEY(id_servicos)
);

CREATE TABLE orcamentos (
    id_os SERIAL NOT NULL,
    nome_cliente VARCHAR(50) NOT NULL,
    cpf_cliente VARCHAR(50) NOT NULL,
    modelo_veiculo VARCHAR(50) NOT NULL,
    ano_veiculo VARCHAR(5) NOT NULL,
    marca_veiculo VARCHAR(50) NOT NULL,
    data_entrada DATE NOT NULL,
    defeito_relatado TEXT,
    descontos INTEGER,
    valor_final FLOAT,
    or_finalizado BOOLEAN,
    servico VARCHAR(100),
    CONSTRAINT pk_orcamentos PRIMARY KEY (id_os)
);

CREATE TABLE agendamento (
    id_agenda SERIAL NOT NULL,
    nome_cliente VARCHAR(50) NOT NULL,
    cpf_cliente VARCHAR(50) NOT NULL,
    data_inicio_servico DATE NOT NULL,
    data_final_servico DATE,
    tipo_pagamento VARCHAR(50),
    valor_pago FLOAT,
	pagamento BOOLEAN,
    CONSTRAINT pk_agendamentos PRIMARY KEY (id_agenda)
);

CREATE TABLE avaliacao (
    id_avaliacao SERIAL NOT NULL,
    nota INTEGER NOT NULL,
    comentario TEXT,
    CONSTRAINT pk_avaliacao PRIMARY KEY (id_avaliacao)
);
