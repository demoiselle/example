DROP TABLE usuario;
DROP TABLE livraria;

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



