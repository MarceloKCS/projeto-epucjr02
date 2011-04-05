<%@ page import="com.epucjr.engyos.aplicacao.controle.UserSessionControl" %>

<div id="logo" class="left"><a href="Controller?acao=page_loader&page_corrente=pagina_principal"
	title="Voltar para tela principal"><img src="imagens/logo.png"
	alt="Projeto Engyos" /></a></div>
<div id="btn_topo" class="right">
<p class="usuarioLogado"><img src="imagens/icon_user.png"
                              alt="Usuario Logado" /><%out.print(request.getSession().getAttribute(UserSessionControl.NOME_USUARIO));%></p>
<p><a href="#">Alterar dados</a></p>
<p><a href="Controller?acao=action_logout">Sair</a></p>
</div>