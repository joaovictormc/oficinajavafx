CREATE TABLE usuarios (
    id serial PRIMARY KEY;
    usuario varchar(10),
    senha varchar(10)
);

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
	status varchar(20),
    id_servico INTEGER REFERENCES servicos(id_servicos)
);

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
    valor_final FLOAT,
    descontos INTEGER,
	situacao boolean,
    id_cliente INT not NULL,
    id_servico INT NOT NULL,
    CONSTRAINT fk_os_cliente FOREIGN KEY (id_cliente) REFERENCES clientes (id_cli),
    CONSTRAINT fk_os_servico FOREIGN KEY (id_servico) REFERENCES servicos (id_servicos)
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



insert into usuarios(usuario, senha) values ('admin', 'admin');

insert into mecanico(nome_mec, telefone_mec, especialidade, email, salario, disponibilidade, status)
values ('Tião Lanterneiro', '(28) 99958-4856', 'Lanternagem e Funilaria', 'tiao@gmail.com', '5312.84', true, 'em serviço');

insert into servicos(tipo_servico, tempo_estimado, valor, id_mecanico, complexidade)
values ('Lanternagem', '2 dias', '800', 1, 'moderado');

insert into orcamentos(tipo_veiculo, modelo_veiculo, ano_veiculo, marca_veiculo, avarias, data_entrada, data_saida, defeito_relatado, defeito_constatado, valor_final, descontos, situacao, id_cliente, id_servico)
values ('Carro Passeio', 'Corsa', '1999', 'Chevrolet', '-', '01/05/2023', '08/05/2023', 'Veículo batido', 'Veículo com amassados na região das portas e no porta-malas', '1200', 15, true, '4', '1');

insert into orcamentos(tipo_veiculo, modelo_veiculo, ano_veiculo, marca_veiculo, avarias, data_entrada, data_saida, defeito_relatado, defeito_constatado, valor_final, descontos, situacao, id_cliente, id_servico)
values ('Carro Passeio', 'Corsa', '1999', 'Chevrolet', '-', '01/05/2023', '15/05/2023', 'Veículo batido', 'Veículo com amassados na região das portas e no porta-malas', '1200', 15, false, '2', '1');