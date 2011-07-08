<%-- 
    Document   : SessaoReuniao
    Created on : 20/02/2011, 22:49:56
    Author     : Rogerio
--%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils"%>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.PaginaDeReuniao" %>
<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>

<%
PaginaDeReuniao paginaDeReuniao = (PaginaDeReuniao) request.getAttribute("paginaDeReuniao");
Date tempoDecorrido = (Date) request.getAttribute("reuniaoElapsedTime");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reuniões - Projeto Engyos - Controle de Presença</title>
	<link href="css/screen.css" rel="stylesheet" type="text/css" />
        <link href="css/jqueryui/jquery-ui.css" type="text/css" rel="stylesheet"/>
        <!--<link href="css/jqueryui/jquery.ui.all.css" type="text/css" rel="stylesheet"/>-->
               
        <script type='text/javascript' src="javascript/jquery-1.5.2.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery-ui.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery.ui.button.js"></script>
        <script type='text/javascript' src="javascript/jquery.form.js"></script>
        <script src="javascript/jquery.alerts.js" type="text/javascript"></script>
        <link href="css/jqueryalerts/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />
        <script type='text/javascript' src="javascript/deployJava.js"></script>

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
                .add_obreiro_button {padding-top: 10px; font-size: 62.5%; }
                .add_congregacao_button {padding-top: 10px; font-size: 62.5%; }
                .reuniao_control_button {padding-top: 10px; font-size: 62.5%; }
                .reuniao_presencacpf_button {padding-top: 10px; font-size: 62.5%; }
	</style>
	<script type="text/javascript">
	$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$( "#dialog:ui-dialog" ).dialog( "destroy" );

		var name = $( "#name" ),
			cargo = $( "#cargo" ),
                        cpf = $( "#cpf" ),
			senha = $( "#senha" ),
                        confrimacaoSenha = $( "#confrimacaoSenha" ),
                        congregacao = $( "#Congregacao" ),
			allFields = $( [] ).add( name ).add( cargo ).add( cpf ).add( senha ).add( confrimacaoSenha ).add( congregacao ),
			tips = $( ".validateTips" );

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
				updateTips( "Tamanho de " + n + " deve estar entre " +
					min + " e " + max + "." );
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

                function checkCongregacao(congregacao){
                    if(congregacao.val() == "SELECIONE" || congregacao.val() == ""){
                        congregacao.addClass( "ui-state-error" );
                        updateTips( "Selecione uma congregação" );
                        return false;
                    }
                    else{
                        return true;
                    }
                }

                function checkMatchSenha(senha, senhaConfirmacao){
                    if(senha.val() != senhaConfirmacao.val()){
                        senha.addClass( "ui-state-error" );
                        senhaConfirmacao.addClass( "ui-state-error" );
                        updateTips( "A senha difere da confirmação..." );
                        return false;
                    }
                    else{
                         return true;
                    }
                }

                function registrarObreiro(nome, cargo, cpf, senha, confirmacaoSenha, congregacaoId, reuniaoId){
                    $.get('frontcontrollerajax?acao=ajax_obreiro_register&Nome=' + nome +
                        "&Cargo=" + cargo +
                        "&Cpf=" + cpf +
                        "&Senha=" + senha +
                        "&SenhaConfirmacao=" + confirmacaoSenha +
                        "&Congregacao=" + congregacaoId +
                        "&idReuniao=" + reuniaoId, function(respostaOperacao) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                           // $('#status').text(responseText);
                           jAlert(respostaOperacao);

                           if(respostaOperacao != "Operação Realizada com Sucesso!"){
                               updateTips( respostaOperacao );
                           }
                     });

                 }

		$( "#dialog-form" ).dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
				"Cadastrar Obreiro": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );

					bValid = bValid && checkLength( name, "nome", 3, 80 );
					bValid = bValid && checkLength( cargo, "cargo", 6, 80 );
                                        bValid = bValid && checkLength( cpf, "cpf", 11, 11 );
					bValid = bValid && checkLength( senha, "senha", 5, 16 );
                                        bValid = bValid && checkLength( confrimacaoSenha, "confrimacaoSenha", 5, 16 );
                                        bValid = bValid && checkMatchSenha(senha, confrimacaoSenha);
                                        bValid = bValid && checkCongregacao(congregacao);
					//bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
					//bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
					//bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );

					if ( bValid ) {

                                                registrarObreiro(name.val(), cargo.val(), cpf.val(), senha.val(), confrimacaoSenha.val(), congregacao.val(), $('#idReuniao').val());
                                                $( this ).dialog( "close" );
                                                
					}
				},
				Cancelar: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});

		$( "#create-user" )
                    .button()
                    .click(function() {
                            $( "#dialog-form" ).dialog( "open" );
                            return false;
                });
                $( "input:button", ".reuniao_presencacpf_button" ).button();
                $( "input:button", ".reuniao_control_button" ).button();
                //$( "input:submit", ".start_button" ).click(function() {
                    //return true;
                //});
                
                var nome_congregacao = $( "#nome_congregacao" ),
                                endereco_congregacao = $( "#endereco_congregacao" ),
                                allFields = $( [] ).add( nome_congregacao ).add( endereco_congregacao ),
                                tips = $( ".validateTips" );
                
                function submitCOngregacaoForm(nomeCongregacao, enderecoCongregacao){
                   var responseText = "";
                    $.get('frontcontrollerajax?acao=congregacao_register&Nome=' + nomeCongregacao + "&Endereco=" + enderecoCongregacao, function(respostaOperacao) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                           // $('#status').text(responseText);

                           if(responseText == "Operação Realizada com Sucesso!"){
                                alert(responseText);
                           }
                           jAlert(respostaOperacao);
                           loadSelectCongregacao();

                     });
                }


                $( "#dialog-congregacao-form" ).dialog({
                    autoOpen: false,
                    height: 300,
                    width: 350,
                    modal: true,
                    buttons: {
                            "Cadastrar Congragação": function() {
                                    var bValid = true;
                                    allFields.removeClass( "ui-state-error" );

                                    bValid = bValid && checkLength( nome_congregacao, "Nome", 3, 100 );
                                    bValid = bValid && checkLength( endereco_congregacao, "Endereço", 3, 100 );

                                    //bValid = bValid && checkRegexp( name, /^[a-zA-Z0-9*\s,'-]*$/i, "O nome deve consistir de a-z, 0-9, underscores, iniciar com letra." );
                                    // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
                                    //bValid = bValid && checkRegexp( address, /^[a-zA-Z0-9*\s,'-]*$/i, "O nome deve consistir de a-z, 0-9, underscores, iniciar com letra." );

                                    if ( bValid ) {

                                            submitCOngregacaoForm(nome_congregacao.val(), endereco_congregacao.val());

                                            $( this ).dialog( "close" );
                                    }
                            },
                            Cancelar: function() {
                                    $( this ).dialog( "close" );
                            }
                    },
                    close: function() {
                            allFields.val( "" ).removeClass( "ui-state-error" );
                    }
            });

            $( "#create-congregacao" )
                .button()
                .click(function() {
                        $( "#dialog-congregacao-form" ).dialog( "open" );
                        return false;
            });

            $(window).unload(function (){
                fecharReuniao();
            }); 
	});

	</script>
        
        <script type="text/javascript"> 
            
            var timercount = 0;
            var timestart  = null;
            var timeClockStarted = false;
            var timedifference = 0;
            <%if(paginaDeReuniao.getTempoCorridoReuniao() != 0){%>
                timedifference = <%out.print(paginaDeReuniao.getTempoCorridoReuniao());%>;
            <%}%>
               
            
            function mostra_tempo() {
                    if(timercount) {
                            clearTimeout(timercount);
                            clockID = 0;
                    }
                    if(!timeClockStarted){
                            timestart = new Date();
                            timeClockStarted = true;
                    }
                    var timeend = new Date();
                    
                    timedifference = timeend.getTime() - timestart.getTime();
                                        
                    timeend.setTime(timedifference);
                    var hour_passed = timeend.getUTCHours();
                    if(hour_passed < 10){
                        hour_passed = "0" + hour_passed;
                    }
                    var minutes_passed = timeend.getMinutes();
                    if(minutes_passed < 10){
                            minutes_passed = "0" + minutes_passed;
                    }
                    var seconds_passed = timeend.getSeconds();
                    if(seconds_passed < 10){
                            seconds_passed = "0" + seconds_passed;
                    }
                    document.timeform.timetextarea.value = hour_passed + ":" + minutes_passed + ":" + seconds_passed;
                    timercount = setTimeout("showtimer()", 1000);
            }

            function showtimer() {
                    if(timercount) {
                            clearTimeout(timercount);
                            clockID = 0;
                    }
                    if(!timeClockStarted){
                            timestart = new Date();
                            timeClockStarted = true;
                    }
                    var timeend = new Date();
                    
                    timedifference = timeend.getTime() - timestart.getTime();
                    timeend.setTime(timedifference);
                    var hour_passed = timeend.getUTCHours();
                    if(hour_passed < 10){
                        hour_passed = "0" + hour_passed;
                    }
                    var minutes_passed = timeend.getMinutes();
                    if(minutes_passed < 10){
                            minutes_passed = "0" + minutes_passed;
                    }
                    var seconds_passed = timeend.getSeconds();
                    if(seconds_passed < 10){
                            seconds_passed = "0" + seconds_passed;
                    }
                    document.timeform.timetextarea.value = hour_passed + ":" + minutes_passed + ":" + seconds_passed;
                    timercount = setTimeout("showtimer()", 1000);
            }

            function time_start(){
                 if(!timercount){
                    timestart   = new Date();
                    var timeInput = <%out.print(paginaDeReuniao.getTempoCorridoReuniao());%>;
                    
                    if(timeInput != 0){
                        var temporeal = timestart.getTime() - timeInput;
                    }
                    else{
                        var timeCurrent = timestart.getTime();
                        var temporeal = timeCurrent;
                    }

                    timestart.setTime(temporeal);
                    
                    document.timeform.timetextarea.value = "00:00:00";                    
                    //document.timeform.laptime.value = "";
                    timeClockStarted = true;
                    timercount  = setTimeout("showtimer()", 1000);
                 }
            }

            function sw_start(){
                <%if(paginaDeReuniao.getTempoCorridoReuniao() != 0){%>

                        var timedifference = <%out.print(paginaDeReuniao.getTempoCorridoReuniao());%>

                        timeend.setTime(timedifference);

                        var hours_passed = timeend.getUTCHours();
                        if(hours_passed < 10){
                            hours_passed = "0" + hours_passed;
                        }
                        var minutes_passed = timeend.getMinutes();
                        if(minutes_passed < 10){
                                minutes_passed = "0" + minutes_passed;
                        }
                        var seconds_passed = timeend.getSeconds();
                        if(seconds_passed < 10){
                                seconds_passed = "0" + seconds_passed;
                        }
                        var milliseconds_passed = timeend.getMilliseconds();
                        if(milliseconds_passed < 10){
                                milliseconds_passed = "00" + milliseconds_passed;
                        }
                        else if(milliseconds_passed < 100){
                                milliseconds_passed = "0" + milliseconds_passed;
                        }
                        document.timeform.laptime.value = hours_passed + ":" + minutes_passed + ":" + seconds_passed;
                <%}else{%>

                    if(!timercount){
                    timestart   = new Date();
                    document.timeform.timetextarea.value = "00:00:00";
                    document.timeform.laptime.value = "";
                    timercount  = setTimeout("showtimer()", 1000);
                    }
                    else{
                        var timeend = new Date();
                        <%if(paginaDeReuniao.getTempoCorridoReuniao() != 0){%>
                        var timedifference = <%out.print(paginaDeReuniao.getTempoCorridoReuniao());%>
                        <%}else{%>
                        var timedifference = timeend.getTime() - timestart.getTime();
                        <%}%>


                        timeend.setTime(timedifference);
                        var hours_passed = timeend.getUTCHours();
                        if(hours_passed < 10){
                            hours_passed = "0" + hours_passed;
                        }
                        var minutes_passed = timeend.getMinutes();
                        if(minutes_passed < 10){
                                minutes_passed = "0" + minutes_passed;
                        }
                        var seconds_passed = timeend.getSeconds();
                        if(seconds_passed < 10){
                                seconds_passed = "0" + seconds_passed;
                        }
                        var milliseconds_passed = timeend.getMilliseconds();
                        if(milliseconds_passed < 10){
                                milliseconds_passed = "00" + milliseconds_passed;
                        }
                        else if(milliseconds_passed < 100){
                                milliseconds_passed = "0" + milliseconds_passed;
                        }
                        document.timeform.laptime.value = hours_passed + ":" + minutes_passed + ":" + seconds_passed;
                    }
                    <%}%>
            }

            function Stop() {
                    if(timercount) {
                            clearTimeout(timercount);
                            timercount  = 0;
                            var timeend = new Date();
                            var timedifference = timeend.getTime() - timestart.getTime();
                            timeend.setTime(timedifference);
                            var hours_passed = timeend.getUTCHours();
                            if(hours_passed < 10){
                                hours_passed = "0" + hours_passed;
                            }
                            var minutes_passed = timeend.getMinutes();
                            if(minutes_passed < 10){
                                    minutes_passed = "0" + minutes_passed;
                            }
                            var seconds_passed = timeend.getSeconds();
                            if(seconds_passed < 10){
                                    seconds_passed = "0" + seconds_passed;
                            }
                            var milliseconds_passed = timeend.getMilliseconds();
                            if(milliseconds_passed < 10){
                                    milliseconds_passed = "00" + milliseconds_passed;
                            }
                            else if(milliseconds_passed < 100){
                                    milliseconds_passed = "0" + milliseconds_passed;
                            }
                            document.timeform.timetextarea.value = hours_passed + ":" + minutes_passed + ":" + seconds_passed;
                    }
                    timestart = null;
            }

            function Reset() {
                    timestart = null;
                    document.timeform.timetextarea.value = "00:00:00";
                    document.timeform.laptime.value = "";
            }

        </script>

        <script type="text/javascript">
           
            //Exibe um valor definido na applet no modelo ajax
            function writeMessage(message) {
                summaryElem = document.getElementById("status");
                summaryElem.innerHTML = message;
            }

            function updateApplet()
            {
                document.jsap.setMessage("Some message from me");
            }

            function updateWebPage(s)
            {
                document.forms[0].txt1.value = s;
            }
        </script>



        <!-- Funções em javascript que implementam o Ajax usando o JQuery -->

        <script type="text/javascript">
            $(document).ready(function() {
                loadSelectCongregacao();

                // When the HTML DOM is ready loading, then execute the following function...
                $('#presencacpf_button').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
                    $.get('frontcontrollerajax?acao=marcar_presenca_cpf&cpf=' + $('#presenca_cpf').val() + '&idReuniao=' + $('#idReuniao').val(), function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        jAlert(responseText);
                        $("#presenca_cpf").attr("value", "");
                        //$('#status').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                    });
                });

                $('#start_button').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
                    $.get('frontcontrollerajax?acao=iniciar_session_reuniao' + '&idReuniao=' + $('#idReuniao').val(), function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        jAlert(responseText, 'Mensagem');
                        //$('#status').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                    });
                });

                $('#stop_button').click(function() {
                    fecharReuniao();
                    
                });

                $('a').click(function(){
                       var value = $(this).attr("class");
                       var redirectAddress =$(this).attr("href");
                       if(value == "ui-dialog-titlebar-close ui-corner-all ui-state-hover"){
                           return false;
                       }

                       fecharReuniaoLink(redirectAddress);
                        return false;
                       //var confirmExit = confirm('Tem certeza que deseja sair? \n A sessão de reunião será interrompida.');

                        //confirmMessage(function(r){
                        //    alert(r);
                        //    confirmationUser = r;
                        //    return true;
                        //});
                        //while(!response) continue; // wait
                         //if(confirmExit){
                         //      $.get('frontcontrollerajax?acao=encerrar_session_reuniao', function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                         //           //jAlert(responseText, 'Alert Dialog');
                        //            return true;
                        //       });
                         //  }
                          // else{
                         //       return false;
                         //  }
                });

                function fecharReuniaoLink(linkRedirect){
                    jConfirm('Tem certeza que deseja sair da Reunião? \n A sessão de reunião será interrompida.', 'Confirmation Dialog', function(confirmExit) {
                        if(confirmExit){
                            Stop();
                            $.get('frontcontrollerajax?acao=encerrar_session_reuniao', function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                                jAlert(responseText, 'Mensagem', function(){
                                    location.replace(linkRedirect);
                                });
                                //$('#status').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                            });

                        }
                    });
                }

                function fecharReuniao(){
                    jConfirm('Tem certeza que deseja parar a Reunião? \n A sessão de reunião será interrompida.', 'Confirmation Dialog', function(confirmExit) {
                        if(confirmExit){
                            Stop();
                            $.get('frontcontrollerajax?acao=encerrar_session_reuniao', function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                                jAlert(responseText, 'Mensagem', function(){
                                    location.replace("Controller?acao=page_loader&page_corrente=pagina_principal");
                                });
                                //$('#status').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                            });

                        }
                    });
                }

                function confirmMessage(functionCallback){

                    jConfirm('Tem certeza que deseja sair? \n A sessão de reunião será interrompida.', 'Confirmation Dialog', function(confirmExit) {
                        functionCallback && functionCallback(confirmExit); // call the callback if provided

                    });

                }

                // increase the default animation speed to exaggerate the effect
                $.fx.speeds._default = 1000;
                $(function() {
                        $( "#dialog" ).dialog({
                                autoOpen: false,
                                show: "blind",
                                hide: "explode"
                        });

                        $( "#opener" ).click(function() {
                                $( "#dialog" ).dialog( "open" );
                                return false;
                        });
                });            
            });

            function loadSelectCongregacao(){
                        var congregacaoSelecionada = "";

                        $.getJSON("frontcontrollerajax?acao=ajax_get_listacongregacao", {ajax: 'true'}, function(data){
                           var options = '<option value="">SELECIONE</option>';
                           for (var i = 0; i < data.length; i++) {
                               if(congregacaoSelecionada == data[i].nome){
                                   options += '<option selected="selected" value="' + data[i].idCongregacao + '">' + data[i].nome + '</option>';
                               }
                               else{
                                   options += '<option value="' + data[i].idCongregacao + '">' + data[i].nome + '</option>';
                               }
                           }
                           if(data.length > 0 ){
                               var hidden_field = '<input type="hidden" value="' + data[0].nome +  '" name="' + data[0].idCongregacao + '">';

                               for (var i = 1; i < data.length; i++) {
                                   hidden_field += '<input type="hidden" value="' + data[i].nome +  '" name="' + data[i].idCongregacao + '">';
                               }
                               $("div#Congregacao_data").html(hidden_field);
                           }

                           $("select#Congregacao").html(options);

                        })
                    }
                    
         </script>
    </head>
    <body id="reuniao" style="cursor: auto;">

        <!-- Encapsula toda a página -->
        <div id="all">

            <div id="topo">
                <%@ include file = "Topo.jsp" %>
            </div>
            <div id="menu">
                    <%@ include file = "Menu.html" %>
            </div>

            <!-- Conteudo da página -->
            <div id="conteudo">     
              
                <div id="dialog-form" title="Cadastrar Obreiro">
                    <p class="validateTips">Todos os campos tem preenchimento obrigatório.</p>

                    <form method="get" name="obreiro_reuniao_form" action="#">
                        <fieldset>
                                <label for="name">Nome</label>
                                <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
                                <label for="cargo">Cargo</label>
                                <input type="text" name="cargo" id="cargo" value="" class="text ui-widget-content ui-corner-all" />
                                <label for="cpf">CPF</label>
                                <input type="text" name="cpf" id="cpf" value="" class="text ui-widget-content ui-corner-all" />
                                <label for="senha">Senha</label>
                                <input type="password" name="senha" id="senha" value="" class="text ui-widget-content ui-corner-all" />
                                <label for="confrimacaoSenha">Confirmação da Senha</label>
                                <input type="password" name="confrimacaoSenha" id="confrimacaoSenha" value="" class="text ui-widget-content ui-corner-all" />
                                 <br/>
                                <label for="congregacoes">Congregação</label>
                                <br/>
                                <br/>
                                <select id="Congregacao">
                                        <option value=""></option>
                                </select>

                                <div id="Congregacao_data">

                                </div>
                        </fieldset>
                    </form>
                </div>

                <div id="dialog-congregacao-form" title="Adicionar nova congregação">
                    <p class="validateTips">Todos os campos tem preenchimento obrigatório.</p>

                    <form method="post" name="congregacao_addin_reuniao" id="congregacao_addin_reuniao" action="frontcontrollerajax">
                        <fieldset>
                                <label for="nome_congregacao">Nome</label>
                                <input type="text" name="nome_congregacao" id="nome_congregacao" class="text ui-widget-content ui-corner-all" />
                                <label for="endereco_congregacao">Endereço</label>
                                <input type="text" name="endereco_congregacao" id="endereco_congregacao" value="" class="text ui-widget-content ui-corner-all" />
                        </fieldset>
                        <input type="hidden" id="action_value" name="acao" value="congregacao_register"/>
                    </form>
                </div>


                <form method="post" name="timeform" action="#">

                    <div id="colunaE">
                        <h2>Dados da Reunião</h2>
                        
                        <br/>
                        <label>Inicio da Reunião:</label> <%out.print(paginaDeReuniao != null ? paginaDeReuniao.getTempoDeInicio() : "");%> <!--<input type="text" name="Inicio" value="" readonly disabled> -->
                        <br/>
                        <br/>
                        <label>Local: </label><%out.print(paginaDeReuniao != null ? paginaDeReuniao.getLocal() : "");%>
                        <br/>
                        <br/>
                        
                        Tempo de Reunião: <input type=text name="timetextarea" value="00:00:00" size="10" style = "font-size:15px" readonly="readonly"/>

                        <!-- Lap:<input type=text name="laptime" size="10" style = "font-size:20px">-->
                        <input type="hidden" name="idReuniao" id="idReuniao" value="<%out.print(paginaDeReuniao != null ? paginaDeReuniao.getIdReuniao() : "");%>"/>
                        <%--<div id="status">

                        </div>--%>
                        <br/>
                        <h2>Opções Adicionais</h2>
                        <div id="additional_options" class="additional_options">
                            
                            <div id="add_obreiro_button" class="add_obreiro_button">
                                <button id="create-user">Adicionar Obreiro</button>
                            </div>
                            <div id="add_congregacao_button" class="add_congregacao_button">
                                <button id="create-congregacao">Adicionar congregação</button>
                            </div>
                        </div>
                        
                        <p id="summary"/>
                        <br/>
                        <h2>Controle Reunião</h2>
                        <div id="reuniao_control_button" class="reuniao_control_button">
                            <input type=button id="start_button" name="start" value="Iniciar" onclick="time_start()">
                            <input type=button id="stop_button" name="stop" value="Parar">
                           <%-- <input type=button name="reset" value="Reset" onclick="Reset()">--%>
                        </div>
                    </div>

                    <div id="colunaD">
                        <h2>Marcar Presença</h2>
                        <br/>
                        <label for="cpf">CPF</label>                        
                        <input type="text" name="presenca_cpf" value="" id="presenca_cpf" size="35"/>
                        <div id="reuniao_presencacpf_button" class="reuniao_presencacpf_button">
                            <input type="button" id="presencacpf_button" name="presencacpf_button" value="Marcar Presença"/>
                        </div>
                        <div id="digital">

                             <%--<object id="jsap" height="189" width="389" name="jsap" type="application/x-java-applet;version=1.4.1">
                                <param value="MarcarPresencaDigitalApplet.jar, lib/NBioBSPJNI.jar" name="archive">
                                <param value="epucjr.engyos.applet.gui.DigitalCaptureAppletGUI" name="code">
                                <param value="yes" name="mayscript">
                                <param value="true" name="scriptable">
                                <param value="jsapplet" name="name">
                                <param value="<% out.print(paginaDeReuniao.getIdReuniao()); %>" name="idReuniao">
                            </object>--%>

                             <script type="text/javascript">
                               var attributes = {
                                    code:       "epucjr.engyos.applet.gui.DigitalCaptureAppletGUI",
                                    archive:    "MarcarPresencaDigitalApplet.jar, lib/NBioBSPJNI.jar",
                                    width:      389,
                                    height:     189
                                };
                                var parameters = {idReuniao:<% out.print(paginaDeReuniao.getIdReuniao()); %>, jnlp_href:"marcar_presenca_digital.jnlp"}; <!-- Applet Parameters -->
                                var version = "1.5"; <!-- Required Java Version -->
                                deployJava.runApplet(attributes, parameters, version);
                            </script>
                            <input type="button" onclick="updateApplet()" value="Update applet">
                        </div>
                    </div>

                </form>


            </div>
            
             <!-- Rodapé da página -->
            <div id="rodape">
		<%@ include file = "Rodape.html" %>
            </div>

        </div>
       
    </body>
</html>
