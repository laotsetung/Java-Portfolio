<h1>Challenge Oracle ONE - Hotel Alura</h1>

<h3>Desafio</h3>
<ul>
<li>Criar uma aplicação Desktop para controlar agendamentos de reservas e hóspedes de um Hotel.</li>
</ul>
<h3>Requisitos</h3>
<ul>
<li>Sistema de autenticação de usuários para que somente usuários pertencentes ao hotel possam acessar o sistema;</li>
<li>Permitir criar, editar e excluir uma reserva para clientes;</li>
<li>Pesquisar na base de dados toda a informação de clientes e reservas;</li>
<li>Registrar, editar e excluir os dados dos hóspedes;</li>
<li>Calcule o valor da reserva com base no número de dias da reserva e uma taxa diária com o valor atribuído por você na moeda Real. Por exemplo, se tivermos uma reserva de 3 dias e o valor de nossa diária for de R$20 devemos multiplicar esses 3 dias pelo valor da diária, totalizando R$60. Tudo isso deve ser feito automaticamente e mostrado ao usuário antes de salvar a reserva;</li>
<li>Banco de dados para armazenar todos os dados solicitados anteriormente.</li>
</ul>
<h3>Tecnologias</h3>
<ul>
<li>JAVA</li>
<li>MySQL</li>
</ul>

<h3>Configuração do Banco de Dados.</h3>
<ul>
<li>Endereço do acesso</li>
localhost:3306
</ul>
<li>Criação do Banco de dados.</li>
<code>CREATE DATABASE hotel;</code>
<li>Criação das Tabelas.</li>

#Tabela de usuários do sistema.<br>
<code>
CREATE TABLE users ( <br>
ID_USER INT auto_increment PRIMARY KEY,<br>
USUARIO VARCHAR(25) NOT NULL,<br>
PASS VARCHAR(25) NOT NULL
);
</code>

#Tabela de reservas. <br>
<code>
CREATE TABLE RESERVAS( <br>
ID_RES INT AUTO_INCREMENT PRIMARY KEY,<br>
DATA_ENTRADA VARCHAR(10) NOT NULL,<br>
DATA_SAIDA VARCHAR(10) NOT NULL,<br>
VALOR DECIMAL(7,2) NOT NULL,<br>
PAGAMENTO VARCHAR(50) NOT NULL
);</code>

#Tabela de Hospedes<br>
<code>
CREATE TABLE HOSPEDES(<br>
ID_HOS INT auto_increment PRIMARY KEY,<br>
NOME VARCHAR(100) NOT NULL,<br>
SOBRENOME VARCHAR(100) NOT NULL,<br>
DATA_NASC VARCHAR(10) NOT NULL,<br>
NACIONALIDADE VARCHAR(100) NOT NULL,<br>
TELEFONE VARCHAR(50),<br>
ID_RESERVA INT
);
</code>

#Adicionando a chave estrangeira da tabela Hospedes.<br>
<code>
ALTER TABLE HOSPEDES ADD FOREIGN KEY (ID_RESERVA) REFERENCES RESERVAS(ID_RES);</code>

#Adicionando o usuario principal de utilização do sistema. <br>
<code>INSERT INTO users (USUARIO, PASS) VALUES('admin','admin');</code>

<video width="320" height="240" controls>
  <source src="https://github.com/laotsetung/Java-Portfolio/blob/main/HotelAlura/extras/resultado.mp4" type="video/mp4">
</video>

https://github.com/laotsetung/Java-Portfolio/blob/main/HotelAlura/extras/resultado.mp4
