create table usuarios(
  id SERIAL PRIMARY KEY,
  nome_completo VARCHAR(150) NOT NULL,
  cpf_cnpj VARCHAR(255) NOT NULL,
  email VARCHAR(180) NOT NULL,
  senha VARCHAR(160) NOT NULL,
  tipo_usuario VARCHAR(100) NOT NULL
);