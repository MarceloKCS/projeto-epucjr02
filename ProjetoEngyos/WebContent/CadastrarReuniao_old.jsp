<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
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
	session = request.getSession();
	formularioDeReuniao = (FormularioDeReuniao) session.getAttribute("viewReuniao");
	validadorDeReuniao = (ValidadorDeFormularioDeReuniao) session.getAttribute("errorReuniao");
	%>
	<form name=formularioReuniao method=post action=registrarReuniao id=formularioReuniao>
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
							String dia = formularioDeReuniao.obterCampoPreenchido("Dia");
							if(dia.equals(posicao)){
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
							String mes = formularioDeReuniao.obterCampoPreenchido("Mes");
							if(mes.equals(posicao)){
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
							String ano = formularioDeReuniao.obterCampoPreenchido("Ano");
							if(ano.equals(posicao)){
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
			<button type=submit name=botao_action value=Cadastrar>Cadastrar</button>
			</p>
		</form>
	</fieldset>
</body>
</html>
