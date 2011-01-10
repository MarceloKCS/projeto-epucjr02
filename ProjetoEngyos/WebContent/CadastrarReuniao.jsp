<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Reuniao" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao" %>
<%
	FormularioDeReuniao formularioDeReuniao;
	ValidadorDeFormularioDeReuniao validadorDeReuniao;
	formularioDeReuniao = (FormularioDeReuniao) request.getAttribute("viewReuniao");
	validadorDeReuniao = (ValidadorDeFormularioDeReuniao) request.getAttribute("errorReuniao");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Reuniões - Projeto Engyos - Controle de Presença</title>
	<link href="screen.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript">
	// Função para esconder a Div após alguns segundos
	function esconderDiv() {
		var div = document.getElementById('mensagemRetorno');
		div.style.display = 'none';
	}
	// esconderá a div "mensagemRetorno" após 9 segundos.
	window.setTimeout(esconderDiv, 5000);
	
	</script>	

</head>
<body id="reunioes"> 
<div id="all">
	<div id="topo">
		<%@ include file = "Topo.html" %>
	</div>
	<div id="menu">
		<%@ include file = "Menu.html" %>
	</div>
	<div id="conteudo">
		<ul id="tabmenu">
			<li><a href="#">Cadastro</a></li>
		</ul>
		<div id="conteudo_tabmenu">
		<div id="mensagemRetorno">
				<%
					if (formularioDeReuniao != null) {
						out.println("<script type=\"text/javascript\">document.getElementById('mensagemRetorno').style.display = 'block';</script>");
						out.println(formularioDeReuniao.getStatus() + "<br />");
						if (formularioDeReuniao.getStatus().equals(
								"Sucesso ao Cadastrar")) {
							out.println("<p>Foram incluidos no banco de dados</p>");
							Reuniao reuniaoCadastrado = (Reuniao) request
									.getAttribute("reuniao");
							out.println("Data: "+reuniaoCadastrado.getDia() + "/" + reuniaoCadastrado.getMes() + "/" + reuniaoCadastrado.getAno() + "<br />");
							out.println("Hora: "+reuniaoCadastrado.getHora() + ":" + reuniaoCadastrado.getMinuto()+ "<br />");
						}
					}
					
				%>
		</div>
	<form name="formularioReuniao" method="post" action="registrarReuniao" id="formularioReuniao">
			<p>
				<label for="Local">Local: Parque São Joaquim</label>
			</p>
			<p>
				<label for="Data">Data: </label>
				<select name="dataReuniaoDia">
					<option value="00"></option>
					<%
					String zero;
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("Dia")){
						String dia = formularioDeReuniao.obterCampoPreenchido("Dia");
						for(int posicao=1; posicao<= 31; posicao++){
							if(posicao <10){
								zero = "0";
								zero = zero + Integer.toString(posicao);
							}
							else{
								zero = Integer.toString(posicao);
							}
							if(dia.equals(zero)){
								out.println("<option selected=\"selected\" value=" + zero +">"+ zero +"</option>");
							}
							else{
								out.println("<option value=\""+zero+"\">"+zero+"</option>");
							}
						}
					}
					else{
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
					}
					%>
				</select>
				
				
				<select name="dataReuniaoMes">
					<option value="00"></option>
					<%
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("Mes")){

						String mes = formularioDeReuniao.obterCampoPreenchido("Mes");
						for(int posicao=1; posicao<= 12; posicao++){
							if(posicao <10){
								zero = "0";
								zero = zero + Integer.toString(posicao);
							}
							else{
								zero = Integer.toString(posicao);
							}
							if(mes.equals(zero)){
								out.println("<option selected=\"selected\" value=" + zero +">"+zero+"</option>");
							}
							else{
								out.println("<option value=\""+zero+"\">"+zero+"</option>");
							}
						}
					}
					else{
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
					}
					%>
				</select>
				<select name="dataReuniaoAno">
					<option value="0000"></option>
					<%
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("Ano")){
						String ano = formularioDeReuniao.obterCampoPreenchido("Ano");
						for(int posicao=2010; posicao<= 2030; posicao++){
							if(ano.equals(Integer.toString(posicao))){
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
			
				<span class="erroCampoFormulario">				
				<%
									if (validadorDeReuniao != null
											&& validadorDeReuniao.verificarCampoComErro("Data")) {
										out.println(validadorDeReuniao
												.obterCampoComErro("Data"));
									}
								%>
				</span>
				
			</p>
			<p>
<p>
				<label for="Data">Hora: </label>
				<select name="horaReuniao">
					<option value="24"></option>
					<%
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("Hora")){
						String hora = formularioDeReuniao.obterCampoPreenchido("Hora");
						for(int posicao=0; posicao< 24; posicao++){
							if(posicao <10){
								zero = "0";
								zero = zero + Integer.toString(posicao);
							}
							else{
								zero = Integer.toString(posicao);
							}
							
							if(hora.equals(zero)){
								out.println("<option selected=\"selected\" value=" + zero +">"+ zero +"</option>");
							}
							else{
								out.println("<option value=\""+zero+"\">"+zero+"</option>");
							}
						}
					}
					else{
						for(int posicao=0; posicao < 24; posicao++){
							if(posicao <10){
								zero = "0";
								zero = zero + Integer.toString(posicao);
							}
							else{
								zero = Integer.toString(posicao);
							}
							out.println("<option value=\""+zero+"\">"+zero+"</option>");
						}
					}
					%>
				</select>
				<select name="minutoReuniao">
					<option value="60"></option>
					<%
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("Minuto")){
						String minuto = formularioDeReuniao.obterCampoPreenchido("Minuto");
						for(int posicao=0; posicao< 60; posicao++){
							if(posicao < 10){
								zero = "0";
								zero = zero + posicao;
							}
							else{
								zero = ""+posicao;
							}
							
							if(minuto.equals(zero)){
								out.println("<option selected=\"selected\" value=" + zero +">"+zero+"</option>");
							}
							else{
								out.println("<option value=\""+zero+"\">"+zero+"</option>");
							}
						}
					}
					
					else{
						for(int posicao=0; posicao< 60; posicao++){
							if(posicao <10){
								zero = "0";
								zero = zero + Integer.toString(posicao);
							}
							else{
								zero = Integer.toString(posicao);
							}
							out.println("<option value=\""+zero+"\">"+zero+"</option>");
						}
					}
					%>
				</select>

			
			
				<span class="erroCampoFormulario">				
				<%
									if (validadorDeReuniao != null
											&& validadorDeReuniao.verificarCampoComErro("Hora")) {
										out.println(validadorDeReuniao
												.obterCampoComErro("Hora"));
									}
								%>
				</span>
				
				
			</p>
			<p>
			<button type="submit" name="botao_action" value="Cadastrar">Cadastrar</button>
			</p>
		</form>
		</div>
	</div>
	<div id="rodape">
		<%@ include file = "Rodape.html" %>
	</div>
</div>
</body>
</html>