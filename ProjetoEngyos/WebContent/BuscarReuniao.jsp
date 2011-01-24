<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="com.epucjr.engyos.dominio.modelo.Reuniao" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.PaginaDeBusca" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaReuniao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%	
	FormularioDeBuscaDaReuniao resultadoDeBuscaDaReuniao = (FormularioDeBuscaDaReuniao) request.getAttribute("resultadoDeBuscaDaReuniao");
%>

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
			<li id="liCongregacoes" class="tab_off"><a href="BuscarCongregacao.jsp">Congregação</a></li>
			<li id="liReunioes" class="tab_on"><a href="BuscarReuniao.jsp">Reunião</a></li>
		</ul>
		<div id="conteudo_tabmenu">
			<div id="buscarReunioes">
				<form name="buscarReuniao" method="post" action="Controller" id="buscarReuniao">
					<label for="Data">Data: </label><input type="text" name="busca_input" id="Nome" />						
					<button type="submit" name="acao" value="buscar_reuniao">Buscar Reunião</button>
				</form>
				
				
				<% if (resultadoDeBuscaDaReuniao != null) { %>
				<table id="resultadoBusca">
					<tr>
						<th>Data</th>
						<th>Hora</th>
						<th>Local</th>
						<th>Editar</th>
						<th>Excluir</th>
					</tr>
				
				<%
				int countVisual = 0;
				List<Reuniao> reunioesDaPagina = resultadoDeBuscaDaReuniao.getListaDeReuniaoDaPagina();
				
				for(Reuniao reuniao : reunioesDaPagina){					
				%>
					<tr class="<% out.println("zebra" + countVisual % 2); %>">
						<td><% out.println(reuniao.getDia()+"/"+reuniao.getMes()+"/"+reuniao.getAno()+"</a>"); %></td>
						<td><% out.println(reuniao.getHora()+":"+reuniao.getMinuto()); %></td>
						<td><% out.println(reuniao.getLocal()); %></td>
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
				
				out.print("<p><b>Busca por: </b>" +  resultadoDeBuscaDaReuniao.getParametroDeBusca() + "</p> ");
				if(resultadoDeBuscaDaReuniao.getPaginaCorrente() == 1){
					out.println("<a class=\"paginaAtual\" href=\"Controller?busca_input=" + resultadoDeBuscaDaReuniao.getParametroDeBusca() + "&acao=buscar_reuniao&paginaCorrente="+numPagina+"\">" +numPagina+ "</a>");
				}
				else{
                	out.println("<a class=\"pagina\" href=\"Controller?busca_input=" + resultadoDeBuscaDaReuniao.getParametroDeBusca() + "&acao=buscar_reuniao&paginaCorrente="+numPagina+"\">" +numPagina+ "</a>");

				}
				
				for(numPagina = 2; numPagina <= resultadoDeBuscaDaReuniao.getQuantidadeTotalDePaginas(); numPagina++){
					if(numPagina == resultadoDeBuscaDaReuniao.getPaginaCorrente()){
						out.print("<a> | </a>" +"<a class=\"paginaAtual\">" +numPagina+ "</a>");
					}
					else{
						out.println("<a> | </a>" +"<a class=\"pagina\" href=\"Controller?busca_input=" + resultadoDeBuscaDaReuniao.getParametroDeBusca() + "&acao=buscar_reuniao&paginaCorrente="+numPagina+"\">" +numPagina+ "</a>");
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