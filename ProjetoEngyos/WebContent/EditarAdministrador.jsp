<%-- 
    Document   : EditarAdministrador
    Author     : Rogerio
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Administrador" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeAdministrador" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeAdministrador" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
    FormularioDeAdministrador formularioDeAdministrador = null;
    ValidadorDeFormularioDeAdministrador validadorDeFormularioDeAdministrador = null;
    formularioDeAdministrador = (FormularioDeAdministrador) request.getAttribute("formularioDeAdministrador");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Obreiros - Projeto Engyos - Controle de Presença</title>
        <link href="css/screen.css" rel="stylesheet" type="text/css" />
        <link href="css/jqueryui/jquery-ui.css" type="text/css" rel="stylesheet"/>

        <script type='text/javascript' src="javascript/jquery-1.5.2.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery-ui.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery.ui.button.js"></script>
        <script type='text/javascript' src="javascript/jquery.form.js"></script>

        <style type="text/css">
            .submit_administrador {padding-top: 10px; font-size: 62.5%; }
        </style>

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

                    //Form JQuery
                    $(function() {

                        $( "input:submit", ".submit_administrador" ).button();
                        $( "input:submit", ".submit_administrador" ).click(function() {
                            $('#formularioAdministrador').submit();
                            //return true;
                        });

                    });

        </script>

        <script type="text/javascript">

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

            //Form JQuery
            $(function() {

                $( "input:submit", ".submit_administrador" ).button();
                $( "input:submit", ".submit_administrador" ).click(function() {
                    $('#formularioAdministrador').submit();
                    //return true;
                });

            }

        </script>

    </head>
    <body  id="administradores">
        <div id="all">
                <div id="topo">
                        <%@ include file = "Topo.jsp" %>
                </div>
                <div id="menu">
                        <%@ include file = "menu_administrativo.html" %>
                </div>
                <div id="conteudo">
                        <ul id="tabmenu">
                                <li><a href="#">Edição</a></li>
                        </ul>
                        <div id="conteudo_tabmenu">
                                <div id="mensagemRetorno">
                                        <%
                                            if(formularioDeAdministrador.getMensagemStatus() != null && !formularioDeAdministrador.getMensagemStatus().equals("")){
                                                out.println(formularioDeAdministrador.getMensagemStatus() + "<br />");
                                                out.println("<script type=\"text/javascript\">document.getElementById('mensagemRetorno').style.display = 'block';</script>");
                                                if(formularioDeAdministrador.verificarDadoDeConfirmacao("confirmacao_edicao")){
                                                    out.println("Foram alterados no banco de dados <br />");
                                                    out.println("Nome: " + formularioDeAdministrador.obterDadoDeConfirmacao("confirmacao_nome") + "<br />");
                                                    out.println("CPF: "+ formularioDeAdministrador.obterDadoDeConfirmacao("confirmacao_cpf") + "<br />");
                                                    out.println("Condição Senha: " + formularioDeAdministrador.obterDadoDeConfirmacao("confirmacao_senha") + "<br />");
                                                }
                                            }

                                        %>
                                </div>


                        <form name="formularioObreiro" method="post" action="Controller?acao=administrador_editer" id="formularioAdministrador">
                                <p>
                                        <label for="Nome">Nome: </label>
                                        <input type="text" name="Nome" value="<%

                                            if (formularioDeAdministrador != null
                                                    && formularioDeAdministrador.verificarCampoPreenchido("Nome")) {
                                                out.println(formularioDeAdministrador.obterCampoPreenchido("Nome"));
                                            }
                                        %>" id="Nome" />
                                        <span class="erroCampoFormulario">
                                                <%

                                                if (validadorDeFormularioDeAdministrador != null
                                                                && validadorDeFormularioDeAdministrador.verificarCampoComErro("Nome")) {
                                                        out.println(validadorDeFormularioDeAdministrador.obterCampoComErro("Nome"));
                                                }

                                                %>
                                        </span>
                                </p>
                                <p>
                                        <label for="Cpf">CPF: </label>
                                        <input type="text" name="Cpf" maxlength="11" value="<%

                                            if (formularioDeAdministrador != null
                                                    && formularioDeAdministrador.verificarCampoPreenchido("Cpf")) {
                                                out.println(formularioDeAdministrador.obterCampoPreenchido("Cpf"));
                                            }

                                        %>" id="Cpf" onfocus="mostrarTooltip('toolTip_cpf', 'Somente números.');" onblur="mostrarTooltip('toolTip_cpf', '');" />
                                        <span class="erroCampoFormulario">
                                        <span id="toolTip_cpf"></span>
                                        <%

                                        if (validadorDeFormularioDeAdministrador != null
                                                        && validadorDeFormularioDeAdministrador.verificarCampoComErro("Cpf")) {
                                                out.println(validadorDeFormularioDeAdministrador.obterCampoComErro("Cpf"));
                                        }

                                        %>
                                        </span>
                                </p>
                                <p>
                                        <label for="Senha">Senha: </label>
                                        <input type="password" name="Senha" id="Senha" />
                                        <span class="erroCampoFormulario">
                                                <%

                                                    if (validadorDeFormularioDeAdministrador != null
                                                                    && validadorDeFormularioDeAdministrador.verificarCampoComErro("Senha")) {
                                                            out.println(validadorDeFormularioDeAdministrador.obterCampoComErro("Senha"));
                                                    }

                                                %>
                                        </span>
                                        <span class="erroCampoFormulario">
                                                <%

                                                    if (validadorDeFormularioDeAdministrador != null
                                                                    && validadorDeFormularioDeAdministrador.verificarCampoComErro("SenhaMatch")) {
                                                            out.println(validadorDeFormularioDeAdministrador.obterCampoComErro("SenhaMatch"));
                                                    }

                                                %>
                                        </span>
                                </p>

                                <p>
                                        <label for="SenhaConfirmacao">Confirmação da Senha: </label>
                                        <input type="password" name="SenhaConfirmacao" id="SenhaConfirmacao" />
                                        <span class="erroCampoFormulario">
                                                <%

                                                if (validadorDeFormularioDeAdministrador != null
                                                                && validadorDeFormularioDeAdministrador.verificarCampoComErro("SenhaConfirmacao")) {
                                                        out.println(validadorDeFormularioDeAdministrador.obterCampoComErro("SenhaConfirmacao"));
                                                }

                                                %>
                                        </span>
                                </p>

                               <%-- <p>
                                <button type="submit" name="botao_action" value="Cadastrar">Cadastrar</button>
                                </p>--%>

                                <div class="submit_administrador">
                                    <input type="submit" id="submit_administrador" name="botao_action" value="Editar Administrador"/>
                                </div>

                        </form>

                        </div>
                </div>
                <div id="rodape">
                        <%@ include file = "Rodape.html" %>
                </div>
        </div>
    </body>
</html>
