<%-- 
    Document   : SessaoReuniao
    Created on : 20/02/2011, 22:49:56
    Author     : Rogerio
--%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reuniões - Projeto Engyos - Controle de Presença</title>
	<link href="screen.css" rel="stylesheet" type="text/css" />

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
       
    </head>
    <body id="reuniao">

        <!-- Encapsula toda a página -->
        <div id="all">

            <div id="topo">
                <%@ include file = "Topo.html" %>
            </div>
            <div id="menu">
                    <%@ include file = "Menu.html" %>
            </div>

            <!-- Conteudo da página -->
            <div id="conteudo">

                <form method="post" name="timeform" action="#">

                    <label>Inicio de Raunião:</label> <input type="text" name="Inicio" value="13:00" readonly disabled>
                    <br/>
                    <br/>
                    Tempo de Reunião: <input type=text name="timetextarea" value="00:00" size="10" style = "font-size:20px">
                    <!-- Lap:<input type=text name="laptime" size="10" style = "font-size:20px">-->
                    <br/>
                    <br/>
                    <input type=button name="start" value="Iniciar" onclick="time_start()">
                    <input type=button name="stop" value="Parar" onclick="Stop()">
                    <input type=button name="reset" value="Reset" onclick="Reset()">
                </form>


            </div>
            
             <!-- Rodapé da página -->
            <div id="rodape">
		<%@ include file = "Rodape.html" %>
            </div>

        </div>
       
    </body>
</html>
