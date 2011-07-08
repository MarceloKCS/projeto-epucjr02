<%-- 
    Document   : teste_page
    Created on : 13/06/2011, 23:38:46
    Author     : Rogerio
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" name="test_form" action="Controller">
            <p>Pagina de teste de geracao de relatório</p>
            <input type="text" name="campo_digitar" value=""/>
            <input type="hidden" name="data_field" value="mock_value"/>
            <input type="submit" name="acao" value="gerar_relatorio"/>
        </form>
    </body>
</html>
