<html>
<head>
<title>Insert title here</title>
</head>
<body>
<script src="http://java.com/js/deployJava.js"></script>
        <script>
            var attributes = {
                code:       "App.Main",
                archive:    "PresencaApplet.jar, lib/NBioBSPJNI.jar",
                width:      350,
                height:     200
            };
            var parameters = {idReuniao:"5", jnlp_href:"MarcadorPresencaApplet/launch.jnlp"}; <!-- Applet Parameters -->
            var version = "1.5"; <!-- Required Java Version -->
            deployJava.runApplet(attributes, parameters, version);
        </script>
</body>
</html>