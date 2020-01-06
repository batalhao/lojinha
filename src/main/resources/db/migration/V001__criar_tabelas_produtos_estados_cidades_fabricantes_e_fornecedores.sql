CREATE TABLE empresas (
  id INT PRIMARY KEY AUTO_INCREMENT,  
  nome VARCHAR(50) NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE estados (
  id INT PRIMARY KEY AUTO_INCREMENT,  
  uf CHAR(2) NOT NULL,
  nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE cidades (
  id INT PRIMARY KEY AUTO_INCREMENT,  
  nome VARCHAR(50) NOT NULL,
  estado_id INT NOT NULL,
  FOREIGN KEY (estado_id) REFERENCES estados(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE clientes (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,  
  nome VARCHAR(50) NOT NULL,
  documento VARCHAR(18) NOT NULL,
  telefone VARCHAR(15) NULL,
  email VARCHAR(100) NULL,
  logradouro VARCHAR(100) NULL,
  numero VARCHAR(20) NULL,
  complemento VARCHAR(50) NULL,
  cep VARCHAR(10) NULL,
  tipo_pessoa VARCHAR(1) NOT NULL,
  estado_id INT NULL,
  cidade_id INT NULL,
  ativo BOOLEAN NULL DEFAULT true,
  FOREIGN KEY (estado_id) REFERENCES estados(id),
  FOREIGN KEY (cidade_id) REFERENCES cidades(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE fornecedores (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,  
  nome VARCHAR(50) NOT NULL,
  documento VARCHAR(18) NOT NULL,
  telefone VARCHAR(15) NULL,
  email VARCHAR(100) NULL,
  logradouro VARCHAR(100) NULL,
  numero VARCHAR(20) NULL,
  complemento VARCHAR(50) NULL,
  cep VARCHAR(10) NULL,
  tipo_pessoa VARCHAR(1) NOT NULL,
  estado_id INT NULL,
  cidade_id INT NULL,
  ativo BOOLEAN NULL DEFAULT true,
  FOREIGN KEY (estado_id) REFERENCES estados(id),
  FOREIGN KEY (cidade_id) REFERENCES cidades(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE fabricantes (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,  
  nome VARCHAR(50) NOT NULL,
  documento VARCHAR(18) NOT NULL,
  telefone VARCHAR(15) NULL,
  email VARCHAR(100) NULL,
  logradouro VARCHAR(100) NULL,
  numero VARCHAR(20) NULL,
  complemento VARCHAR(50) NULL,
  cep VARCHAR(10) NULL,
  tipo_pessoa VARCHAR(1) NOT NULL,
  estado_id INT NULL,
  cidade_id INT NULL,
  ativo BOOLEAN NULL DEFAULT true,
  FOREIGN KEY (estado_id) REFERENCES estados(id),
  FOREIGN KEY (cidade_id) REFERENCES cidades(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE produtos (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT /* NOT */ NULL, 
  sku VARCHAR(6) NOT NULL,
  gtin VARCHAR(14) NULL,
  nome VARCHAR(50) NOT NULL,
  descricao VARCHAR(150) NOT NULL,
  custo_unitario DECIMAL(19,4) NOT NULL,
  preco_venda DECIMAL(19,4) NOT NULL,
  comissao DECIMAL(19,4) NULL,
  quantidade_estoque INT NULL,
  unidade VARCHAR(50) NOT NULL,
  origem VARCHAR(20) NOT NULL,
  fornecedor_id BIGINT NOT NULL,
  fabricante_id BIGINT NOT NULL,
  FOREIGN KEY (empresa_id) REFERENCES empresas(id),
  FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id),
  FOREIGN KEY (fabricante_id) REFERENCES fabricantes(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
