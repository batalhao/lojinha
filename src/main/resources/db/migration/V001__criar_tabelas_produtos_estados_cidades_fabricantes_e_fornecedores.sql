CREATE TABLE fornecedores (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,  
  nome VARCHAR(150) NULL,
  documento VARCHAR(50) NULL,
  telefone VARCHAR(50) NULL,
  email VARCHAR(50) NULL,
  logradouro VARCHAR(50) NULL,
  numero VARCHAR(50) NULL,
  complemento VARCHAR(50) NULL,
  cep VARCHAR(50) NULL,
  tipo_entidade VARCHAR(50) NULL,
  ativo BIT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE fabricantes (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,  
  nome VARCHAR(150) NULL,
  documento VARCHAR(50) NULL,
  telefone VARCHAR(50) NULL,
  email VARCHAR(50) NULL,
  logradouro VARCHAR(50) NULL,
  numero VARCHAR(50) NULL,
  complemento VARCHAR(50) NULL,
  cep VARCHAR(50) NULL,
  tipo_entidade VARCHAR(50) NULL,
  ativo BIT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE estados (
  id INT PRIMARY KEY AUTO_INCREMENT,  
  uf CHAR(2) NULL,
  nome VARCHAR(50) NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE cidades (
  id INT PRIMARY KEY AUTO_INCREMENT,  
  nome VARCHAR(50) NULL,
  estado_id INT NULL,
  FOREIGN KEY (estado_id) REFERENCES estados(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE produtos (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  sku VARCHAR(50) NULL,
  gtin VARCHAR(14) NULL,
  nome VARCHAR(150) NULL,
  descricao VARCHAR(255) NULL,
  custo_unitario DECIMAL(19,4) NULL,
  preco_venda DECIMAL(19,4) NULL,
  comissao DECIMAL(19,4) NULL,
  quantidade_estoque INT NULL,
  unidade VARCHAR(50) NULL,
  origem VARCHAR(50) NULL,
  estado_id INT NULL,
  cidade_id INT NULL,
  fornecedor_id BIGINT NULL,
  fabricante_id BIGINT NULL,
  FOREIGN KEY (estado_id) REFERENCES estados(id),
  FOREIGN KEY (cidade_id) REFERENCES cidades(id),
  FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id),
  FOREIGN KEY (fabricante_id) REFERENCES fabricantes(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
