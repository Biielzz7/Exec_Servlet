CREATE DATABASE Cliente
GO 
USE Cliente
GO
CREATE TABLE cliente (
cpf					char(11)		NOT NULL,
nome				varchar(100)	NOT NULL,
email				varchar(200)	NOT NULL,
limite_credito		decimal(7,2)	NOT NULL,
data_nasc			date			NOT NULL
PRIMARY KEY(cpf)
)

CREATE PROCEDURE sp_cli 
(
@opcao char (1),
@cpf   char(11),
@nome	varchar(100),
@email	varchar(200),
@limite_credito decimal (7,2),
@data_nasc	date,
@saida  varchar(200) OUTPUT
)

AS

	IF(UPPER(@opcao) = 'D' AND @cpf IS NOT NULL) 
		BEGIN
			DELETE cliente WHERE cpf = @cpf
				SET
					@saida = 'CPF ' + @cpf + ' excluido'
						END
					ElSE
				BEGIN

	IF(UPPER(@opcao) = 'U')
		BEGIN
			UPDATE cliente 
				SET nome = @nome,
					email = @email,
					limite_credito = @limite_credito,
					data_nasc = @data_nasc
						WHERE cpf = @cpf
							SET
								@saida = 'CPF ' + @cpf + ' atualizado'
								END	
							ELSE
						BEGIN

	IF(UPPER(@opcao) = 'I')
		BEGIN
			INSERT INTO cliente VALUES 
			(@cpf, @nome, @email, @limite_credito, @data_nasc)
				SET
					@saida = 'CPF ' + @cpf + '	inserido'
						END
					END
				END


SELECT * FROM cliente

