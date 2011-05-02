<%-- 
    Document   : index
    Created on : 16/12/2010, 12:38:04
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Test page for launching the applet via JNLP</h3>
        <script src="http://java.com/js/deployJava.js"></script>
        <script>
            var attributes = {
                code:       "App.Main",
                archive:    "PresencaApplet.jar, lib/NBioBSPJNI.jar",
                width:      350,
                height:     180
            };
            var parameters = {idReuniao:<% out.println((int)(Math.random() * 5)); %>, jnlp_href:"launch.jnlp"}; <!-- Applet Parameters -->
            var version = "1.5"; <!-- Required Java Version -->
            deployJava.runApplet(attributes, parameters, version);
        </script>
        <!-- Or use the following applet element to launch the applet using jnlp_href -->
        <!--
        <applet width="250" height="200">
            <param name="jnlp_href" value="launch.jnlp"/>
        </applet>
        -->
    </body>
</html>
