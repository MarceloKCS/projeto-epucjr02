<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.PaginaDeBusca" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaCongregacao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	ArrayList<Congregacao> resultadoDeBusca;
	resultadoDeBusca = null;
	FormularioDeBuscaDaCongregacao buscarCongregacao = (FormularioDeBuscaDaCongregacao) request.getAttribute("resultadoDaBusca");
	if(buscarCongregacao != null){
		resultadoDeBusca = buscarCongregacao.getListaDeCongregacaoDaPagina();
	}
%>


<%//@page import="org.eclipse.persistence.internal.oxm.schema.model.Include"%>

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
			<li id="liObreiros" class="tab_off"><a href="BuscarObreiro.jsp">Obreiro</a></li>
			<li id="liCongregacoes" class="tab_on"><a href="BuscarCongregacao.jsp">Congregação</a></li>
			<li id="liReunioes" class="tab_off"><a href="BuscarReuniao.jsp">Reunião</a></li>
		</ul>
		<div id="conteudo_tabmenu">
			<div id="buscarCongregacoes">
				<form name="buscarCongregacao" method="post" action="buscarCongregacao"	id="buscarCongregacao">
					<label for="Nome">Nome: </label><input type="text" name="Nome" id="Nome" />
					<button type="submit" name="botao_action" value="Buscar">Buscar Congregação</button>
				</form>
				
				
				<% if (buscarCongregacao != null) { %>
				<table id="resultadoBusca">
					<tr>
						<th>Nome</th>
						<th>Endereço</th>
						<th>Editar</th>
						<th>Excluir</th>
					</tr>
				
				<%
					Congregacao congregacao;
					for(int i = 0; i < resultadoDeBusca.size(); i++){	
						congregacao = (Congregacao) resultadoDeBusca.get(i);
				%>
					<tr class="<% out.println("zebra"+i%2); %>">
						<td><% out.println(congregacao.getNome()); %></td>
						<td><% out.println(congregacao.getEndereco()); %></td>
						<td><% out.println("<a href=http://localhost:8080/ProjetoEngyos/CongregacaoCarregaServlet?id="+congregacao.getIdCongregacao()+">Editar</a>"); %></td>
						<td><a href="#">Excluir</a></td>
					</tr>
				<% } %>
				
				</table>
				
				<p class="paginacao">
				<%
					// Paginação
					int numeroPagina = 1;
					int qntidadePage = buscarCongregacao.getPaginaTotal();
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