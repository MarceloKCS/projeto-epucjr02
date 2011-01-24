<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.PaginaDeBusca" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaCongregacao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	FormularioDeBuscaDaCongregacao resultadoDeBuscaDaCongregacao = (FormularioDeBuscaDaCongregacao) request.getAttribute("resultadoDeBuscaDaCongregacao");
%>


<%//@page import="org.eclipse.persistence.internal.oxm.schema.model.Include"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
				<form name="buscarCongregacao" method="post" action="Controller"	id="buscarCongregacao">
					<label for="Nome">Parâmetro: </label><input type="text" name="busca_input" id="Nome" />
					<button type="submit" name="acao" value="buscar_congregacao">Buscar Congregação</button>
				</form>
				
				
				<% if (resultadoDeBuscaDaCongregacao != null) { %>
				<table id="resultadoBusca">
					<tr>
						<th>Nome</th>
						<th>Endereço</th>
						<th>Editar</th>
						<th>Excluir</th>
					</tr>
				
				<%
				int countVisual = 0;
				List<Congregacao> congregacoesDaPagina = resultadoDeBuscaDaCongregacao.getListaDeCongregacaoDaPagina();
				for(Congregacao congregacao : congregacoesDaPagina){	
						
				%>
					<tr class="<% out.println("zebra" + countVisual % 2); %>">
						<td><% out.println(congregacao.getNome()); %></td>
						<td><% out.println(congregacao.getEndereco()); %></td>
						<td><a href="#"><img src="imagens/edit.png" width="18" height="18" title="Alterar Dados"/></a></td>
						<td><a href="#"><img src="imagens/error.png" width="18" height="18" title="Apagar Dados"/></a></td>
					</tr>
				<% 
				countVisual++;
				}
				%>
				
				</table>
				
				<p class="paginacao">
				
				<%
				int numPagina = 1;							
				
				out.print("<p><b>Busca por: </b>" +  resultadoDeBuscaDaCongregacao.getParametroDeBusca() + "</p> ");
				if(resultadoDeBuscaDaCongregacao.getPaginaCorrente() == 1){
					out.println("<a class=\"paginaAtual\" href=\"Controller?busca_input=" + resultadoDeBuscaDaCongregacao.getParametroDeBusca() + "&acao=buscar_congregacao&paginaCorrente="+numPagina+"\">" +numPagina+ "</a>");
				}
				else{
                	out.println("<a class=\"pagina\" href=\"Controller?busca_input=" + resultadoDeBuscaDaCongregacao.getParametroDeBusca() + "&acao=buscar_congregacao&paginaCorrente="+numPagina+"\">" +numPagina+ "</a>");

				}
				
				for(numPagina = 2; numPagina <= resultadoDeBuscaDaCongregacao.getQuantidadeTotalDePaginas(); numPagina++){
					if(numPagina == resultadoDeBuscaDaCongregacao.getPaginaCorrente()){
						out.print("<a> | </a>" +"<a class=\"paginaAtual\">" +numPagina+ "</a>");
					}
					else{
						out.println("<a> | </a>" +"<a class=\"pagina\" href=\"Controller?busca_input=" + resultadoDeBuscaDaCongregacao.getParametroDeBusca() + "&acao=buscar_congregacao&paginaCorrente="+numPagina+"\">" +numPagina+ "</a>");
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