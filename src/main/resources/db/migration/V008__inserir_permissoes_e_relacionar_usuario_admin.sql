INSERT INTO permissoes VALUES (1, 'ROLE_CADASTRAR_CIDADE');
INSERT INTO permissoes VALUES (2, 'ROLE_CADASTRAR_USUARIO');

INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (1, 1);
INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (1, 2);

INSERT INTO usuarios_grupos (usuario_id, grupo_id) VALUES (
	(SELECT id FROM usuarios WHERE email = 'admin@ameridata.com.br'), 1);