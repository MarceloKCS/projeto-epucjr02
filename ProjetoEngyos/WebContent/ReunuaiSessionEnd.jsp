<%-- 
    Document   : ReunuaiSessionEnd
    Created on : 03/04/2011, 23:47:32
    Author     : Rogerio
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="refresh" content="5;url=Principal.jsp">
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
					<%@ include file = "Menu.html" %>
				</ul>
			</div>
			<div id="conteudo">
                            <p>Reunião encerrada, redirecionando para a página principal...</p>
				<div id="colunaE">(Calendário)</div>
				<div id="colunaD">
					<h2 class="h2_subtitulo proximasReunioes">Próximas Reuniões</h2>					
				</div>
			</div>
			<div id="rodape">
				<%@ include file = "Rodape.html" %>
			</div>
		</div>
	</body>
</html>
