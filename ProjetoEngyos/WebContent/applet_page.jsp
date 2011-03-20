<%-- 
    Document   : applet_page
    Created on : 20/01/2011, 18:47:33
    Author     : Rogerio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
         <script language="JavaScript">
       
		     // (C) 2001 www.CodeLifter.com
		     // http://www.codelifter.com
		     // Free for all users, but leave in this header
		
		     var howLong = 10000;
		
		     t = null;
		     function closeMe(){
		     	t = setTimeout("self.close()",howLong);
		     }

    
        </script>
        
    </head>
    <body onload="closeMe();self.focus()">
        <h1>Insira a digital!</h1>
        <script src="http://java.com/js/deployJava.js"></script>
        <script>
            var attributes = {
                code:       "epucjr.engyos.applet.CadastroDigital",
                archive:    "CadastroDigitalObreiroApplet.jar, lib/NBioBSPJNI.jar",
                width:      200,
                height:     250
            };
            var parameters = {idReuniao:<% out.println((int)(Math.random() * 5)); %>, jnlp_href:"launch.jnlp"}; <!-- Applet Parameters -->
            var version = "1.5"; <!-- Required Java Version -->
            deployJava.runApplet(attributes, parameters, version);
        </script>
        
       
    </body>
</html>
