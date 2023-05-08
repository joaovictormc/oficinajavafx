CREATE TABLE usuarios (
    id serial PRIMARY KEY;
    usuario varchar(10),
    senha varchar(10)
);

insert into usuarios(usuario, senha) values ('admin', 'admin');


CREATE TABLE clientes (
    id_cli SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    email VARCHAR(50)
);

CREATE TABLE mecanico (
    id_mec SERIAL PRIMARY KEY,
    nome_mec VARCHAR(100) NOT NULL,
    telefone_mec VARCHAR(20) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    email VARCHAR(50),
    salario FLOAT,
	disponibilidade boolean,
	status varchar(20)
);

insert into mecanico(nome_mec, telefone_mec, especialidade, email, salario, disponibilidade, status)
values ('Tião Lanterneiro', '(28) 99958-4856', 'Lanternagem e Funilaria', 'tiao@gmail.com', '5312.84', true, 'em serviço');

CREATE TABLE servicos (
    id_servicos SERIAL PRIMARY KEY,
    tipo_servico VARCHAR(100) NOT NULL,
    tempo_estimado VARCHAR(20),
    valor FLOAT,
    id_mecanico INTEGER REFERENCES mecanico(id_mec),
    complexidade VARCHAR(100)
);



CREATE TABLE orcamentos (
    id_os SERIAL PRIMARY KEY,
    tipo_veiculo VARCHAR(50) NOT NULL,
    modelo_veiculo VARCHAR(50)NOT NULL,
    ano_veiculo VARCHAR(5) NOT NULL,
    marca_veiculo VARCHAR(50) NOT NULL,
    avarias TEXT,
    data_entrada DATE NOT NULL,
    data_saida DATE,
    defeito_relatado TEXT,
    defeito_constatado TEXT,
    descontos INTEGER,
    valor_final FLOAT,
	situacao boolean,
    id_cliente INTEGER REFERENCES clientes(id_cli),
    id_servicos INTEGER REFERENCES servicos(id_servicos)
);

CREATE TABLE agendamento (
    id_agenda SERIAL PRIMARY KEY,
    data_inicio_servico DATE NOT NULL,
    data_final_servico DATE,
	pagamento boolean,
    observacoes VARCHAR(255),
    id_mecanico INTEGER REFERENCES mecanico(id_mec),
    id_cliente INTEGER REFERENCES clientes(id_cli),
    id_orcamentos INTEGER REFERENCES orcamentos(id_os)
);

CREATE TABLE avaliacao (
    id_avaliacao SERIAL PRIMARY KEY,
    nota INTEGER NOT NULL,
    comentario TEXT,
    id_agendamento INTEGER REFERENCES agendamento(id_agenda),
	id_mecanico INTEGER REFERENCES mecanico(id_mec)
);
