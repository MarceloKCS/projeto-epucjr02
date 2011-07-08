<%--
    Document   : SessaoReuniao
    Created on : 20/02/2011, 22:49:56
    Author     : Rogerio
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
            <title>Projeto Engyos - Controle de Presença</title>
            <link href="css/screen.css" rel="stylesheet" type="text/css" />
            <link href="css/jqueryui/jquery-ui.css" type="text/css" rel="stylesheet"/>

            <script type='text/javascript' src="javascript/jquery-1.5.2.js"></script>
            <script type='text/javascript' src="javascript/deployJava.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery-ui.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery.ui.button.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery.ui.datepicker.js"></script>

            <style type="text/css">
                #menubv {
                width: 15em;
                padding-left: 20px;
                margin: 0;
                font: 12px Verdana, sans-serif;
                }
                #menubv ul {
                list-style: none;
                margin: 0;
                padding: 0;
                }
                #menubv li {
                border-bottom: 0px solid #8a8a8a;
                margin: 0;               
                }
                #menubv li a {
                display: block;
                padding: 5px 5px 5px 0.5em;
                font-weight:bold;
                border-left: 10px solid #a7a7a7;
                border-right: 10px solid #fff5ee;
                background-color: #e7e7e7;
                color: #51514a;
                text-decoration: none;
                }
                #menubv li a:hover {
                border-left: 10px solid #d0d0d0;
                border-right: 10px solid #fff;
                background-color: #fff0f5;
                color: #ccc;
                }
                /* Fix IE. Hide from IE Mac \*/
                * html ul#menubv  li { float: left; height: 1%; }
                * html ul#menubv  li a { height: 1%; }
                /* End */
             </style>

             <style type="text/css">
		body { font-size: 62.5%; }
                div#colunaE {font-size: 15px;}
                div#colunaD {font-size: 15px;}

		/*label, input { display:block; }*/
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }
              </style>

             <script type="text/javascript">

                //Responsável pelo menu de obreiros utilizando o JQuery UI
                $(function() {
                    $(document).ready(function() {
                        loadSelectCongregacao();
                    });

                    // a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
                    $( "#obreiro:ui-dialog" ).dialog( "destroy" );

      
                    function updateTips( t ) {
                            tips
                                    .text( t )
                                    .addClass( "ui-state-highlight" );
                            setTimeout(function() {
                                    tips.removeClass( "ui-state-highlight", 1500 );
                            }, 500 );
                    }

                    function checkLength( o, n, min, max ) {
                            if ( o.val().length > max || o.val().length < min ) {
                                    o.addClass( "ui-state-error" );
                                    updateTips( "Length of " + n + " must be between " +
                                            min + " and " + max + "." );
                                    return false;
                            } else {
                                    return true;
                            }
                    }

                    function checkRegexp( o, regexp, n ) {
                            if ( !( regexp.test( o.val() ) ) ) {
                                    o.addClass( "ui-state-error" );
                                    updateTips( n );
                                    return false;
                            } else {
                                    return true;
                            }
                    }

                    //var orderByCampoRelatorio = $("input[name='radio']:checked"),
                    //congregacaoId = $("#my_vertical");

                    //Relatório de obreiros cadastrados
                    $( "#obreiro-dialog-form" ).dialog({
                            autoOpen: false,
                            height: 240,
                            width: 350,
                            modal: true,
                            buttons: {
                                    "Gerar relatório": function() {
                                        
                                        enviaFormData($("input[name='radio']:checked").val(), $("#Congregacao").val());
                                        $( this ).dialog( "close" );
                                    },
                                    Cancela: function() {
                                            $( this ).dialog( "close" );
                                    }
                            },
                            close: function() {
                                    allFields.val( "" ).removeClass( "ui-state-error" );
                            }
                    });

                    function requisitarFormularioObreiro(tipoFormulario, congregacaoId){
                        //$.get('Controller?acao=gerar_relatorio&tipoFormulario=' + tipoFormulario + '&congregacaoId=' + congregacaoId , function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                         $.post('Controller' , {acao: "gerar_relatorio", tipoFormulario: tipoFormulario, congregacaoId: congregacaoId}, function(responseText) {
                            alert(responseText.val());
                        });
                    }

                    function enviaFormDataGraficoPresenca(dataInicio, dataFinal){
                        var tipoFormularioEfetivo = "RELATORIO_GRAFICO";
                        $("#tipoFormulario").attr("value", tipoFormularioEfetivo);
                        $("#congregacaoId").attr("value", "");
                        $("#dataInicioForm").attr("value", dataInicio);
                        $("#dataFimForm").attr("value", dataFinal);
                        $('#requestRelatorioForm').submit();
                    }

                    function enviaFormData(tipoFormulario, congregacaoId){
                        var tipoFormularioEfetivo = tipoFormulario;
                        if(congregacaoId != ""){
                            if(tipoFormulario == "OBREIRO_NOMESORT"){
                                tipoFormularioEfetivo = "OBREIRO_CONGREGACAO_NOMESORT";
                            }
                            else if(tipoFormulario == "OBREIRO_CPFSORT"){
                                tipoFormularioEfetivo = "OBREIRO_CONGREGACAO_CPFSORT";
                            }
                            else if(tipoFormulario == "OBREIRO_CARGOSORT"){
                                tipoFormularioEfetivo = "OBREIRO_CONGREGACAO_CARGOSORT";
                            }
                        }

                        $("#tipoFormulario").attr("value", tipoFormularioEfetivo);
                        $("#congregacaoId").attr("value", congregacaoId);
                        $('#requestRelatorioForm').submit();
                    }

                    $( "#open_obreiro_rel" )
                            .button()
                            .click(function() {
                                    $( "#obreiro-dialog-form" ).dialog( "open" );
                            });

                   //Relatório de congregações cadastradas
                   $( "#congregacao-dialog-form" ).dialog({
                            autoOpen: false,
                            height: 240,
                            width: 350,
                            modal: true,
                            buttons: {
                                    "Gerar relatório": function() {

                                        //alert("submit: " + $("input[name='radioc']:checked").val());
                                        enviaFormData($("input[name='radioc']:checked").val(), "");
                                        $( this ).dialog( "close" );
                                    },
                                    Cancela: function() {
                                            $( this ).dialog( "close" );
                                    }
                            },
                            close: function() {
                                    allFields.val( "" ).removeClass( "ui-state-error" );
                            }
                    });

                    $( "#open_congregacao_rel" )
                            .button()
                            .click(function() {
                                    $( "#congregacao-dialog-form" ).dialog( "open" );
                            });


                     var periodoInicio = $( "#data-inicio" ),
                        periodoFim = $( "#data-fim" ),
                        allFields = $( [] ).add( periodoInicio ).add( periodoFim ),
			tips = $( ".validateTips" );

                    //Form de geração de relatório gráfico
                     $( "#dialog-relatoriograph-form" ).dialog({
                            autoOpen: false,
                            height: 240,
                            width: 350,
                            modal: true,
                            buttons: {
                                    "Gerar relatório": function() {
                                        var bValid = true;
                                        allFields.removeClass( "ui-state-error" );
                                        bValid = bValid && checkLength( periodoInicio, "periodoInicio", 10, 10 );
                                        bValid = bValid && checkLength( periodoFim, "periodoFim", 10, 10 );

                                        if ( bValid ) {
                                            //alert("periodo inicio: " + periodoInicio.val() + "\nperiodo fim: " + periodoFim.val());
                                            enviaFormDataGraficoPresenca(periodoInicio.val(), periodoFim.val());
                                            $( this ).dialog( "close" );
                                        }

                                        
                                    },
                                    Cancela: function() {
                                            $( this ).dialog( "close" );
                                    }
                            },
                            close: function() {
                                    allFields.val( "" ).removeClass( "ui-state-error" );
                            }
                    });

                    $( "#open_relatoriograph_rel" )
                            .button()
                            .click(function() {
                                    $( "#dialog-relatoriograph-form" ).dialog( "open" );
                            });
                    //Ativa o seletor de data usando o JQuery UI Datepicker
                    $( "#data-inicio" ).datepicker({
                            dateFormat: 'dd/mm/yy',
                            disabled: true,
                            changeMonth: true,
                            changeYear: true
                    });

                    $( "#data-fim" ).datepicker({
                            dateFormat: 'dd/mm/yy',
                            disabled: true,
                            changeMonth: true,
                            changeYear: true
                    });

            });


            //Ativa o radio button do java UI
            $(function() {
                $("#radio-obreiro" ).buttonset();

                $("#radio-congregacao" ).buttonset();

                $("#radio-relatoriograph" ).buttonset();
            });

            function loadSelectCongregacao(){

                $.getJSON("frontcontrollerajax?acao=ajax_get_listacongregacao", {ajax: 'true'}, function(data){
                   var options = '<option value="">SELECIONE</option>';
                   for (var i = 0; i < data.length; i++) {
                       options += '<option value="' + data[i].idCongregacao + '">' + data[i].nome + '</option>';
                   }

                   $("select#Congregacao").html(options);
                })
            }
	</script>
    </head>
	<body id="relatorios">
		<div id="all">
			<div id="topo">
				<%@ include file = "Topo.jsp" %>
			</div>
			<div id="menu">
				<ul id="menu_principal_administrativo">
					<%@ include file = "menu_administrativo.html" %>
				</ul>
			</div>
			<div id="conteudo">
                            <ul id="tabmenu">
                                <li><a href="#" style="font-family: Arial, Verdana; font-weight: bold; font-size: 16px;">Relatórios</a></li>
                            </ul>
                            <!-- Janela de geração de lista de obreiros -->
                            <div id="conteudo_tabmenu">
                                <div id="obreiro-dialog-form" title="Obreiros">
                                    <p class="validateTips">Selecione as opções desejadas.</p>

                                    <form method="post" id="obreiro_rel_form" name="" action="#">
                                        <fieldset>
                                                <label for="radio_button">Ordenar campos por:</label>
                                                <div id="radio-obreiro">
                                                    <input type="radio" id="opcao-order-nome" name="radio" checked="checked"  value="OBREIRO_NOMESORT"/><label for="opcao-order-nome">Nome</label>
                                                    <input type="radio" id="opcao-order-congregacao" name="radio" value="OBREIRO_CPFSORT"/><label for="opcao-order-congregacao">CPF</label>
                                                    <input type="radio" id="opcao-order-cargo" name="radio" value="OBREIRO_CARGOSORT"/><label for="opcao-order-cargo">Cargo</label>
                                                </div>
                                                <br/>
                                                <label for="radio_button">Filtrar por congregação: </label>
                                                <br/>
                                                <br/>
                                                <select id="Congregacao">
                                                    <option value=""></option>
                                                </select>
                                                <%--<label for="name">Name</label>
                                                <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
                                                <label for="email">Email</label>
                                                <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
                                                <label for="password">Password</label>
                                                <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />--%>
                                        </fieldset>
                                    </form>
                                </div>
                                <!--Fim: Janela de geração de lista de obreiros -->

                                <!-- Janela de geração de lista de congregação -->
                                <div id="congregacao-dialog-form" title="Congregacões">
                                    <p class="validateTips">Selecione as opções desejadas.</p>

                                    <form method="post" id="congregacao_rel_form" name="" action="#">
                                        <fieldset>
                                                <label for="radio_button">Ordenar campos por:</label>
                                                <div id="radio-congregacao">
                                                    <input type="radio" id="opcao-order-cnome" name="radioc" checked="checked" value="CONGREGACAO_NOMESORT"/><label for="opcao-order-cnome">Nome</label>
                                                    <input type="radio" id="opcao-order-cendereco" name="radioc" value="CONGREGACAO_ENDERECOSORT"/><label for="opcao-order-cendereco">Endereço</label>
                                                </div>
                                                <br/>
                                                <%--<label for="name">Name</label>
                                                <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
                                                <label for="email">Email</label>
                                                <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
                                                <label for="password">Password</label>
                                                <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />--%>
                                        </fieldset>
                                    </form>
                                </div>
                                <!--Fim: Janela de geração de lista de congregação -->

                                <!-- Janela de geração de Relatório de presença -->

                                <div id="dialog-relatoriograph-form" title="Relatório Gráfico">
                                    <p class="validateTips">Selecione as opções desejadas.</p>

                                    <form method="post" id="congregacao_rel_graph" name="" action="#">
                                        <fieldset>
                                                <label for="Periodo Inicio">Período Inicio</label>
                                                <input type="text" name="data-inicio" id="data-inicio" class="text ui-widget-content ui-corner-all" readonly="readonly" />

                                                <label for="Periodo Fim">Período Fim</label>
                                                <input type="text" name="data-fim" id="data-fim" class="text ui-widget-content ui-corner-all" readonly="readonly"/>
                                                <%--<label for="name">Name</label>
                                                <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
                                                <label for="email">Email</label>
                                                <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
                                                <label for="password">Password</label>
                                                <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />--%>
                                        </fieldset>
                                    </form>
                                </div>

                                <form method="post" name="requestRelatorioForm" id="requestRelatorioForm" action="Controller">

                                    <input type="hidden" name="tipoFormulario" id="tipoFormulario" value=""/>
                                    <input type="hidden" name="congregacaoId" id="congregacaoId" value=""/>
                                    <input type="hidden" name="dataInicioForm" id="dataInicioForm" value=""/>
                                    <input type="hidden" name="dataFimForm" id="dataFimForm" value=""/>
                                    <input type="hidden" name="acao" value="gerar_relatorio"/>
                                </form>

                                <!--Fim: Janela de geração de Relatório de presença -->

                                <%--<button id="create-user">Gerar relatório</button>--%>


                                     <%--   <p><a href="Controller?acao=gerar_relatorio">Obter lista de obreiros por congregação</a></p>--%>

                                        <ul id="menubv">
    <%--                                        <li>
                                                <a href="#" title="Entrada no site">Home</a>
                                            </li>--%>
                                            <li>
                                                <a id="open_obreiro_rel" href="#" style="margin-left: -45px;" title="Obter Lista de Obreiros">Lista de Obreiros</a>
                                            </li>
                                            <li>
                                                <a id="open_congregacao_rel" href="#" style="margin-left: -45px;" title="Técnicas de layout com CSS">Lista de Congregações</a>
                                            </li>
                                            <li>
                                                <a id="open_relatoriograph_rel" href="#" style="margin-left: -45px;" title="Técnicas de efeitos em links">Relatório de presença</a>
                                            </li>
                                         </ul>

                                </div>
				<div id="colunaD">
					
				</div>
			</div>
			<div id="rodape">
				<%@ include file = "Rodape.html" %>
			</div>
		</div>
	</body>
</html>