<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro" %>
<%
	FormularioDeObreiro formularioDeObreiro;
	ValidadorDeFormularioDeObreiro validadorDeObreiro;
	formularioDeObreiro = (FormularioDeObreiro) request.getAttribute("viewObreiro");
	validadorDeObreiro = (ValidadorDeFormularioDeObreiro) request.getAttribute("errorObreiro");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Obreiros - Projeto Engyos - Controle de Presença</title>
	<link href="screen.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript">
		// Função para esconder a Div após alguns segundos
		function esconderDiv() {
			var div = document.getElementById('mensagemRetorno');
			div.style.display = 'none';
		}
		// esconderá a div "mensagemRetorno" após 9 segundos.
		window.setTimeout(esconderDiv, 9000);

		// Função para mostrarTooltip
		function mostrarTooltip(elemento, mensagem) {
			var el = document.getElementById(elemento);
			el.innerHTML = mensagem;
		}
		
	</script>

</head>
<body id="obreiros"> 
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
					if (formularioDeObreiro != null) {
						out.println(formularioDeObreiro.getStatus() + "<br />");
						out.println("<script type=\"text/javascript\">document.getElementById('mensagemRetorno').style.display = 'block';</script>");
						if (formularioDeObreiro.getStatus().equals(
								"Sucesso ao Cadastrar")) {
							out.println("Foram incluidos no banco de dados <br />");
							Obreiro obreiroCadastrado = (Obreiro) request
									.getAttribute("obreiro");
							out.println("Nome: "+obreiroCadastrado.getNome() + "<br />");
							out.println("Cargo: "+obreiroCadastrado.getCargo() + "<br />");
							out.println("CPF: "+obreiroCadastrado.getCpf() + "<br />");
							out.println("Congregação: "+obreiroCadastrado.getCongregacao());
						}
					}
				%>
			</div>
			<%
				List<Congregacao> listaDeCongregacao;
				listaDeCongregacao = (List<Congregacao>) request.getAttribute("listaDeCongregacao");
			%>
			
	
	
	<form name="formularioObreiro" method="post" action="registrarObreiro" id="formularioObreiro">
			<p>
				<label for="Nome">Nome: </label>
				<input type="text" name="Nome" value="
				<%if (formularioDeObreiro != null
					&& formularioDeObreiro.verificarCampoPreenchido("Nome")) {
				out.println(formularioDeObreiro.obterCampoPreenchido("Nome"));
			}%>
				" id="Nome" />
				<span class="erroCampoFormulario">
					<%
						if (validadorDeObreiro != null
								&& validadorDeObreiro.verificarCampoComErro("Nome")) {
							out.println(validadorDeObreiro.obterCampoComErro("Nome"));
						}
					%>
				</span>
			</p>
			<p>
				<label for="Cargo">Cargo: </label>
				<input type="text" name="Cargo" value="
				<%if (formularioDeObreiro != null
					&& formularioDeObreiro.verificarCampoPreenchido("Cargo")) {
				out.println(formularioDeObreiro.obterCampoPreenchido("Cargo"));
			}%>
				"id="Cargo" />
				<span class="erroCampoFormulario">
					<%
						if (validadorDeObreiro != null
								&& validadorDeObreiro.verificarCampoComErro("Cargo")) {
							out.println(validadorDeObreiro.obterCampoComErro("Cargo"));
						}
					%>
				</span>
			</p>
			<p>
				<label for="Cpf">CPF: </label>
				<input type="text" name="Cpf" maxlength="11" value="
				<%if (formularioDeObreiro != null
					&& formularioDeObreiro.verificarCampoPreenchido("Cpf")) {
				out.println(formularioDeObreiro.obterCampoPreenchido("Cpf"));
			}%>
				" id="Cpf" onfocus="mostrarTooltip('toolTip_cpf', 'Somente números.');" onblur="mostrarTooltip('toolTip_cpf', '');" />
				<span class="erroCampoFormulario">
				<span id="toolTip_cpf"></span>
				<%
					if (validadorDeObreiro != null
							&& validadorDeObreiro.verificarCampoComErro("Cpf")) {
						out.println(validadorDeObreiro.obterCampoComErro("Cpf"));
					}
				%>
				</span>
			</p>
			
			<!--
			<p>
				<label for="Senha">Senha: </label>
				<input type="text" name="Senha" id="Senha" />
				<span class="erroCampoFormulario">
					<% /*
						if (validadorDeObreiro != null
								&& validadorDeObreiro.verificarCampoComErro("Senha")) {
							out.println(validadorDeObreiro.obterCampoComErro("Senha"));
						}
						*/
					%>
				</span>
			</p>			

			<p>
				<label for="SenhaConfirmacao">Confirmação da Senha: </label>
				<input type="text" name="SenhaConfirmacao" id="SenhaConfirmacao" />
				<span class="erroCampoFormulario">
					<% /*
						if (validadorDeObreiro != null
								&& validadorDeObreiro.verificarCampoComErro("SenhaConfirmacao")) {
							out.println(validadorDeObreiro.obterCampoComErro("SenhaConfirmacao"));
						}
						*/
					%>
				</span>
			</p>
			-->			
			
			<p>
				<label for="Congregacao">Congregação</label>
				<select name="Congregacao">
					<option value=""></option>
					<%
						 if(formularioDeObreiro != null && formularioDeObreiro.verificarCampoPreenchido("Congregacao")){
							 String congregacao = formularioDeObreiro.obterCampoPreenchido("Congregacao");
							 for(int posicao=0; posicao<listaDeCongregacao.size(); posicao++){
								 if(congregacao.equals(listaDeCongregacao.get(posicao).getNome())){
									 out.println("<option selected=\"selected\" value=" + listaDeCongregacao.get(posicao).getNome() +">"+listaDeCongregacao.get(posicao).getNome()+"</option>");
								 }
								 else{
									 out.println("<option value=\""+listaDeCongregacao.get(posicao).getNome()+"\">"+listaDeCongregacao.get(posicao).getNome()+"</option>");
								 }
							}
						 }
						 else{
							for(int posicao = 0; posicao < listaDeCongregacao.size(); posicao++){
								out.println("<option value=\""+listaDeCongregacao.get(posicao).getNome()+"\">"+listaDeCongregacao.get(posicao).getNome()+"</option>");
							}
						 }
					%>
				</select>
				<span class="erroCampoFormulario">
				<%
					if (validadorDeObreiro != null
							&& validadorDeObreiro.verificarCampoComErro("Congregacao")) {
						out.println(validadorDeObreiro.obterCampoComErro("Congregacao"));
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