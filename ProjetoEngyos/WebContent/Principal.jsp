<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<title>Projeto Engyos - Controle de Presen�a</title>
	<link href="css/screen.css" rel="stylesheet" type="text/css" />
</head>
	<body id="principal"> 
		<div id="all">
			<div id="topo">
				<%@ include file = "Topo.html" %>
			</div>
			<div id="menu">
				<ul id="menu_principal">
					<%@ include file = "Menu.html" %>
				</ul>
			</div>
			<div id="conteudo">
				<div id="colunaE">(Calend�rio)</div>
				<div id="colunaD">
					<h2 class="h2_subtitulo proximasReunioes">Pr�ximas Reuni�es</h2>
					<img src="imagens/calendar.png" />
				</div>
			</div>
			<div id="rodape">
				<%@ include file = "Rodape.html" %>
			</div>
		</div>
	</body>
</html>