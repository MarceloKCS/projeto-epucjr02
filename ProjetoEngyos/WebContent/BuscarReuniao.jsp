<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="com.epucjr.engyos.dominio.modelo.Reuniao" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.PaginaDeBusca" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaReuniao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	ArrayList<Reuniao> resultadoDeBusca;
	resultadoDeBusca = null;
	FormularioDeBuscaDaReuniao buscarReuniao = (FormularioDeBuscaDaReuniao) request.getAttribute("resultadoDaBusca");
	if(buscarReuniao != null){
		resultadoDeBusca = buscarReuniao.getListaDeCongregacaoDaPagina();
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
			<li id="liObreiros" class="tab_off"><a href="BuscarObreiro.jsp">Obreiro</a></li>
			<li id="liCongregacoes" class="tab_off"><a href="BuscarCongregacao.jsp">Congregação</a></li>
			<li id="liReunioes" class="tab_on"><a href="BuscarReuniao.jsp">Reunião</a></li>
		</ul>
		<div id="conteudo_tabmenu">
			<div id="buscarReunioes">
				<form name="buscarReuniao" method="post" action="buscarReuniao"	id="buscarReuniao">
					<label for="Data">Data: </label>
						<select name="dataReuniaoDia">
							<option value="00"></option>
							<%
								String zero;
								for(int posicao=1; posicao<= 31; posicao++){
									if(posicao <10){
										zero = "0";
										zero = zero + Integer.toString(posicao);
									}
									else{
										zero = Integer.toString(posicao);
									}
									out.println("<option value=\""+zero+"\">"+zero+"</option>");
								}
							%>
						</select>
						<select name="dataReuniaoMes">
							<option value="00"></option>
							<%
								for(int posicao=1; posicao<= 12; posicao++){
									if(posicao <10){
										zero = "0";
										zero = zero + Integer.toString(posicao);
									}
									else{
										zero = Integer.toString(posicao);
									}
									out.println("<option value=\""+zero+"\">"+zero+"</option>");
								}
							%>
						</select>
						<select name="dataReuniaoAno">
							<option value="0000"></option>
							<%
								for(int posicao=2010; posicao<= 2030; posicao++){
									out.println("<option value=\""+posicao+"\">"+posicao+"</option>");
								}
							%>
						</select>
					<button type="submit" name="botao_action" value="Buscar">Buscar Reunião</button>
				</form>
				
				
				<% if (buscarReuniao != null) { %>
				<table id="resultadoBusca">
					<tr>
						<th>Data</th>
						<th>Hora</th>
					</tr>
				
				<%
					Reuniao reuniao;
					for(int i = 0; i < resultadoDeBusca.size(); i++){	
						reuniao = (Reuniao) resultadoDeBusca.get(i);
				%>
					<tr class="<% out.println("zebra"+i%2); %>">
						<td><% out.println("<a href=http://localhost:8080/ProjetoEngyos/ReuniaoCarregaServlet?idReuniao="+reuniao.getIdReuniao()+">"+reuniao.getDia()+"/"+reuniao.getMes()+"/"+reuniao.getAno()+"</a>"); %></td>
						<td><% out.println(reuniao.getHora()+":"+reuniao.getMinuto()); %></td>
					</tr>
				<% } %>
				
				</table>
				
				<p class="paginacao">
				<%
					// Paginação
					int numeroPagina = 1;
					int qntidadePage = buscarReuniao.getPaginaTotal();
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