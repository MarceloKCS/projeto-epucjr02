<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeCongregacao" %>

<%
	FormularioDeCongregacao formularioDeCongregacao;
	ValidadorDeFormularioDeCongregacao validadorDeCongregacao;
	formularioDeCongregacao = (FormularioDeCongregacao) request.getAttribute("formularioDeCongregacao");
	validadorDeCongregacao = formularioDeCongregacao.getValidadorDeFormularioDeCongregacao();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>Congregações - Projeto Engyos - Controle de Presença</title>
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
<body id="congregacoes"> 

<div id="all">
	<div id="topo">
		<%@ include file = "Topo.html" %>
	</div>
	<div id="menu">
		<%@ include file = "Menu.html" %>
	</div>
	<div id="conteudo">
		<ul id="tabmenu">
			<li><a href="#">Edição</a></li>
		</ul>
		<div id="conteudo_tabmenu">
			
			<div id="mensagemRetorno">
			<%
				if (formularioDeCongregacao.getMensagemStatus() != null && !formularioDeCongregacao.getMensagemStatus().equals("")) {
					out.println("<script type=\"text/javascript\">document.getElementById('mensagemRetorno').style.display = 'block';</script>");
					out.println(formularioDeCongregacao.getMensagemStatus() + "<br />");
					if (formularioDeCongregacao.verificarDadoDeConfirmacao("confirmacao_edicao")) {
						out.println("<p>Foram alterados no banco de dados</p>");
						out.println("Nome: " + formularioDeCongregacao.obterDadoDeConfirmacao("confirmacao_nomeDaCongregacao") + "<br/>");
						out.println("Endereço: " + formularioDeCongregacao.obterDadoDeConfirmacao("confirmacao_endereco") + "<br/>");
					}
				}
			%>
			</div>
			
			<form name="formularioCongregacao" method="post" action="Controller" id="formularioCongregacao">
			<p>
                            <input type="hidden" name="idCongregacao" value="<%if (formularioDeCongregacao != null && formularioDeCongregacao.verificarCampoPreenchido("idCongregacao")){ out.print(formularioDeCongregacao.obterCampoPreenchido("idCongregacao")); }%>">
				<label for="Nome">Nome: </label>
				<input type="text" name="Nome" value="
				<%if (formularioDeCongregacao != null
					&& formularioDeCongregacao.verificarCampoPreenchido("Nome")) {
				out.println(formularioDeCongregacao
						.obterCampoPreenchido("Nome"));
				}
				%>
				" id="Nome"/>
				<span class="erroCampoFormulario">
				<%
					if (validadorDeCongregacao != null
							&& validadorDeCongregacao.verificarCampoComErro("Nome")) {
						out.println(validadorDeCongregacao.obterCampoComErro("Nome"));
					}
				%>
				</span>
			</p>
			<p>
				<label for="Endereco">Endereço: </label>
				<input type="text" name="Endereco" value="
				<%if (formularioDeCongregacao != null
					&& formularioDeCongregacao
							.verificarCampoPreenchido("Endereco")) {
				out.println(formularioDeCongregacao
						.obterCampoPreenchido("Endereco"));
			}%>
				" id="Endereco"/>
				<span class="erroCampoFormulario">				
				<%
									if (validadorDeCongregacao != null
											&& validadorDeCongregacao.verificarCampoComErro("Endereco")) {
										out.println(validadorDeCongregacao
												.obterCampoComErro("Endereco"));
									}
								%>
				</span>
			</p>
			<p>
			<button type="submit" name="acao" value="congregacao_editer">Editar</button>
			</p>
			</form>
		</div>
	</div>
	<div id="rodape">
		<%@ include file = "Rodape.html" %>
	</div>
</div>

<script type="text/javascript">

</script>

</body>
</html>