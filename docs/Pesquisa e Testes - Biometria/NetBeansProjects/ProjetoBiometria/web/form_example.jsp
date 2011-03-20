<%-- 
    Document   : form_example
    Created on : 20/01/2011, 18:45:25
    Author     : Rogerio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <script language="JavaScript">

        function AbreAppletPopUp()
        {
            w=300; //largura da janela(popup)
            h=300; //altura da janela(popup)

            winl = (screen.width - w) / 2; //DEIXARÁ A JANELA(POPUP) NO CENTRO DA TELA
            wint = (screen.height - h) / 2;

            winprops = 'height='+h+',width='+w+',top='+wint+',left='+winl+',scrollbars=yes,toolbar=0,status=0, resizable=yes'; //configurações da popup

            win = window.open("applet_page.jsp","popApplet",winprops); // abre a popup
            win.focus(); //Focaliza a popup
        }

    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Página de Cadastro de Obreiro - Campo Digital</h1>

        <input type="button" onClick="AbreAppletPopUp()" value="Iniciar Applet">  
    </body>
</html>
