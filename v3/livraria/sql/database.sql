
CREATE SCHEMA demoiselle AUTHORIZATION postgres;
GRANT ALL ON SCHEMA demoiselle TO postgres;


DROP TABLE demoiselle.livro;
CREATE TABLE demoiselle.livro
(
  id uuid NOT NULL,
  descricao character varying(255) NOT NULL,
  CONSTRAINT livro_pkey PRIMARY KEY (id)
);

DROP TABLE public.livro;
CREATE TABLE public.livro
(
  id uuid NOT NULL,
  descricao character varying(255) NOT NULL,
  CONSTRAINT livro_pkey PRIMARY KEY (id)
);

INSERT INTO public.livro(id, descricao) VALUES ('18c76a0b-6524-4efa-ae60-5f6ecb96387a', 'public');
INSERT INTO demoiselle.livro(id, descricao) VALUES ('18c76a0b-6524-4efa-ae60-5f6ecb96387a', 'demoiselle');