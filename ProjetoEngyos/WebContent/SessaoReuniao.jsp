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
	<link href="screen.css" rel="stylesheet" type="text/css" rel="stylesheet"/>
        <link href="css/jqueryui/jquery-ui.css" type="text/css" rel="stylesheet"/>
        <!--<link href="css/jqueryui/jquery.ui.all.css" type="text/css" rel="stylesheet"/>-->
               
        <script type='text/javascript' src="javascript/jquery-1.5.2.js"></script>
        <script type='text/javascript' src="javascript/deployJava.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery-ui.js"></script>

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
	$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$( "#dialog:ui-dialog" ).dialog( "destroy" );

		var name = $( "#name" ),
			email = $( "#email" ),
			password = $( "#password" ),
			allFields = $( [] ).add( name ).add( email ).add( password ),
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

		$( "#dialog-form" ).dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
				"Create an account": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );

					bValid = bValid && checkLength( name, "username", 3, 16 );
					bValid = bValid && checkLength( email, "email", 6, 80 );
					bValid = bValid && checkLength( password, "password", 5, 16 );

					bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
					bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
					bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );

					if ( bValid ) {
						$( "#users tbody" ).append( "<tr>" +
							"<td>" + name.val() + "</td>" +
							"<td>" + email.val() + "</td>" +
							"<td>" + password.val() + "</td>" +
						"</tr>" );
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
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
			});
	});
	</script>
        
        <script type="text/javascript"> 
            
            var timercount = 0;
            var timestart  = null;
            <%if(paginaDeReuniao.getTempoCorridoReuniao() != 0){%>
                timediference = paginaDeReuniao.getTempoCorridoReuniao();
            <%}else{%>
                var timediference = 0;
            <%}%>

            function mostra_tempo() {
                    if(timercount) {
                            clearTimeout(timercount);
                            clockID = 0;
                    }
                    if(!timestart){
                            timestart = new Date();
                    }
                    var timeend = new Date();
                    var timedifference = timeend.getTime() - timestart.getTime();
                    timeend.setTime(timedifference);
                    var minutes_passed = timeend.getMinutes();
                    if(minutes_passed < 10){
                            minutes_passed = "0" + minutes_passed;
                    }
                    var seconds_passed = timeend.getSeconds();
                    if(seconds_passed < 10){
                            seconds_passed = "0" + seconds_passed;
                    }
                    document.timeform.timetextarea.value = minutes_passed + ":" + seconds_passed;
                    timercount = setTimeout("showtimer()", 1000);
            }

            function showtimer() {
                    if(timercount) {
                            clearTimeout(timercount);
                            clockID = 0;
                    }
                    if(!timestart){
                            timestart = new Date();
                    }
                    var timeend = new Date();
                    var timedifference = timeend.getTime() - timestart.getTime();
                    timeend.setTime(timedifference);
                    var minutes_passed = timeend.getMinutes();
                    if(minutes_passed < 10){
                            minutes_passed = "0" + minutes_passed;
                    }
                    var seconds_passed = timeend.getSeconds();
                    if(seconds_passed < 10){
                            seconds_passed = "0" + seconds_passed;
                    }
                    document.timeform.timetextarea.value = minutes_passed + ":" + seconds_passed;
                    timercount = setTimeout("showtimer()", 1000);
            }

            function time_start(){
                 if(!timercount){
                    timestart   = new Date();
                    var timeInput = <%out.print(paginaDeReuniao.getTempoCorridoReuniao());%>;
                    var timeCurrent = timestart.getTime();
                    var temporeal = timeCurrent - timeInput;
                    timestart.setTime(temporeal);
                    document.timeform.timetextarea.value = "00:00";
                    //document.timeform.laptime.value = "";
                    timercount  = setTimeout("showtimer()", 1000);
                 }
            }

            function sw_start(){
                <%if(paginaDeReuniao.getTempoCorridoReuniao() != 0){%>

                        var timedifference = <%out.print(paginaDeReuniao.getTempoCorridoReuniao());%>

                        timeend.setTime(timedifference);
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
                        document.timeform.laptime.value = minutes_passed + ":" + seconds_passed + "." + milliseconds_passed;
                <%}else{%>

                    if(!timercount){
                    timestart   = new Date();
                    document.timeform.timetextarea.value = "00:00";
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
                        document.timeform.laptime.value = minutes_passed + ":" + seconds_passed + "." + milliseconds_passed;
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
                            document.timeform.timetextarea.value = minutes_passed + ":" + seconds_passed + "." + milliseconds_passed;
                    }
                    timestart = null;
            }

            function Reset() {
                    timestart = null;
                    document.timeform.timetextarea.value = "00:00";
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
            $(document).ready(function() {                        // When the HTML DOM is ready loading, then execute the following function...
                $('#acao_button').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
                    $.get('frontcontrollerajax?acao=marcar_presenca_cpf&cpf=' + $('#cpf').val() + '&idReuniao=' + $('#idReuniao').val(), function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        $('#status').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                    });
                });

                $('#start_button').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
                    $.get('frontcontrollerajax?acao=iniciar_session_reuniao' + '&idReuniao=' + $('#idReuniao').val(), function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        $('#status').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                    });
                });

                $('#stop_button').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
                    $.get('frontcontrollerajax?acao=encerrar_session_reuniao', function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        alert(responseText);
                        location.replace("Principal.jsp")
                        //$('#status').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                    });
                });

                $('a').click(function(){
                       var confirmExit = confirm('Tem certeza que deseja sair? \n A sessão de reunião será interrompida.');

                       if(confirmExit){                          
                           $.get('frontcontrollerajax?acao=encerrar_session_reuniao', function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                            return true;
                           });                          
                       }
                       else{
                            return false;
                       }
                });

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
              
                <div id="dialog-form" title="Create new user">
                    <p class="validateTips">All form fields are required.</p>

                    <form>
                        <fieldset>
                                <label for="name">Name</label>
                                <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
                                <label for="email">Email</label>
                                <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
                                <label for="password">Password</label>
                                <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
                        </fieldset>
                    </form>
                </div>
                
                <button id="create-user">Create new user</button>

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
                        
                        Tempo de Reunião: <input type=text name="timetextarea" value="00:00" size="10" style = "font-size:15px">

                        <!-- Lap:<input type=text name="laptime" size="10" style = "font-size:20px">-->
                        <input type="hidden" name="idReuniao" id="idReuniao" value="<%out.print(paginaDeReuniao != null ? paginaDeReuniao.getIdReuniao() : "");%>">
                        <div id="status">
                            
                        </div>
                        <p id="summary"/>
                        <br/>
                        <br/>
                        <input type=button id="start_button" name="start" value="Iniciar" onclick="time_start()">
                        <input type=button id="stop_button" name="stop" value="Parar" onclick="Stop()">
                        <input type=button name="reset" value="Reset" onclick="Reset()">

                    </div>

                    <div id="colunaD">
                        <h2>Marcar Presença</h2>
                        <br/>
                        <label for="cpf">CPF</label>                        
                        <input type="text" name="cpf" value="" id="cpf" size="35"/>
                        <input type="button" id="acao_button" name="acao" value="Marcar Presença"/>
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
