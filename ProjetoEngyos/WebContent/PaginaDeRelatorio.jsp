<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<title>Projeto Engyos - Controle de Presença</title>
	<link href="css/screen.css" rel="stylesheet" type="text/css" />
</head>
	<body id="principal">
		<div id="all">
			<div id="topo">
				<%@ include file = "Topo.jsp" %>
			</div>
			<div id="menu">
				<ul id="menu_principal">
					<%@ include file = "menu_administrativo.html" %>
				</ul>
			</div>
			<div id="conteudo">
				<div id="colunaE">
                                    <p><a href="#">Obter lista de obreiros por congregação</a></p>

                                </div>
				<div id="colunaD">
					
				</div>
			</div>
			<div id="rodape">
				<%@ include file = "Rodape.html" %>
			</div>
		</div>
	</body>
</html>