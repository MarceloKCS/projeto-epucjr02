<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeCongregacao" %>
<%
	FormularioDeCongregacao formularioDeCongregacao;
	ValidadorDeFormularioDeCongregacao validadorDeCongregacao;
	formularioDeCongregacao = (FormularioDeCongregacao) request.getAttribute("viewCongregacao");
	validadorDeCongregacao = (ValidadorDeFormularioDeCongregacao) request.getAttribute("errorCongregacao");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="javax.swing.JOptionPane"%><html>
<head>
	<title>Formulário - Congregação</title>
	<meta http-equiv="Content-Type"  content="text/html; charset=utf-8" /> 
</head>
<body>
<% 
	if(formularioDeCongregacao != null){
		out.println(formularioDeCongregacao.getStatus()+"<br />");
		if(formularioDeCongregacao.getStatus().equals("Sucesso ao Editar")){
			out.println("Foram alterados no banco de dados <br />");
			Congregacao congregacaoCadastrada = (Congregacao) request.getAttribute("Congregacao");
			out.println(congregacaoCadastrada.getNome()+"<br />");
			out.println(congregacaoCadastrada.getEndereco());
		}
	}
%>

	<fieldset>
	<legend>Formulário - Congregação</legend>
	<form name=formularioCongregacao method=post action=editarCongregacao id=formularioCongregacao>
			<p>
				<label for="Nome">Nome: </label>
				<input type="text" name="Nome" value="
				<%
					if(formularioDeCongregacao != null && formularioDeCongregacao.verificarCampoPreenchido("Nome")){
						out.println(formularioDeCongregacao.obterCampoPreenchido("Nome"));
					}
				%>
				" id="Nome"/>
				<%
					if(validadorDeCongregacao != null && validadorDeCongregacao.verificarCampoComErro("Nome")){
						out.println(validadorDeCongregacao.obterCampoComErro("Nome"));
					}
				%>
			</p>
			<p>
				<label for="Endereco">Endereço: </label>
				<input type="text" name="Endereco" value="
				<%
					if(formularioDeCongregacao != null && formularioDeCongregacao.verificarCampoPreenchido("Endereco")){
						out.println(formularioDeCongregacao.obterCampoPreenchido("Endereco"));
						
					}
				%>
				" id="Endereco"/>
				<%
					if(validadorDeCongregacao != null && validadorDeCongregacao.verificarCampoComErro("Endereco")){
						out.println(validadorDeCongregacao.obterCampoComErro("Endereco"));
					}
				%>
			</p>
			<p>
			<button type=submit name=botao_action value=Editar>Editar</button>
			</p>
			
			<input type="hidden" name="idUsuario" value="">
			<input type="hidden" name="idCongregacao" value="<%out.print(formularioDeCongregacao.obterCampoPreenchido("idCongregacao")); %>">
		</form>
	</fieldset>
</body>
</html>
