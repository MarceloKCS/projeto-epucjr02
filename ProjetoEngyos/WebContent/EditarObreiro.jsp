<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro" %>
<%
	FormularioDeObreiro formularioDeObreiro;
	ValidadorDeFormularioDeObreiro validadorDeObreiro;
	formularioDeObreiro = (FormularioDeObreiro) request.getAttribute("viewObreiro");
	validadorDeObreiro = (ValidadorDeFormularioDeObreiro) request.getAttribute("errorObreiro");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="javax.swing.JOptionPane"%><html>
<head>
	<title>Formulário - Obreiro</title>
	<meta http-equiv="Content-Type"  content="text/html; charset=utf-8" /> 
</head>
<body>
<% 
	if(formularioDeObreiro != null){
		out.println(formularioDeObreiro.getStatus()+"<br />");
		if(formularioDeObreiro.getStatus().equals("Sucesso ao Cadastrar")){
			out.println("Foram incluidos no banco de dados <br />");
			Obreiro obreiroCadastrado = (Obreiro) request.getAttribute("obreiro");
			out.println(obreiroCadastrado.getNome()+"<br />");
			out.println(obreiroCadastrado.getCargo()+"<br />");
			out.println(obreiroCadastrado.getCpf()+"<br />");
			out.println(obreiroCadastrado.getCongregacao());
		}
	}
%>
	<fieldset>
	<legend>Formulário - Obreiro</legend>
	<%
	List<Congregacao> listaDeCongregacao;
	listaDeCongregacao = (List<Congregacao>) request.getAttribute("listaCongregacao");
	%>
	<form name=formularioObreiro method=post action=editarObreiro id=formularioObreiro>
			<p>
				<label for="Nome">Nome: </label>
				<input type="text" name="Nome" value="
				<%
					if(formularioDeObreiro != null && formularioDeObreiro.verificarCampoPreenchido("Nome")){
						out.println(formularioDeObreiro.obterCampoPreenchido("Nome"));
					}
				%>
				" id="Nome" />
				<%
					if(validadorDeObreiro != null && validadorDeObreiro.verificarCampoComErro("Nome")){
						out.println(validadorDeObreiro.obterCampoComErro("Nome"));
					}
				%>
			</p>
			<p>
				<label for="Cargo">Cargo: </label>
				<input type="text" name="Cargo" value="
				<%
					if(formularioDeObreiro != null && formularioDeObreiro.verificarCampoPreenchido("Cargo")){
						out.println(formularioDeObreiro.obterCampoPreenchido("Cargo"));
					}
				%>
				"id="Cargo" />
				<%
					if(validadorDeObreiro != null && validadorDeObreiro.verificarCampoComErro("Cargo")){
						out.println(validadorDeObreiro.obterCampoComErro("Cargo"));
					}
				%>
			</p>
			<p>
				<label for="Cpf">CPF: </label>
				<input type="text" name="Cpf" value="
				<%
					if(formularioDeObreiro != null && formularioDeObreiro.verificarCampoPreenchido("Cpf")){
						out.println(formularioDeObreiro.obterCampoPreenchido("Cpf"));
					}
				%>
				" id="Cpf" />
				<%
					if(validadorDeObreiro != null && validadorDeObreiro.verificarCampoComErro("Cpf")){
						out.println(validadorDeObreiro.obterCampoComErro("Cpf"));
					}
				%>
			</p>
			<p>
				<label for="Congregacao">Congregação</label>
				<select name="Congregacao">
					<option value=""></option>
					<%
					if(formularioDeObreiro != null && formularioDeObreiro.verificarCampoPreenchido("Congregacao")){
						String congregacao = formularioDeObreiro.obterCampoPreenchido("Congregacao");
						for(int posicao=1; posicao<=listaDeCongregacao.size(); posicao++){
							if(congregacao.equals(listaDeCongregacao.get(posicao-1).getNome())){
								out.println("<option selected=\"selected\" value=\"" + listaDeCongregacao.get(posicao-1).getNome() +"\">"+listaDeCongregacao.get(posicao-1).getNome()+"</option>");
							}
							else{
								out.println("<option value=\""+listaDeCongregacao.get(posicao-1).getNome()+"\">"+listaDeCongregacao.get(posicao-1).getNome()+"</option>");
							}
						}
					}
					else{
						for(int posicao = 1; posicao <= listaDeCongregacao.size(); posicao++){
							out.println("<option value=\""+listaDeCongregacao.get(posicao-1).getNome()+"\">"+listaDeCongregacao.get(posicao-1).getNome()+"</option>");
						}
					}
					%>
				</select>
			</p>
			<p>
			<button type=submit name=botao_action value=Editar>Editar</button>
			</p>
		</form>
	</fieldset>
</body>
</html>
