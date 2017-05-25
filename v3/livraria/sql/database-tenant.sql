
CREATE SCHEMA demoiselle AUTHORIZATION postgres;
GRANT ALL ON SCHEMA demoiselle TO postgres;


CREATE TABLE demoiselle.livro
(
  id bigint NOT NULL,
  descricao character varying(255) NOT NULL,
  CONSTRAINT livro_pkey PRIMARY KEY (id)
)