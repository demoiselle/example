DROP TABLE usuario;
DROP TABLE livraria;
DROP TABLE sgdb;

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
  id serial not null,
  comando varchar(8192),
  CONSTRAINT sgdb_pkey PRIMARY KEY (id)
);

INSERT INTO sgdb(id, comando) VALUES (nextval('sgdb_id_seq'), ' CREATE SCHEMA DEMO AUTHORIZATION postgres; ');
INSERT INTO sgdb(id, comando) VALUES (nextval('sgdb_id_seq'), ' GRANT ALL ON SCHEMA DEMO TO postgres; ');
INSERT INTO sgdb(id, comando) VALUES (nextval('sgdb_id_seq'), ' SET search_path to DEMO ; ');
INSERT INTO sgdb(id, comando) VALUES (nextval('sgdb_id_seq'), ' CREATE TABLE DEMO.livro ( id uuid NOT NULL, descricao character varying(255) NOT NULL, CONSTRAINT livro_pkey PRIMARY KEY (id) );  ');


