<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.PaginaDeBusca" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDoObreiro" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	ArrayList<Obreiro> resultadoDeBusca;
	resultadoDeBusca = null;
	FormularioDeBuscaDoObreiro buscarObreiro = (FormularioDeBuscaDoObreiro) request.getAttribute("resultadoDaBusca");
	if(buscarObreiro != null){
		resultadoDeBusca = buscarObreiro.getListaDeCongregacaoDaPagina();
	}
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Buscar - Projeto Engyos - Controle de Presença</title>
	<link href="screen.css" rel="stylesheet" type="text/css" />
	
</head>
<body id="buscarCongregacao"> 
<div id="all">
	<div id="topo">
		<%@ include file = "Topo.html" %>
	</div>
	<div id="menu">
		<%@ include file = "Menu.html" %>
	</div>
	<div id="conteudo">
		<ul id="tabmenu">
			<li id="liObreiros" class="tab_on"><a href="BuscarObreiro.jsp">Obreiro</a></li>
			<li id="liCongregacoes" class="tab_off"><a href="BuscarCongregacao.jsp">Congregação</a></li>
			<li id="liReunioes" class="tab_off"><a href="BuscarReuniao.jsp">Reunião</a></li>
		</ul>
		<div id="conteudo_tabmenu">
			<div id="buscarObreiros">
				<form name="buscarObreiro" method="post" action="buscarObreiro"	id="buscarObreiro">
					<label for="Nome">Nome: </label><input type="text" name="Nome" id="Nome" />
					<!--<label for="Cpf">CPF: </label><input type="text" name="Cpf" maxlength="11" id="Cpf" />-->
					<button type="submit" name="botao_action" value="Buscar">Buscar Obreiro</button>
				</form>
				
				
				<% if (buscarObreiro != null) { %>
				<table id="resultadoBusca">
					<tr>
						<th>CPF</th>
						<th>Nome</th>
						<th>Cargo</th>
						<th>Congregação</th>
					</tr>
				
				<%
					Obreiro obreiro;
					for(int i = 0; i < resultadoDeBusca.size(); i++){	
						obreiro = (Obreiro) resultadoDeBusca.get(i);
				%>
					<tr class="<% out.println("zebra"+i%2); %>">
						<td><% out.println("<a href=http://localhost:8080/ProjetoEngyos/ObreiroCarregaServlet?cpf="+obreiro.getCpf()+">"+obreiro.getCpf()+"</a>"); %></td>
						<td><% out.println("<a href=http://localhost:8080/ProjetoEngyos/ObreiroCarregaServlet?cpf="+obreiro.getCpf()+">"+obreiro.getNome()+"</a>"); %></td>
						<td><% out.println("<a href=http://localhost:8080/ProjetoEngyos/ObreiroCarregaServlet?cpf="+obreiro.getCpf()+">"+obreiro.getCargo()+"</a>"); %></td>
						<td><% out.println("<a href=#>"+obreiro.getCongregacao()+"</a>"); %></td>
					</tr>
				<% } %>
				
				</table>
				
				<p class="paginacao">
				<%
					// Paginação
					int numeroPagina = 1;
					int qntidadePage = buscarObreiro.getPaginaTotal();
					out.println("<a href=http://localhost:8080/ProjetoEngyos/NavegarPaginaBusca?numeroPagina=" + numeroPagina + ">" +numeroPagina+ "</a>");
					if(qntidadePage > numeroPagina){
						for(numeroPagina = 2; numeroPagina<=qntidadePage; numeroPagina++){
							out.println(" | " +"<a href=http://localhost:8080/ProjetoEngyos/NavegarPaginaBusca?numeroPagina=" + numeroPagina + ">" +numeroPagina+ "</a>");
						}
					}
				%>				
				</p>
				<% } %>
				
			</div>
		</div>
				
	</div>
	<div id="rodape">
		<%@ include file = "Rodape.html" %>
	</div>
</div>
</body>
</html>