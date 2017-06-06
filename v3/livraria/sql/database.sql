CREATE TABLE livro (id uuid NOT NULL, descricao character varying(255) NOT NULL, CONSTRAINT livro_pkey PRIMARY KEY (id),CONSTRAINT uk_livro UNIQUE(descricao));


