CREATE TABLE [Categoria] (
	Id float NOT NULL,
	Doces_e_Sobremesas string NOT NULL,
	Outros string NOT NULL UNIQUE,
	categoria_Produtos float NOT NULL,
  CONSTRAINT [PK_CATEGORIA] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
GO
CREATE TABLE [Produtos] (
	Id float NOT NULL,
	Nome string NOT NULL UNIQUE,
	Preço float NOT NULL UNIQUE,
	Quantidade float NOT NULL UNIQUE,
	Descricao text NOT NULL UNIQUE,
	Img string(URL) NOT NULL UNIQUE,
	produtos_Carrinho float NOT NULL,
	produtos_Pedido float NOT NULL,
  CONSTRAINT [PK_PRODUTOS] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
GO
CREATE TABLE [Usuarios] (
	Id binary NOT NULL,
	Nome string NOT NULL UNIQUE,
	CPF string(CPF) NOT NULL UNIQUE,
	Celular string NOT NULL UNIQUE,
	Email string(Email/usuario) NOT NULL UNIQUE,
	Senha string(max=6) NOT NULL UNIQUE,
	CEP string(CEP) NOT NULL UNIQUE,
	Numero string(CEP) NOT NULL UNIQUE,
  CONSTRAINT [PK_USUARIOS] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
GO
CREATE TABLE [Carrinho] (
	Id float NOT NULL,
	Carrinho_Usuario float NOT NULL,
  CONSTRAINT [PK_CARRINHO] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
GO
CREATE TABLE [Pedido] (
	Id float NOT NULL,
	Total float NOT NULL UNIQUE,
	Quantidade_de_produtos float NOT NULL UNIQUE,
	Pedido_Usuario float NOT NULL UNIQUE,
  CONSTRAINT [PK_PEDIDO] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
GO
ALTER TABLE [Categoria] WITH CHECK ADD CONSTRAINT [Categoria_fk0] FOREIGN KEY ([categoria_Produtos]) REFERENCES [Produtos]([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [Categoria] CHECK CONSTRAINT [Categoria_fk0]
GO

ALTER TABLE [Produtos] WITH CHECK ADD CONSTRAINT [Produtos_fk0] FOREIGN KEY ([produtos_Carrinho]) REFERENCES [Carrinho]([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [Produtos] CHECK CONSTRAINT [Produtos_fk0]
GO
ALTER TABLE [Produtos] WITH CHECK ADD CONSTRAINT [Produtos_fk1] FOREIGN KEY ([produtos_Pedido]) REFERENCES [Pedido]([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [Produtos] CHECK CONSTRAINT [Produtos_fk1]
GO


ALTER TABLE [Carrinho] WITH CHECK ADD CONSTRAINT [Carrinho_fk0] FOREIGN KEY ([Carrinho_Usuario]) REFERENCES [Usuarios]([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [Carrinho] CHECK CONSTRAINT [Carrinho_fk0]
GO

ALTER TABLE [Pedido] WITH CHECK ADD CONSTRAINT [Pedido_fk0] FOREIGN KEY ([Pedido_Usuario]) REFERENCES [Usuarios]([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [Pedido] CHECK CONSTRAINT [Pedido_fk0]
GO

