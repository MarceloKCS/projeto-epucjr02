<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDoObreiro" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%	
	FormularioDeBuscaDoObreiro resultadoDeBuscaDoObreiro = (FormularioDeBuscaDoObreiro) request.getAttribute("resultadoDeBuscaDoObreiro");
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
			<li id="liObreiros" class="tab_on"><a href="BuscarObreiro.jsp">Obreiro</a></li>
			<li id="liCongregacoes" class="tab_off"><a href="BuscarCongregacao.jsp">Congregação</a></li>
			<li id="liReunioes" class="tab_off"><a href="BuscarReuniao.jsp">Reunião</a></li>
		</ul>
		<div id="conteudo_tabmenu">
			<div id="buscarObreiros">
				<form name="buscarObreiro" method="post" action="Controller" id="buscarObreiro">
					<label for="Nome">Parâmetro: </label><input type="text" name="busca_input" id="Nome" />
					<!--<label for="Cpf">CPF: </label><input type="text" name="Cpf" maxlength="11" id="Cpf" />-->
					<button type="submit" name="acao" value="buscar_obreiro">Buscar Obreiro</button>
				</form>
				
				
				<% if (resultadoDeBuscaDoObreiro != null) { %>
				<table id="resultadoBusca">
					<tr>
						<th>CPF</th>
						<th>Nome</th>
						<th>Cargo</th>
						<th>Congregação</th>
						<th>Editar</th>
						<th>|Excluir</th>
					</tr>
				
				<%
					int countVisual = 0;
					List<Obreiro> obreirosDaPAgina = resultadoDeBuscaDoObreiro.getListaDeObreiroDaPagina();
					for(Obreiro obreiro: obreirosDaPAgina){	
						
				%>
					<tr class="<% out.println("zebra" + countVisual % 2); %>">
						<td><% out.println(obreiro.getCpf()); %></td>
						<td>|<% out.println(obreiro.getNome()); %></td>
						<td>|<% out.println(obreiro.getCargo()); %></td>
						<td>|<% out.println(obreiro.getCongregacao().getNome()); %></td>
						<!-- <td><a href="#"><img src="imagens/error.png" width="18" height="18" title="Apagar Dados"/></a> | <a href="#"><img src="imagens/edit.png" width="18" height="18" title="Alterar Dados"/></a></td> -->
                                                <td><a href="Controller?acao=obreiro_editformload&cpfObreiro=<% out.print(obreiro.getCpf()); %>"><img src="imagens/edit.png" width="18" height="18" title="Alterar Dados"/></a></td>
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
				
				out.print("<p><b>Busca por: </b>" +  resultadoDeBuscaDoObreiro.getParametroDeBusca() + "</p> ");
				if(resultadoDeBuscaDoObreiro.getPaginaCorrente() == 1){
					out.println("<a class=\"paginaAtual\" href=\"Controller?busca_input=" + resultadoDeBuscaDoObreiro.getParametroDeBusca() + "&acao=buscar_obreiro&paginaCorrente="+numPagina+"\">" +numPagina+ "</a>");
				}
				else{
                	out.println("<a class=\"pagina\" href=\"Controller?busca_input=" + resultadoDeBuscaDoObreiro.getParametroDeBusca() + "&acao=buscar_obreiro&paginaCorrente="+numPagina+"\">" +numPagina+ "</a>");

				}
				
				for(numPagina = 2; numPagina <= resultadoDeBuscaDoObreiro.getQuantidadeTotalDePaginas(); numPagina++){
					if(numPagina == resultadoDeBuscaDoObreiro.getPaginaCorrente()){
						out.print("<a> | </a>" +"<a class=\"paginaAtual\">" +numPagina+ "</a>");
					}
					else{
						out.println("<a> | </a>" +"<a class=\"pagina\" href=\"Controller?busca_input=" + resultadoDeBuscaDoObreiro.getParametroDeBusca() + "&acao=buscar_obreiro&paginaCorrente="+numPagina+"\">" +numPagina+ "</a>");
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