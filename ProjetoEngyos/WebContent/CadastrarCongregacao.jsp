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
            <link href="css/screen.css" rel="stylesheet" type="text/css" />
            <link href="css/jqueryui/jquery-ui.css" type="text/css" rel="stylesheet"/>

            <script type='text/javascript' src="javascript/jquery-1.5.2.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery-ui.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery.ui.button.js"></script>

            <style type="text/css">
                div#conteudo_tabmenu form p input#congregacao_padrao {
			width: 20px;
		}

                div#conteudo_tabmenu form p label#congregacao_padrao_label {
                    width: 200px;
		}

                .submit_congregacao {padding-top: 10px; font-size: 62.5%; }

                #congregacao_padrao_p{ margin-top: 10px; margin-bottom: 10px;}

            </style>
            <script type="text/javascript">
            // Função para esconder a Div após alguns segundos
            function esconderDiv() {
                    var div = document.getElementById('mensagemRetorno');
                    div.style.display = 'none';
            }
            // esconderá a div "mensagemRetorno" após 9 segundos.
            window.setTimeout(esconderDiv, 5000);


             $(function() {

                $( "input:submit", ".submit_congregacao" ).button();
                $( "input:submit", ".submit_congregacao" ).click(function() {
                    $('#formularioCongregacao').submit();
                    //return true;
                });

             });

            </script>


    </head>
    <body id="congregacoes">

    <div id="all">
            <div id="topo">
                    <%@ include file = "Topo.jsp" %>
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
                                    if (formularioDeCongregacao.getMensagemStatus() != null && !formularioDeCongregacao.getMensagemStatus().equals("")) {
                                            out.println("<script type=\"text/javascript\">document.getElementById('mensagemRetorno').style.display = 'block';</script>");
                                            out.println(formularioDeCongregacao.getMensagemStatus() + "<br />");
                                            if (formularioDeCongregacao.verificarDadoDeConfirmacao("confirmacao_cadastro")) {
                                                    out.println("<p>Foram incluidos no banco de dados</p>");
                                                    out.println("Nome: " + formularioDeCongregacao.obterDadoDeConfirmacao("confirmacao_nomeDaCongregacao") + "<br />");
                                                    out.println("Endereço: " + formularioDeCongregacao.obterDadoDeConfirmacao("confirmacao_endereco") + "<br />");
                                            }
                                    }
                            %>
                            </div>

                            <form name="formularioCongregacao" method="post" action="Controller?acao=congregacao_register" id="formularioCongregacao">
                            <p>
                                    <label for="Nome">Nome: </label>
                                    <input type="text" name="Nome" value="<%
                                        if (formularioDeCongregacao != null && formularioDeCongregacao.verificarCampoPreenchido("Nome")) {
                                            out.println(formularioDeCongregacao.obterCampoPreenchido("Nome"));
                                        }
                                    %>" id="Nome"/>
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
                                    <input type="text" name="Endereco" value="<%
                                        if (formularioDeCongregacao != null
                                                && formularioDeCongregacao
                                                                .verificarCampoPreenchido("Endereco")) {
                                            out.println(formularioDeCongregacao.obterCampoPreenchido("Endereco"));
                                    }%>" id="Endereco"/>
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

                            <div id="congregacao_padrao_p">
                                <input type="checkbox" <% if(formularioDeCongregacao.isCongregacaoPadraoDefinida() && !formularioDeCongregacao.isFormularioPertenceCongregacaoPadrao()){ out.print("disabled=\"disabled\""); };%> id="congregacao_padrao" name="congregacao_padrao" <%
                                if(formularioDeCongregacao != null && formularioDeCongregacao.verificarCampoPreenchido("congregacao_padrao")){out.print("checked=\"checked\"");}
                                    %> value="selecionado" />
                                <label id="congregacao_padrao_label" for="congregacao_padrao">Congregação sede</label>
                                
                            </div>
                            
                            <div class="submit_congregacao">
                                <input type="submit" id="submit_congregacao" name="botao_action" value="Cadastrar Congregacao"/>
                            </div>

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