--DROP TABLE usuario;
--DROP TABLE livraria;
--DROP TABLE sgdb;
--DROP TABLE mensagem;

CREATE TABLE mensagem
(
  id serial NOT NULL,
  dia date DEFAULT now(),
  comando character varying(64),
  tipo character varying(8),
  ativo boolean DEFAULT true,
  CONSTRAINT mensagem_pkey PRIMARY KEY (id)
);

CREATE TABLE usuario
(
  id uuid not null,
  livraria_id uuid not null,
  firstname varchar(128),
  email varchar(128),
  pass varchar(128),
  perfil int,
  CONSTRAINT usuario_pkey PRIMARY KEY (id),
  CONSTRAINT uk_email UNIQUE (email)
);

CREATE TABLE livraria
(
  id uuid not null,
  description varchar(128),
  CONSTRAINT livraria_pkey PRIMARY KEY (id),
  CONSTRAINT uk_livraria UNIQUE (description)
);

CREATE TABLE sgdb
(
  id serial NOT NULL,
  dia date NOT NULL DEFAULT now(),
  comando character varying(10240),
  CONSTRAINT sgdb_pkey PRIMARY KEY (id)
);

INSERT INTO sgdb(comando) VALUES ('CREATE TABLE livro (id uuid NOT NULL, descricao character varying(255) NOT NULL, CONSTRAINT livro_pkey PRIMARY KEY (id),CONSTRAINT uk_livro UNIQUE(descricao));');

