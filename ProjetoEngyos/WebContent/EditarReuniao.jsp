<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Reuniao" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao" %>
<%
FormularioDeReuniao formularioDeReuniao;
ValidadorDeFormularioDeReuniao validadorDeReuniao;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Formulário - Reunião</title>
	<meta http-equiv="Content-Type"  content="text/html; charset=utf-8" /> 
</head>
<body>
	<fieldset>
	<legend>Formulário - Reunião</legend>
	<%
	formularioDeReuniao = (FormularioDeReuniao) request.getAttribute("viewReuniao");
	validadorDeReuniao = (ValidadorDeFormularioDeReuniao) request.getAttribute("errorReuniao");
	%>
	<form name=formularioReuniao method=post action=editarReuniao id=formularioReuniao>
			<p>
				<label for="Local">Local: Parque São Joaquim</label>
			</p>
			<p>
				<label for="Data">Data: </label>
				<select name="dataReuniaoDia">
					<option value=""></option>
					<%
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("Dia")){
						for(int posicao=1; posicao<= 31; posicao++){
							int dia = Integer.parseInt(formularioDeReuniao.obterCampoPreenchido("Dia"));
							
							if(dia == posicao){
								out.println("<option selected=\"selected\" value=" + posicao +">"+ posicao +"</option>");
							}
							else{
								out.println("<option value=\""+posicao+"\">"+posicao+"</option>");
							}
						}
					}
					else{
						for(int posicao=1; posicao<= 31; posicao++){
							out.println("<option value=\""+posicao+"\">"+posicao+"</option>");
						}
					}
					%>
				</select>
				<select name="dataReuniaoMes">
					<option value=""></option>
					<%
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("Mes")){
						for(int posicao=1; posicao<= 12; posicao++){
							int mes = Integer.parseInt(formularioDeReuniao.obterCampoPreenchido("Mes"));
							if(mes == posicao){
								out.println("<option selected=\"selected\" value=" + posicao +">"+posicao+"</option>");
							}
							else{
								out.println("<option value=\""+posicao+"\">"+posicao+"</option>");
							}
						}
					}
					else{
						for(int posicao=1; posicao<= 12; posicao++){
							out.println("<option value=\""+posicao+"\">"+posicao+"</option>");
						}
					}
					%>
				</select>
				<select name="dataReuniaoAno">
					<option value=""></option>
					<%
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("Ano")){
						for(int posicao=2010; posicao<= 2030; posicao++){
							int ano = Integer.parseInt(formularioDeReuniao.obterCampoPreenchido("Ano"));
							if(ano == posicao){
								out.println("<option selected=\"selected\" value=" + posicao +">"+posicao+"</option>");
							}
							else{
								out.println("<option value=\""+posicao+"\">"+posicao+"</option>");
							}
						}
					}
					else{
						for(int posicao=2010; posicao<= 2030; posicao++){
							out.println("<option value=\""+posicao+"\">"+posicao+"</option>");
						}
					}
					%>
				</select>
			</p>
			<p>
				<label for="Hora">Hora: </label>
				<input type="text" name="Hora" value="
				<%
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("Hora")){
						out.println(formularioDeReuniao.obterCampoPreenchido("Hora"));
					}
				%>
				" id="Hora" />
				<%
					if(validadorDeReuniao != null && validadorDeReuniao.verificarCampoComErro("Hora")){
						out.println(validadorDeReuniao.obterCampoComErro("Hora"));
					}
				%>
			</p>
			<p>
			<button type=submit name=botao_action value=Editar>Editar</button>
			</p>
		</form>
	</fieldset>
</body>
</html>
