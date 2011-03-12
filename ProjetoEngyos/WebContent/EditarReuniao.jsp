<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Reuniao" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao" %>
<%
	FormularioDeReuniao formularioDeReuniao;
	ValidadorDeFormularioDeReuniao validadorDeReuniao;
	formularioDeReuniao = (FormularioDeReuniao) request.getAttribute("formularioDeReuniao");
	validadorDeReuniao = formularioDeReuniao.getValidadorDeFormularioDeReuniao();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>Reuniões - Projeto Engyos - Controle de Presença</title>
	<link href="screen.css" rel="stylesheet" type="text/css" />
	
	<script type='text/javascript' src="javascript/OptionTransfer.js"></script>
	<script type='text/javascript' src="javascript/replicarCampos.js"></script>
	<script type='text/javascript' src="javascript/selectbox.js"></script>

	<script type="text/javascript">
	// Função para esconder a Div após alguns segundos
	function esconderDiv() {
		var div = document.getElementById('mensagemRetorno');
		div.style.display = 'none';
	}
	// esconderá a div "mensagemRetorno" após 9 segundos.
	window.setTimeout(esconderDiv, 5000);

	function obterObreirosSelecionados(){
		var campoAutor = document.getElementById('obreiros2').value;
		if(document.getElementById('obreiros2').options.length != 0){
			//Obter os obreiros incluidos no processo
			var listaDeObreiros = document.getElementById("obreiros2").options[0].value;		
			for(var i=1;i<document.getElementById("obreiros2").options.length;i++) {
				var opcao = document.getElementById("obreiros2").options[i];
				listaDeObreiros = listaDeObreiros + "%" + opcao.value;
			}
			document.getElementById("obreiros").value = listaDeObreiros;
		}
	}
	
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
			<li><a href="#">Edição</a></li>
		</ul>
		<div id="conteudo_tabmenu">
		<div id="mensagemRetorno">
				<%
					if (formularioDeReuniao.getMensagemStatus() != null && !formularioDeReuniao.getMensagemStatus().equals("")) {
						out.println("<script type=\"text/javascript\">document.getElementById('mensagemRetorno').style.display = 'block';</script>");
						out.println(formularioDeReuniao.getMensagemStatus() + "<br />");
						if (formularioDeReuniao.verificarDadoDeConfirmacao("confirmacao_edicao")){
							out.println("<p>Foram alterados no banco de dados</p>");
							out.println("Local: " + formularioDeReuniao.obterDadoDeConfirmacao("confirmacao_local") + "<br />");
							out.println("Data: " + formularioDeReuniao.obterDadoDeConfirmacao("confirmacao_data") + "<br />");
							out.println("Hora: " + formularioDeReuniao.obterDadoDeConfirmacao("confirmacao_hora") + "<br />");
						}
					}
					
				%>
		</div>
	<form name="formularioReuniao" method="post" action="Controller" id="formularioReuniao">
            <input type="hidden" name="idReuniao" value="<% if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("idReuniao")){ out.print(formularioDeReuniao.obterCampoPreenchido("idReuniao"));}%>">
            <p>
				<label for="Local">Local:</label>
				<input type="text" name="local" id="local" value="
				<%if (formularioDeReuniao != null
					&& formularioDeReuniao.verificarCampoPreenchido("local")) {
				out.println(formularioDeReuniao.obterCampoPreenchido("local"));
				}%>
				"/>
				<span class="erroCampoFormulario">
					<%
						if (validadorDeReuniao != null
								&& validadorDeReuniao.verificarCampoComErro("local")) {
							out.println(validadorDeReuniao.obterCampoComErro("local"));
						}
					%>
				</span>
			</p>
			<p>
				<label for="Data">Data: </label>
				<select name="dataReuniaoDia">
					<option value="00"></option>
					<%
					String zero;
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("dataReuniaoDia")){
						String dia = formularioDeReuniao.obterCampoPreenchido("dataReuniaoDia");
						for(int posicao = 1; posicao <= 31; posicao++){
							if(posicao < 10){
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
						for(int posicao = 1; posicao <= 31; posicao++){
							if(posicao < 10){
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
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("dataReuniaoMes")){

						String mes = formularioDeReuniao.obterCampoPreenchido("dataReuniaoMes");
						for(int posicao = 1; posicao <= 12; posicao++){
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
						for(int posicao = 1; posicao <= 12; posicao++){
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
					Calendar calendar = Calendar.getInstance();
					int anoAtual = calendar.get(calendar.YEAR);
					
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("dataReuniaoAno")){
						String ano = formularioDeReuniao.obterCampoPreenchido("dataReuniaoAno");
						for(int posicao = 2010; posicao <= (anoAtual + 30); posicao++){
							if(ano.equals(Integer.toString(posicao))){
								out.println("<option selected=\"selected\" value=" + posicao +">"+posicao+"</option>");
							}
							else{
								out.println("<option value=\""+posicao+"\">"+posicao+"</option>");
							}
						}
					}
					else{
						for(int posicao = 2010; posicao <= (anoAtual + 30); posicao++){
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

				<label for="Data">Hora: </label>
				<select name="horaReuniao">
					<option value="24"></option>
					<%
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("horaReuniao")){
						String hora = formularioDeReuniao.obterCampoPreenchido("horaReuniao");
						for(int posicao = 0; posicao < 24; posicao++){
							if(posicao < 10){
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
						for(int posicao = 0; posicao < 24; posicao++){
							if(posicao < 10){
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
					if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("minutoReuniao")){
						String minuto = formularioDeReuniao.obterCampoPreenchido("minutoReuniao");
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
							&& validadorDeReuniao.verificarCampoComErro("Horario")) {
						out.println(validadorDeReuniao
								.obterCampoComErro("Horario"));
					}
				%>
				</span>
				
				
			</p>
			<p>
				<table border="0">
					<tbody>
						<tr>
							<td>
								OPÇÕES:<br/>
								<select name="obreiros1" id="obreiros1" class="field" style="font-size: 8pt;" multiple="multiple" size="6" ondblclick="moveSelectedOptions(this.form['obreiros1'],this.form['obreiros2'],true,this.form['movepattern1'].value)" onfocus="focusProcesso('focusAutor')">
									<%
									List<Obreiro> listaDeObreiros = formularioDeReuniao.getListaDeObreiros();
									List<String> listaDeIdDeObreirosInclusos = null;
									int posicaoAtualListaDeObreirosInclusos = 0;
									
									if(formularioDeReuniao.campoListaEstaPreenchido("obreiros")){
										listaDeIdDeObreirosInclusos = formularioDeReuniao.obterCampoListaPreenchido("obreiros");
										for(Obreiro obreiro : listaDeObreiros){
											posicaoAtualListaDeObreirosInclusos = 0;
											while(posicaoAtualListaDeObreirosInclusos < listaDeIdDeObreirosInclusos.size() && !listaDeIdDeObreirosInclusos.get(posicaoAtualListaDeObreirosInclusos).equals(obreiro.getCpf())){
												posicaoAtualListaDeObreirosInclusos++;
											}
											if(posicaoAtualListaDeObreirosInclusos >= listaDeIdDeObreirosInclusos.size()){
												out.println("<option value=\"" + obreiro.getCpf() + "\">" + obreiro.getNome() + "</option>");
											}
										}
									}
									else{
										for(Obreiro obreiro : listaDeObreiros){
											out.println("<option value=\"" + obreiro.getCpf() + "\">" + obreiro.getNome() + "</option>");
										}
									}
									//out.println("<option value=\"" + autor + "\">" + autor + "</option>");
									%>		
								</select>
							</td>
							<td align="center" valign="middle">
								<br/>
								<input name="right" value="&raquo;" onclick="moveSelectedOptions(this.form['obreiros1'],this.form['obreiros2'],true,this.form['movepattern1'].value)" type="button"/><br/><br/>
								<!--
								<input name="right" value="All &gt;&gt;" onclick="moveAllOptions(this.form['obreiros1'],this.form['obreiros2'],true,this.form['movepattern1'].value)" type="button"/><br/><br/>
								-->
								<input name="left" value="&laquo;" onclick="moveSelectedOptions(this.form['obreiros2'],this.form['obreiros1'],true,this.form['movepattern1'].value)" type="button"/><br/><br/>
								<!--
								<input name="left" value="All &lt;&lt;" onclick="moveAllOptions(this.form['obreiros2'],this.form['obreiros1'],true,this.form['movepattern1'].value)" type="button"/>
								-->
							</td>
							<td>
								INCLUÍDOS:<br/>
								<select name="obreiros2" id="obreiros2" class="field" style="font-size: 8pt;" multiple="multiple" size="6" ondblclick="moveSelectedOptions(this.form['obreiros2'],this.form['obreiros1'],true,this.form['movepattern1'].value)" onfocus="focusProcesso('focusAutor')">
									<%
									List<String> listaDeIdDeObreirosIncluidos = null;	
									
									if(formularioDeReuniao.campoListaEstaPreenchido("obreiros")){
										listaDeIdDeObreirosIncluidos = formularioDeReuniao.obterCampoListaPreenchido("obreiros");
										for(String cpfObreiro: listaDeIdDeObreirosIncluidos){
											for(Obreiro obreiro : listaDeObreiros){
												if(obreiro.getCpf().equals(cpfObreiro)){
													out.print("<option value=\"" + obreiro.getCpf() + "\">" + obreiro.getNome() + "</option>");
												}
											}
											
										}
									}
																			
									%>	
								</select>
								
								<!-- Input para capturar String de obreiros preparada para o servlet -->
								<input name="obreiros" id="obreiros" value="" type="hidden"/>
	
							
								<!-- Input para auxiliar o javascript "optionTransfer"-->
								<input name="movepattern1" value="" type="hidden"/>
							</td>											
						</tr>
					</tbody>
				</table>
			</p>
			<p>
				<button type="submit" name="acao" value="reuniao_editer" onclick="obterObreirosSelecionados();">Editar</button>
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