<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro" %>
<%
	FormularioDeObreiro formularioDeObreiro;
	ValidadorDeFormularioDeObreiro validadorDeObreiro;
	formularioDeObreiro = (FormularioDeObreiro) request.getAttribute("formularioDeObreiro");
	//validadorDeObreiro = (ValidadorDeFormularioDeObreiro) request.getAttribute("errorObreiro");
	validadorDeObreiro = formularioDeObreiro.getValidadorDeFormularioDeObreiro();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
	
	<script language="JavaScript">

        function AbreAppletPopUp()
        {
            w=300; //largura da janela(popup)
            h=300; //altura da janela(popup)

            winl = (screen.width - w) / 2; //DEIXARÁ A JANELA(POPUP) NO CENTRO DA TELA
            wint = (screen.height - h) / 2;

            winprops = 'height='+h+',width='+w+',top='+wint+',left='+winl+',scrollbars=yes,toolbar=0,status=0, resizable=yes'; //configurações da popup

            win = window.open("applet_page.jsp","popApplet",winprops); // abre a popup
            win.focus(); //Focaliza a popup
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
			<li><a href="#">Edição</a></li>
		</ul>
		<div id="conteudo_tabmenu">
			<div id="mensagemRetorno">
				<%
					if (formularioDeObreiro.getMensagemStatus() != null && !formularioDeObreiro.getMensagemStatus().equals("")){
						out.println(formularioDeObreiro.getMensagemStatus() + "<br />");
						out.println("<script type=\"text/javascript\">document.getElementById('mensagemRetorno').style.display = 'block';</script>");
						if(formularioDeObreiro.verificarDadoDeConfirmacao("confirmacao_edicao")){
							out.println("Foram incluidos no banco de dados <br />");							
							out.println("Nome: " + formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_nome") + "<br />");
							out.println("Cargo: " + formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_cargo") + "<br />");
							out.println("CPF: "+ formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_cpf") + "<br />");
							out.println("Congregação: " + formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_congregacao"));
						}
					}
				%>
			</div>
	
	
		<form name="formularioObreiro" method="post" action="Controller?acao=obreiro_edit" id="formularioObreiro">
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
			
			
			<p>
				<label for="Senha">Senha: </label>
				<input type="text" name="Senha" id="Senha" />
				<span class="erroCampoFormulario">
					<% 
						if (validadorDeObreiro != null
								&& validadorDeObreiro.verificarCampoComErro("Senha")) {
							out.println(validadorDeObreiro.obterCampoComErro("Senha"));
						}
						
					%>
				</span>
				<span class="erroCampoFormulario">
					<% 
						if (validadorDeObreiro != null
								&& validadorDeObreiro.verificarCampoComErro("SenhaMatch")) {
							out.println(validadorDeObreiro.obterCampoComErro("SenhaMatch"));
						}
						
					%>
				</span>
			</p>			

			<p>
				<label for="SenhaConfirmacao">Confirmação da Senha: </label>
				<input type="text" name="SenhaConfirmacao" id="SenhaConfirmacao" />
				<span class="erroCampoFormulario">
					<%
						if (validadorDeObreiro != null
								&& validadorDeObreiro.verificarCampoComErro("SenhaConfirmacao")) {
							out.println(validadorDeObreiro.obterCampoComErro("SenhaConfirmacao"));
						}
						
					%>
				</span>
			</p>
			<p>
				<label for="Digital">Digital: </label>
				<input type="button" name="digital" onClick="AbreAppletPopUp()" id="digital" value="InserirDigital"/>
			</p>		
			
			<p>
				<label for="Congregacao">Congregação</label>
				<select name="Congregacao">
					<option value=""></option>
					
					<%
					
					List<Congregacao> listaDeCongregacao;
					listaDeCongregacao = formularioDeObreiro.getListaDeCongregacoes();
					
					 if(formularioDeObreiro != null && formularioDeObreiro.verificarCampoPreenchido("Congregacao")){
						 String congregacao = formularioDeObreiro.obterCampoPreenchido("Congregacao").trim();
						
						 for(int posicao=0; posicao<listaDeCongregacao.size(); posicao++){								 
							 String congregacaoCorrente = listaDeCongregacao.get(posicao).getNome().trim();
							 if(congregacaoCorrente.equals(congregacao)){
					
					%>
								<option selected="selected" value="<%out.print(listaDeCongregacao.get(posicao).getIdCongregacao()); %>"> <% out.print(listaDeCongregacao.get(posicao).getNome()); %> </option>
						
							<%}else{ %>
								<option value="<%out.print(listaDeCongregacao.get(posicao).getIdCongregacao()); %>"> <% out.print(listaDeCongregacao.get(posicao).getNome()); %> </option>
							
							<%
							}
						 }
					 }
					 else{
						for(int posicao = 0; posicao < listaDeCongregacao.size(); posicao++){
					%>
							<option value="<%out.print(listaDeCongregacao.get(posicao).getIdCongregacao()); %>"> <% out.print(listaDeCongregacao.get(posicao).getNome()); %> </option>
					<%
					
						}
					 }
					
					%>
					<!-- Hidden Input dos campos para retrieve -->
					<%
					for(Congregacao congregacao : listaDeCongregacao){
						out.println("<input type=\"hidden\" name=\"" + congregacao.getIdCongregacao() + "\" value=\"" + congregacao.getNome() + "\"/>");
					}
					
					%>
					
					
				</select>
				<!-- Exibe erros relacionado ao preenchimento da congregação -->
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
			<button type="submit" name="botao_action" value="Editar">Editar</button>
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