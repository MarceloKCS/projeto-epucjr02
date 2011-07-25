<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaCongregacao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	FormularioDeBuscaDaCongregacao resultadoDeBuscaDaCongregacao = (FormularioDeBuscaDaCongregacao) request.getAttribute("resultadoDeBuscaDaCongregacao");
%>


<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
            <title>Buscar - Projeto Engyos - Controle de Presença</title>
            <link href="css/screen.css" rel="stylesheet" type="text/css" />
            <link href="css/jqueryui/jquery-ui.css" type="text/css" rel="stylesheet"/>

            <script type='text/javascript' src="javascript/jquery-1.5.2.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery-ui.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery.ui.button.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery.ui.draggable.js"></script>
            <script src="javascript/jquery.alerts.js" type="text/javascript"></script>
            <link href="css/jqueryalerts/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />
            <script type='text/javascript' src="javascript/jquery.form.js"></script>


             <style type="text/css">
                .buscar_congregacao_submit {padding-top: 10px; font-size: 62.5%; }
            </style>

            <script type="text/javascript">
                 $(function() {

                    $("#congregacaoDelete").click( function() {
                        jConfirm('Confirmar operação?', 'Confirmation Dialog', function(r) {
                            jAlert('Confirmed: ' + r, 'Confirmation Results');
                        });
                    });

                    $( "input:button", ".buscar_congregacao_submit" ).button();
                    $( "input:button", ".buscar_congregacao_submit" ).click(function() {
                        var parametroBusca = $("#busca_input");
                        //jAlert('parametro de busca: ' + parametroBusca.val(), 'Alert Dialog');
                        realizaBuscaDeCongregacoes(parametroBusca.val(), 1);
                        return false;
                    });
                });

                function removeCongregacao(parametroBusca, idCongregacao, paginaCorrente, qtdResultados){
                    var qtdResultadosPorPagina = 10;
                    var paginas = Math.ceil((qtdResultados - 1) / qtdResultadosPorPagina);

                    if(paginaCorrente > 1 && paginaCorrente > paginas){
                        paginaCorrente = paginaCorrente - 1;
                    }
                    jConfirm('Deseja realmente apagar? \nEsta operação não é reversivel após confirmação.', 'Confirmation Dialog', function(okResponse) {
                        if(okResponse){
                            $.get('frontcontrollerajax?acao=ajax_congregacao_remover&idCongregacao=' + idCongregacao, function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                               // $('#status').text(responseText);
                               jAlert(responseText , 'Confirmation Results');
                               realizaBuscaDeCongregacoes(parametroBusca, paginaCorrente);
                            });

                            //jAlert('Removido: ' + parametroBusca + "\ncpf: " + cpf + "\npaginaCorrente: " + paginaCorrente + "\nResultados: " + qtdResultados + "\nPaginas: " + paginas , 'Confirmation Results');
                        }
                        else{
                            jAlert('Operação Cancelada', 'Alert Dialog');
                        }
                    });
                }

                 function realizaBuscaDeCongregacoes(parametroBusca, paginaSelecionada){
                     $.getJSON("frontcontrollerajax?acao=ajax_get_congregacaosearch&parametroBusca=" + parametroBusca + "&paginaCorrente=" + paginaSelecionada, {ajax: 'true'}, function(resultadoBuscaArray){

                        //Dados de paginacao
                        var numPagina = 1;
                        var dadosDePaginacao = "<p><b>Busca por: </b>" + resultadoBuscaArray[0].parametroDeBusca + "</p> ";
                        var pipePAgina = "";
                        var parametroBusca = resultadoBuscaArray[0].parametroDeBusca;
                        if(parametroBusca == null || parametroBusca == "" ){
                            parametroBusca = "";
                        }
                        var paginaCorrenete = resultadoBuscaArray[0].paginaCorrente;
                        var qtdPResultados = resultadoBuscaArray[0].quantidadeTotalDeResultados

                        for(numPagina = 1; numPagina <= resultadoBuscaArray[0].quantidadeTotalDePaginas; numPagina++){
                            if(numPagina == resultadoBuscaArray[0].paginaCorrente){

                                dadosDePaginacao += pipePAgina +"<a class=\"paginaAtual\">" +numPagina+ "</a>";

                            }
                            else{
                                dadosDePaginacao += pipePAgina +"<a class=\"pagina\" href=\"#\" onclick=\"realizaBuscaDeCongregacoes(\u0027" + parametroBusca + "\u0027, " + numPagina + ");\">" +numPagina+ "</a>";
                            }

                            pipePAgina = "<a> | </a>";
                        }

                        //Tabela de resultados
                        var countVisual = 0;
                        var tabela = '<tr> <th>Nome</th>  <th>Endereço</th> <th>Editar</th> <th>|Excluir</th> </tr>';
                        for (var i = 1; i < resultadoBuscaArray.length; i++) {
                            tabela += "<tr class=\"zebra" + countVisual % 2 + "\">";
                            tabela += " <td>" + resultadoBuscaArray[i].nome + "</td>";
                            tabela += " <td>|" + resultadoBuscaArray[i].endereco + "</td>";
                            tabela += " <td> <a href=\"Controller?acao=congregacao_editformload&idCongregacao=" + resultadoBuscaArray[i].id + "\">" + "<img src=\"imagens/edit.png\" width=\"18\" height=\"18\" title=\"Alterar Dados\"/></a>" +  "</td>";
                            tabela += " <td> <a href=\"#\" id=\"congregacaoDelete\" onclick=\"removeCongregacao(\u0027" + parametroBusca + "\u0027, \u0027" + resultadoBuscaArray[i].id + "\u0027, " + paginaCorrenete + ", " + qtdPResultados  + ");\" >" + "<img src=\"imagens/error.png\" width=\"18\" height=\"18\" title=\"Apagar Dados\"/></a>" +  "</td>";
                            tabela += "</tr>";

                            countVisual++;
                        }

                        $("table#resultadoBusca").html(tabela);
                        $("#paginacao").html(dadosDePaginacao);
                     });
                }
            </script>
    </head>
    <body id="buscarCongregacao">
        <div id="all">
                <div id="topo">
                        <%@ include file = "Topo.jsp" %>
                </div>
                <div id="menu">
                        <%@ include file = "Menu.html" %>
                </div>
                <div id="conteudo">
                        <ul id="tabmenu">
                                <li id="liObreiros" class="tab_off"><a href="Controller?acao=busca_loader&pagina_busca=buscar_obreiro">Obreiro</a></li>
                                <li id="liCongregacoes" class="tab_on"><a href="Controller?acao=busca_loader&pagina_busca=buscar_congregacao">Congregação</a></li>
                                <li id="liReunioes" class="tab_off"><a href="Controller?acao=busca_loader&pagina_busca=buscar_reuniao">Reunião</a></li>
                        </ul>
                        <div id="conteudo_tabmenu">
                            <div id="buscarCongregacoes">
                                <form name="buscarCongregacao" method="post" action="Controller" id="buscarCongregacao">
                                        <label for="Nome">Parâmetro: </label><input type="text" name="busca_input" id="busca_input" />

                                        <div class="buscar_congregacao_submit">
                                            <input type="button" id="buscar_congregacao_submit" name="acao" value="Buscar Congregação"/>
                                        </div>
                                </form>

                                <table id="resultadoBusca">

                                </table>

                                <p class="paginacao" id="paginacao">


                                </p>


                            </div>
                        </div>

                </div>
                <div id="rodape">
                        <%@ include file = "Rodape.html" %>
                </div>
        </div>
    </body>
</html>