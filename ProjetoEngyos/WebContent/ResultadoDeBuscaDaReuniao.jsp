<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Reuniao" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.PaginaDeBusca" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	PaginaDeBusca resultadoDaPaginacao = (PaginaDeBusca) request.getAttribute("resultadoDaBusca");
	request.setAttribute("resultadoDaBusca", resultadoDaPaginacao);
	String tipoDeBusca = (String) request.getAttribute("tipoDeBusca");
	Reuniao resultadoDaBusca;
	ArrayList listaDePaginaDeBusca;
	
	if(tipoDeBusca != null){	
		listaDePaginaDeBusca = (ArrayList)resultadoDaPaginacao.getListaDePaginaDeBusca().get(0);
	}
	else{
		listaDePaginaDeBusca = (ArrayList)request.getAttribute("carregarBusca");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" name="tipoDeBusca" value="<% out.println(tipoDeBusca); %>">
<%
	for(int i = 0; i < listaDePaginaDeBusca.size(); i++){	
		resultadoDaBusca = (Reuniao) resultadoDaPaginacao.getPaginaListaPaginaBusca().get(0).get(i);
		out.println(resultadoDaBusca.getData());
		out.println(resultadoDaBusca.getHora());
		out.println("<br />");
	}
%>
</body>
</html>