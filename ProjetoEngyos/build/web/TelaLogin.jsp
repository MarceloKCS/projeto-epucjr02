<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Projeto Engyos - Controle de Presença</title>
	<link href="css/screen.css" rel="stylesheet" type="text/css" />
</head>
<body id="telaLogin"> 
<div id="all">
	<div id="conteudo_tabmenu" class="formularioLogin"><!-- div chama "conteudo_tabmenu" para utuilizar o mesmo CSS para o form de login -->
		<img src="imagens/logo.png" alt="Projeto Engyos - Controle de Presen&ccedil;a" class="logo" />
		<h2>Acesso ao Painel - Controle de Presen&ccedil;a</h2>
		<form name="formularioLogin" method="post" action="LoginServlet" id="formularioLogin">
			<p>
				<label for="login">Login: </label>
				<input type="text" name="login" />
			</p>
			<p>
				<label for="senha">Senha: </label>
				<input type="password" name="senha"/>
			</p>
			<p>
				<button type="submit" value="Acessar">Acessar</button>
			</p>
		</form>
	<div style="clear: both;"><!-- correção de bug css --></div> 
	</div>
</div>
</body>
</html>