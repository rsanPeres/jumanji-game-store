CREATE TABLE address (
  cep VARCHAR(255) NOT NULL,
   street VARCHAR(255),
   complement VARCHAR(255),
   neighborhood VARCHAR(255),
   locality VARCHAR(255),
   uf VARCHAR(255),
   ibge VARCHAR(255),
   gia VARCHAR(255),
   ddd VARCHAR(255),
   siafi VARCHAR(255),
   CONSTRAINT pk_address PRIMARY KEY (cep)
);