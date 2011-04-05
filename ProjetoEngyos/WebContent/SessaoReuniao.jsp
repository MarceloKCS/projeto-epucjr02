<%-- 
    Document   : SessaoReuniao
    Created on : 20/02/2011, 22:49:56
    Author     : Rogerio
--%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.PaginaDeReuniao" %>
<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>

<%
PaginaDeReuniao paginaDeReuniao = (PaginaDeReuniao) request.getAttribute("paginaDeReuniao");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reuniões - Projeto Engyos - Controle de Presença</title>
	<link href="screen.css" rel="stylesheet" type="text/css" />
        <script type='text/javascript' src="javascript/jquery-1.5.1.min.js"></script>
        <script type='text/javascript' src="javascript/deployJava.js"></script>
        <script type="text/javascript"> 
            
            var timercount = 0;
            var timestart  = null;

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
                    document.timeform.timetextarea.value = "00:00";
                    //document.timeform.laptime.value = "";
                    timercount  = setTimeout("showtimer()", 1000);
                 }
            }

            function sw_start(){
                    if(!timercount){
                    timestart   = new Date();
                    document.timeform.timetextarea.value = "00:00";
                    document.timeform.laptime.value = "";
                    timercount  = setTimeout("showtimer()", 1000);
                    }
                    else{
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
                            document.timeform.laptime.value = minutes_passed + ":" + seconds_passed + "." + milliseconds_passed;
                    }
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
            });
        </script>
    </head>
    <body id="reuniao">

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
