<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.PaginaDeBusca" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	PaginaDeBusca resultadoDaPaginacao = (PaginaDeBusca) request.getAttribute("resultadoDaBusca");
	request.setAttribute("resultadoDaBusca", resultadoDaPaginacao);
	String tipoDeBusca = (String) request.getAttribute("tipoDeBusca");
	Obreiro resultadoDaBusca;
	ArrayList listaDePaginaDeBusca;
	
	if(tipoDeBusca != null){	
		listaDePaginaDeBusca = (ArrayList)resultadoDaPaginacao.getListaDePaginaDeBusca().get(0);
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
		<td>Cargo</td>
		<td>Congregação</td>
		<td>CPF</td>
	</tr>
<input type="hidden" name="tipoDeBusca" value="<% out.println(tipoDeBusca); %>" />
<%

	for(int i = 0; i < listaDePaginaDeBusca.size(); i++){	

%>
	<tr>
		<td><% out.println(resultadoDaBusca.getNome()); %></td>
		<td><% out.println(resultadoDaBusca.getCargo()); %></td>
		<td><% out.println(resultadoDaBusca.getCongregacao()); %></td>
		<td><% out.println(resultadoDaBusca.getCpf()); %></td>
		
	</tr>
<% } %>
</table>
</body>
</html>