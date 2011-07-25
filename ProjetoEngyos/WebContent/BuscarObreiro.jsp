<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDoObreiro" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%	
	FormularioDeBuscaDoObreiro resultadoDeBuscaDoObreiro = (FormularioDeBuscaDoObreiro) request.getAttribute("resultadoDeBuscaDoObreiro");
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
            .buscar_obreiro_submit {padding-top: 10px; font-size: 62.5%; }
        </style>

        <script type="text/javascript">
           $(function() {

                $("#obreiroDelete").click( function() {
                    jConfirm('Confirmar operação?', 'Confirmation Dialog', function(r) {
                        jAlert('Confirmed: ' + r, 'Confirmation Results');
                    });
                });

                $( "input:button", ".buscar_obreiro_submit" ).button();
                $( "input:button", ".buscar_obreiro_submit" ).click(function() {
                    var parametroBusca = $("#busca_input");
                    //jAlert('parametro de busca: ' + parametroBusca.val(), 'Alert Dialog');
                    realizaBuscaDeObreiros(parametroBusca.val(), 1);
                    return false;
                });
            });

            function removeObreiro(parametroBusca, cpf, paginaCorrente, qtdResultados){
                var qtdResultadosPorPagina = 10;
                var paginas = Math.ceil((qtdResultados - 1) / qtdResultadosPorPagina);

                if(paginaCorrente > 1 && paginaCorrente > paginas){
                    paginaCorrente = paginaCorrente - 1;
                }
                jConfirm('Deseja realmente apagar? \nEsta operação não é reversivel após confirmação.', 'Confirmation Dialog', function(okResponse) {
                    if(okResponse){
                        $.get('frontcontrollerajax?acao=ajax_obreiro_remover&cpfObreiro=' + cpf, function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                           // $('#status').text(responseText);
                           jAlert(responseText , 'Confirmation Results');
                           realizaBuscaDeObreiros(parametroBusca, paginaCorrente);
                        });

                        //jAlert('Removido: ' + parametroBusca + "\ncpf: " + cpf + "\npaginaCorrente: " + paginaCorrente + "\nResultados: " + qtdResultados + "\nPaginas: " + paginas , 'Confirmation Results');
                    }
                    else{
                        jAlert('Operação Cancelada', 'Alert Dialog');
                    }
                });
            }

            function realizaBuscaDeObreiros(parametroBusca, paginaSelecionada){
                 $.getJSON("frontcontrollerajax?acao=ajax_get_obreirosearch&parametroBusca=" + parametroBusca + "&paginaCorrente=" + paginaSelecionada, {ajax: 'true'}, function(resultadoBuscaArray){

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
                            dadosDePaginacao += pipePAgina +"<a class=\"pagina\" href=\"#\" onclick=\"realizaBuscaDeObreiros(\u0027" + parametroBusca + "\u0027, " + numPagina + ");\">" +numPagina+ "</a>";
                        }

                        pipePAgina = "<a> | </a>";
                    }

                    //Tabela de resultados
                    var countVisual = 0;
                    var tabela = '<tr> <th>CPF</th>  <th>Nome</th> <th>Editar</th> <th>|Excluir</th> </tr>';
                    for (var i = 1; i < resultadoBuscaArray.length; i++) {
                        tabela += "<tr class=\"zebra" + countVisual % 2 + "\">";
                        tabela += " <td>" + resultadoBuscaArray[i].cpf + "</td>";
                        tabela += " <td>|" + resultadoBuscaArray[i].nome + "</td>";
                        tabela += " <td> <a href=\"Controller?acao=obreiro_editformload&cpfObreiro=" + resultadoBuscaArray[i].cpf + "\">" + "<img src=\"imagens/edit.png\" width=\"18\" height=\"18\" title=\"Alterar Dados\"/></a>" +  "</td>";
                        tabela += " <td> <a href=\"#\" id=\"obreiroDelete\" onclick=\"removeObreiro(\u0027" + parametroBusca + "\u0027, \u0027" + resultadoBuscaArray[i].cpf + "\u0027, " + paginaCorrenete + ", " + qtdPResultados  + ");\" >" + "<img src=\"imagens/error.png\" width=\"18\" height=\"18\" title=\"Apagar Dados\"/></a>" +  "</td>";
                        tabela += "</tr>";

                        countVisual++;
                    }

                    $("table#resultadoBusca").html(tabela);
                    $("#paginacao").html(dadosDePaginacao);
                 });
            }

        </script>

    </head>
    <body id="buscarObreiro">
        <div id="all">
                <div id="topo">
                        <%@ include file = "Topo.jsp" %>
                </div>
                <div id="menu">
                        <%@ include file = "Menu.html" %>
                </div>
                <div id="conteudo">
                        <ul id="tabmenu">
                                <li id="liObreiros" class="tab_on"><a href="Controller?acao=busca_loader&pagina_busca=buscar_obreiro">Obreiro</a></li>
                                <li id="liCongregacoes" class="tab_off"><a href="Controller?acao=busca_loader&pagina_busca=buscar_congregacao">Congregação</a></li>
                                <li id="liReunioes" class="tab_off"><a href="Controller?acao=busca_loader&pagina_busca=buscar_reuniao">Reunião</a></li>
                        </ul>
                        <div id="conteudo_tabmenu">
                                <div id="buscarObreiros">
                                        <form name="buscarObreiro" method="post" action="Controller" id="buscarObreiro">
                                            <label for="Nome">Parâmetro: </label><input type="text" name="busca_input" id="busca_input" />
                                            <!--<label for="Cpf">CPF: </label><input type="text" name="Cpf" maxlength="11" id="Cpf" />-->
                                            <div class="buscar_obreiro_submit">
                                                <input type="button" id="buscar_obreiro_submit" name="acao" value="Buscar Obreiro"/>
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