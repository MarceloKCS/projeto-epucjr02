<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Buscar - Projeto Engyos - Controle de Presença</title>
	<link href="screen.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		// função para pegar o Elemento por Id.
		function getEl(idElemento) {
			return document.getElementById(idElemento);			
		}

		// adiciona display: block ao elemento
		function mostrarElemento(idElemento) {
			getEl(idElemento).style.display = 'block';
		}

		// adiciona display: block ao elemento
		function esconderElemento(idElemento) {
			getEl(idElemento).style.display = 'none';
		}

		// tab ativa
		function tabOn(idElemento) {
			getEl(idElemento).className = 'tab_on';
		}

		// tab inativa
		function tabOff(idElemento) {
			getEl(idElemento).className = 'tab_off';
		}
		
		function mostrarObreiros () {
			mostrarElemento('buscarObreiros');
			esconderElemento('buscarCongregacoes');
			esconderElemento('buscarReunioes');

			tabOn('liObreiros');
			tabOff('liCongregacoes');
			tabOff('liReunioes');
		}

		function mostrarCongregacoes () {
			mostrarElemento('buscarCongregacoes');
			esconderElemento('buscarObreiros');
			esconderElemento('buscarReunioes');

			tabOn('liCongregacoes');
			tabOff('liObreiros');
			tabOff('liReunioes');			
			
		}

		function mostrarReunioes () {
			mostrarElemento('buscarReunioes');
			esconderElemento('buscarCongregacoes');
			esconderElemento('buscarObreiros');

			tabOn('liReunioes');
			tabOff('liCongregacoes');
			tabOff('liObreiros');			
		}
	</script>
	
</head>
<body id="buscar"> 
<div id="all">
	<div id="topo">
		<div id="logo" class="left">
			<a href="Tela1.html" title="Voltar para tela principal"><img src="imagens/logo.png" alt="Projeto Engyos" /></a>
		</div>
		<div id="btn_topo" class="right">
			<p class="usuarioLogado"><img src="imagens/icon_user.png" alt="UsuÃ¡rio Logado" />Calebe Santos</p>
			<p><a href="#">Alterar dados</a></p>
			<p><a href="#">Sair</a></p>
		</div>
	</div>
	<div id="menu">
		<ul id="menu_principal">
			<%@ include file = "Menu.html" %>
		</ul>
	</div>
	<div id="conteudo">
		<ul id="tabmenu">
			<li id="liObreiros" class="tab_on"><a href="#" onclick="mostrarObreiros();">Obreiro</a></li>
			<li id="liCongregacoes" class="tab_off"><a href="#" onclick="mostrarCongregacoes();">Congregação</a></li>
			<li id="liReunioes" class="tab_off"><a href="#" onclick="mostrarReunioes();">Reunião</a></li>
		</ul>
		<div id="conteudo_tabmenu">
			<div id="buscarObreiros">Buscar Obreiros</div>
			<div id="buscarCongregacoes">
				<form name="buscarCongregacao" method="post" action="buscarCongregacao"	id="buscarCongregacao">
					<label for="Nome">Nome: </label><input type="text" name="Nome" id="Nome" />
					<button type="submit" name="botao_action" value="Buscar">Buscar Cogregação</button>
				</form>
			</div>
			<div id="buscarReunioes">Buscar Reuniões</div>
		</div>
		
		<script type="text/javascript">mostrarObreiros();</script>
		
	</div>
	<div id="rodape">
		<%@ include file = "Rodape.html" %>
	</div>
</div>
</body>
</html>