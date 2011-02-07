<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.PaginaDeBusca" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	session = request.getSession();
	PaginaDeBusca resultadoDaPaginacao = (PaginaDeBusca) session.getAttribute("resultadoDaBusca");
	String tipoDeBusca = (String) session.getAttribute("tipoDeBusca");
	String buscaInicio = (String) request.getAttribute("buscaInicio");
	Congregacao resultadoDaBusca;
	ArrayList listaDePaginaDeBusca;
	
	if(buscaInicio != null){	
		listaDePaginaDeBusca = (ArrayList) resultadoDaPaginacao.getPaginaListaPaginaBusca().get(0);
	}
	else{
		listaDePaginaDeBusca = (ArrayList) request.getAttribute("carregarBusca");
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" name="tipoDeBusca" value="<% out.println(tipoDeBusca); %>">
<table>
	<tr>
		<td>Nome<td/>
		<td>Endereço</td>
		<td>Editar</td>
		<td>Excluir</td>
	</tr>
<%
	for(int i = 0; i < listaDePaginaDeBusca.size(); i++){	
		resultadoDaBusca = (Congregacao) listaDePaginaDeBusca.get(i);
%>
	<tr>
		<td><% out.println(resultadoDaBusca.getNome()); %></td>
		<td><% out.println(resultadoDaBusca.getEndereco()); %></td>
		<td>Editar</td>
		<td>Excluir</td>
	</tr>
<% } %>

	<%
			int numeroPagina = 1;
			int qntidadePage = resultadoDaPaginacao.getPaginaTotal();
			out.println("<a href=http://localhost:8080/ProjetoEngyos/NavegarPaginaBusca?numeroPagina=" + numeroPagina + ">" +numeroPagina+ "</a>");
			if(qntidadePage > numeroPagina){
				for(numeroPagina = 2; numeroPagina<=qntidadePage; numeroPagina++){
					out.println(" | " +"<a href=http://localhost:8080/ProjetoEngyos/NavegarPaginaBusca?numeroPagina=" + numeroPagina + ">" +numeroPagina+ "</a>");
				}
			}
%>
</table>
</body>
</html>