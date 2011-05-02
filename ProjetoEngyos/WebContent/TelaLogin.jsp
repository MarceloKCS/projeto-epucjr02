<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.PaginaDeLogin"%>
<%@ page import="com.epucjr.engyos.dominio.administrativo.ValidadorLogin" %>

<%
    PaginaDeLogin paginaDeLogin = (PaginaDeLogin) request.getAttribute("paginaDeLogin");
    ValidadorLogin validadorLogin = null;
    if(paginaDeLogin != null){
        paginaDeLogin.getValidadorLogin();
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Projeto Engyos - Controle de Presença</title>
        <link href="css/screen.css" rel="stylesheet" type="text/css" />
    </head>
    <body id="telaLogin">
        <div id="all">
            <div id="conteudo_tabmenu" class="formularioLogin"><!-- div chama "conteudo_tabmenu" para utuilizar o mesmo CSS para o form de login -->
                <img src="imagens/logo.png" alt="Projeto Engyos - Controle de Presen&ccedil;a" class="logo" />
                <h2>Acesso ao Painel - Controle de Presen&ccedil;a</h2>
                <form name="formularioLogin" method="post" action="Controller" id="formularioLogin">
                    <p>
                        <%
                            if(validadorLogin != null && !validadorLogin.isUsuarioValido()){
                                out.print(paginaDeLogin.getMensagemStatus());
                            }                           
                        %>
                    </p>
                    <p>
                        <label for="login">Login: </label>
                        <input type="text" name="login" value="<% out.print(paginaDeLogin != null && paginaDeLogin.verificarCampoPreenchido("login") ? paginaDeLogin.obterCampoPreenchido("login"): "");%>"/>
                    </p>
                    <p>
                        <label for="senha">Senha: </label>
                        <input type="password" name="senha"/>
                    </p>
                    <p>
                        <button type="submit" name="acao" value="action_login">Acessar</button>
                    </p>
                </form>
                <div style="clear: both;"><!-- correção de bug css --></div>
            </div>
        </div>
    </body>
</html>