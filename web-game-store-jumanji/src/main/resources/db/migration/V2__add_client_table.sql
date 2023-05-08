CREATE TABLE client (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   dtype VARCHAR(31),
   name VARCHAR(255),
   email VARCHAR(255),
   password VARCHAR(255),
   address_cep VARCHAR(255),
   CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE product (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   dtype VARCHAR(31),
   name VARCHAR(255),
   price DECIMAL,
   arrival TIMESTAMP WITHOUT TIME ZONE,
   sold TIMESTAMP WITHOUT TIME ZONE,
   amount DECIMAL,
   product_type INTEGER,
   CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE shopping (
  client_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL
);


ALTER TABLE client ADD CONSTRAINT uc_client_email UNIQUE (email);

ALTER TABLE client ADD CONSTRAINT FK_CLIENT_ON_ADDRESS_CEP FOREIGN KEY (address_cep) REFERENCES address (cep);

ALTER TABLE shopping ADD CONSTRAINT fk_shopping_on_client FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE shopping ADD CONSTRAINT fk_shopping_on_product FOREIGN KEY (product_id) REFERENCES product (id);