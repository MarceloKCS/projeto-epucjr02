<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script src="http://java.com/js/deployJava.js"></script>
        <script>
            var attributes = {
                code:       "App.Main",
                archive:    "MarcadorPresencaApplet/PresencaApplet.jar, MarcadorPresencaApplet/lib/NBioBSPJNI.jar",
                width:      350,
                height:     200
            };
            var parameters = {idReuniao:"5", jnlp_href:"MarcadorPresencaApplet/launch.jnlp"}; <!-- Applet Parameters -->
            var version = "1.5"; <!-- Required Java Version -->
            deployJava.runApplet(attributes, parameters, version);
        </script>
</body>
</html>