<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Reuniao" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao" %>
<%
	FormularioDeReuniao formularioDeReuniao;
	ValidadorDeFormularioDeReuniao validadorDeReuniao;
	formularioDeReuniao = (FormularioDeReuniao) request.getAttribute("formularioDeReuniao");
	validadorDeReuniao = formularioDeReuniao.getValidadorDeFormularioDeReuniao();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <title>Reuniões - Projeto Engyos - Controle de Presença</title>
        <link href="css/screen.css" rel="stylesheet" type="text/css" />
        <link href="css/jqueryui/jquery-ui.css" type="text/css" rel="stylesheet"/>

        <script type='text/javascript' src="javascript/jquery-1.5.2.js"></script>
        <%--<script type='text/javascript' src="javascript/jquery.transfer.js"></script>--%>
        <script type='text/javascript' src="javascript/jqueryui/jquery-ui.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery.ui.button.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery.ui.datepicker.js"></script>
        <script type='text/javascript' src="javascript/OptionTransfer.js"></script>
        <script type='text/javascript' src="javascript/selectbox.js"></script>
        <style type="text/css">
            #ui-datepicker-div{font-size: 62.5%;}
            .submit_reuniao {padding-top: 10px; font-size: 62.5%; }

            /*Plugin classes*/
            .hidden{display:none;}
            .added{color:#CCCCCC;}
            /*End*/

            a.button {
                display: block;
                border: 1px solid #aaa;
                text-decoration: none;
                background-color: #fafafa;
                color: #123456;
                margin: 2px;
                clear:both;
            }

            div#tabela_obreiros input.button {
                display: block;
                border: 1px solid #aaa;
                text-decoration: none;
                background-color: #fafafa;
                color: #123456;
                margin: 2px;
                width: 200px;
                clear:both;
            }

            div#tabela_obreiros select {
                width: 200px;
                height: 80px;
            }

            .align-lt {
                float:left;
                text-align: center;
                margin: 0px;
            }

            .tabela_obreiros {
                width: 500px;
                height: 150px;
            }
         </style>

        <script type="text/javascript">

            $(document).ready(function() {
                //carregarListaDeObreiros();
                /*
                $('#lista_obreiros1').transfer({
                    to:'#lista_obreiros2',//selector of second multiple select box
                    searchId:'#search',//id of search text, provide only if you want to enable searching
                    dblClick:true,//true or false, activates double click transfer
                    searchText:'{count} options matching {keyword}',
                    searchTextId:'#searchText',//id to show search message, ex- matching 2 records
                    addId:'#addT',//add buttong id
                    removeId:'#removeT', // remove button id
                    addAllId:'#addAllT', // add all button id
                    removeAllId:'#removeAllT',// remove all button id
                    tClass:'added'//class to be added when option tranfered to another select.
                });
                */
            });
         </script>

         <script type="text/javascript">

            $(function() {
                $( "#dataReuniao" ).datepicker({
                    dateFormat: 'dd/mm/yy',
                    showOn: "button",
                    buttonImage: "./imagens/calendar_icon.png",
                    buttonImageOnly: true,
                    changeMonth: true,
                    changeYear: true
                });


                $( "input:submit", ".submit_reuniao" ).button();
                $( "input:submit", ".submit_reuniao" ).click(function() {
                    obterObreirosSelecionados();
                    $('#formularioReuniao').submit();
                    //return true;
                });
             });
             /*
             function carregarListaDeObreiros(){
                 $.getJSON("frontcontrollerajax?acao=ajax_get_listaobreiro", {ajax: 'true'}, function(data){
                   var options = '';
                   for (var i = 0; i < data.length; i++) {
                       options += '<option value="' + data[i].cpf + '">' + data[i].nome + '</option>';
                   }

                   $("select#lista_obreiros1").html(options);
                });
             }
             */
             // Função para esconder a Div após alguns segundos
            function esconderDiv() {
                    var div = document.getElementById('mensagemRetorno');
                    div.style.display = 'none';
            }
            // esconderá a div "mensagemRetorno" após 9 segundos.
            window.setTimeout(esconderDiv, 5000);

            function obterObreirosSelecionados(){
                    var campoObreiros2 = document.getElementById('lista_obreiros2').value;
                    if(document.getElementById('lista_obreiros2').options.length != 0){
                            //Obter os obreiros incluidos no processo
                            var listaDeObreiros = document.getElementById("lista_obreiros2").options[0].value;
                            for(var i=1;i<document.getElementById("lista_obreiros2").options.length;i++) {
                                    var opcao = document.getElementById("lista_obreiros2").options[i];
                                    listaDeObreiros = listaDeObreiros + "%" + opcao.value;
                            }
                            document.getElementById("obreiros").value = listaDeObreiros;
                    }
            }

        </script>

    </head>
    <body id="reunioes">
        <div id="all">
                <div id="topo">
                        <%@ include file = "Topo.jsp" %>
                </div>
                <div id="menu">
                        <%@ include file = "Menu.html" %>
                </div>
                <div id="conteudo">
                        <ul id="tabmenu">
                                <li><a href="#">Cadastro</a></li>
                        </ul>
                        <div id="conteudo_tabmenu">
                        <div id="mensagemRetorno">
                        <%
                            if (formularioDeReuniao.getMensagemStatus() != null && !formularioDeReuniao.getMensagemStatus().equals("")) {
                                    out.println("<script type=\"text/javascript\">document.getElementById('mensagemRetorno').style.display = 'block';</script>");
                                    out.println(formularioDeReuniao.getMensagemStatus() + "<br />");
                                    if (formularioDeReuniao.verificarDadoDeConfirmacao("confirmacao_cadastro")){
                                            out.println("<p>Foram incluidos no banco de dados</p>");
                                            out.println("Local: " + formularioDeReuniao.obterDadoDeConfirmacao("confirmacao_local") + "<br />");
                                            out.println("Data: " + formularioDeReuniao.obterDadoDeConfirmacao("confirmacao_data") + "<br />");
                                            out.println("Hora: " + formularioDeReuniao.obterDadoDeConfirmacao("confirmacao_hora") + "<br />");
                                    }
                            }

                        %>
                        </div>
                <form name="formularioReuniao" method="post" action="Controller?acao=reuniao_register" id="formularioReuniao">
                    <p>
                            <label for="Local">Local:</label>
                            <input type="text" name="local" id="local" value="<%
                                if (formularioDeReuniao != null
                                        && formularioDeReuniao.verificarCampoPreenchido("local")) {
                                    out.println(formularioDeReuniao.obterCampoPreenchido("local"));
                                }
                                else if(!formularioDeReuniao.getCongregacaoPadrao().equals("")){
                                    out.print(formularioDeReuniao.getCongregacaoPadrao());
                                }
                            %>"/>
                            <span class="erroCampoFormulario">
                                    <%
                                            if (validadorDeReuniao != null
                                                            && validadorDeReuniao.verificarCampoComErro("local")) {
                                                    out.println(validadorDeReuniao.obterCampoComErro("local"));
                                            }
                                    %>
                            </span>
                    </p>
                    <p>
                        <label for="Data">Data: </label>
                        <input id="dataReuniao" type="text" size="15" name="dataReuniao" readonly="readonly" value="<%
                                if (formularioDeReuniao != null
                                        && formularioDeReuniao.verificarCampoPreenchido("dataReuniao")) {
                                    out.println(formularioDeReuniao.obterCampoPreenchido("dataReuniao"));
                                }
                            %>"/>

                        <span class="erroCampoFormulario">
                        <%
                                if (validadorDeReuniao != null
                                                && validadorDeReuniao.verificarCampoComErro("Data")) {
                                        out.println(validadorDeReuniao
                                                        .obterCampoComErro("Data"));
                                }
                        %>
                        </span>

                    </p>
                    <p>

                        <%
                            String zero = "";
                        %>
                            <label for="Data">Hora: </label>
                            <select name="horaReuniao">
                                    <option value="24"></option>
                                    <%
                                    if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("horaReuniao")){
                                            String hora = formularioDeReuniao.obterCampoPreenchido("horaReuniao");
                                            for(int posicao = 0; posicao < 24; posicao++){
                                                    if(posicao < 10){
                                                            zero = "0";
                                                            zero = zero + Integer.toString(posicao);
                                                    }
                                                    else{
                                                            zero = Integer.toString(posicao);
                                                    }

                                                    if(hora.equals(zero)){
                                                            out.println("<option selected=\"selected\" value=" + zero +">"+ zero +"</option>");
                                                    }
                                                    else{
                                                            out.println("<option value=\""+zero+"\">"+zero+"</option>");
                                                    }
                                            }
                                    }
                                    else{
                                            for(int posicao = 0; posicao < 24; posicao++){
                                                    if(posicao < 10){
                                                            zero = "0";
                                                            zero = zero + Integer.toString(posicao);
                                                    }
                                                    else{
                                                            zero = Integer.toString(posicao);
                                                    }
                                                    out.println("<option value=\""+zero+"\">"+zero+"</option>");
                                            }
                                    }
                                    %>
                            </select>
                            <select name="minutoReuniao">
                                    <option value="60"></option>
                                    <%
                                    if(formularioDeReuniao != null && formularioDeReuniao.verificarCampoPreenchido("minutoReuniao")){
                                            String minuto = formularioDeReuniao.obterCampoPreenchido("minutoReuniao");
                                            for(int posicao=0; posicao< 60; posicao++){
                                                    if(posicao < 10){
                                                            zero = "0";
                                                            zero = zero + posicao;
                                                    }
                                                    else{
                                                            zero = ""+posicao;
                                                    }

                                                    if(minuto.equals(zero)){
                                                            out.println("<option selected=\"selected\" value=" + zero +">"+zero+"</option>");
                                                    }
                                                    else{
                                                            out.println("<option value=\""+zero+"\">"+zero+"</option>");
                                                    }
                                            }
                                    }

                                    else{
                                            for(int posicao=0; posicao< 60; posicao++){
                                                    if(posicao <10){
                                                            zero = "0";
                                                            zero = zero + Integer.toString(posicao);
                                                    }
                                                    else{
                                                            zero = Integer.toString(posicao);
                                                    }
                                                    out.println("<option value=\""+zero+"\">"+zero+"</option>");
                                            }
                                    }
                                    %>
                            </select>



                            <span class="erroCampoFormulario">
                            <%
                                    if (validadorDeReuniao != null
                                                    && validadorDeReuniao.verificarCampoComErro("Horario")) {
                                            out.println(validadorDeReuniao
                                                            .obterCampoComErro("Horario"));
                                    }
                            %>
                            </span>

                        </p>

                        <div id="tabela_obreiros" class="tabela_obreiros">
                           <div class="align-lt">
                               <select multiple="multiple" name="obreiros1" id="lista_obreiros1" ondblclick="moveSelectedOptions(this.form['obreiros1'],this.form['obreiros2'],true,this.form['movepattern1'].value)">
                                    <%
                                        List<Obreiro> listaDeObreiros = formularioDeReuniao.getListaDeObreiros();
                                        List<String> listaDeIdDeObreirosInclusos = null;
                                        int posicaoAtualListaDeObreirosInclusos = 0;

                                        if(formularioDeReuniao.campoListaEstaPreenchido("obreiros")){
                                                listaDeIdDeObreirosInclusos = formularioDeReuniao.obterCampoListaPreenchido("obreiros");
                                                for(Obreiro obreiro : listaDeObreiros){
                                                        posicaoAtualListaDeObreirosInclusos = 0;
                                                        while(posicaoAtualListaDeObreirosInclusos < listaDeIdDeObreirosInclusos.size() && !listaDeIdDeObreirosInclusos.get(posicaoAtualListaDeObreirosInclusos).equals(obreiro.getCpf())){
                                                                posicaoAtualListaDeObreirosInclusos++;
                                                        }
                                                        if(posicaoAtualListaDeObreirosInclusos >= listaDeIdDeObreirosInclusos.size()){
                                                                out.println("<option value=\"" + obreiro.getCpf() + "\">" + obreiro.getNome() + "</option>");
                                                        }
                                                }
                                        }
                                        else{
                                                for(Obreiro obreiro : listaDeObreiros){
                                                        out.println("<option value=\"" + obreiro.getCpf() + "\">" + obreiro.getNome() + "</option>");
                                                }
                                        }
                                    %>
                                </select>
                               <input type="button" class="button" id="addT" value="adicionar >>" onclick="moveSelectedOptions(this.form['obreiros1'],this.form['obreiros2'],true,this.form['movepattern1'].value)"/>
                               <input type="button" class="button" id="addAllT" value="adicionar todos >>" onclick="moveAllOptions(this.form['obreiros1'],this.form['obreiros2'],true,this.form['movepattern1'].value)"/>
                               <%--<a id="addT" class="button" href="#">add »</a>--%>
                               <%--<a id="addAllT" class="button" href="#">add All »»</a>--%>
                            </div>
                            <div class="align-lt">
                                <select multiple="multiple" name="obreiros2" id="lista_obreiros2" ondblclick="moveSelectedOptions(this.form['obreiros2'],this.form['obreiros1'],true,this.form['movepattern1'].value)">
                                    <%
                                        List<String> listaDeIdDeObreirosIncluidos = null;

                                        if(formularioDeReuniao.campoListaEstaPreenchido("obreiros")){
                                                listaDeIdDeObreirosIncluidos = formularioDeReuniao.obterCampoListaPreenchido("obreiros");
                                                for(String cpfObreiro: listaDeIdDeObreirosIncluidos){
                                                        for(Obreiro obreiro : listaDeObreiros){
                                                                if(obreiro.getCpf().equals(cpfObreiro)){
                                                                        out.print("<option value=\"" + obreiro.getCpf() + "\">" + obreiro.getNome() + "</option>");
                                                                }
                                                        }

                                                }
                                        }
                                    %>
                                </select>
                                <input type="button" class="button" id="removeT" value="remover <<" onclick="moveSelectedOptions(this.form['obreiros2'],this.form['obreiros1'],true,this.form['movepattern1'].value)"/>
                                <input type="button" class="button" id="removeAllT" value="remover todos<<" onclick="moveAllOptions(this.form['obreiros2'],this.form['obreiros1'],true,this.form['movepattern1'].value)"/>
                                <%--<a id="removeT" class="button" href="#">remove «</a>
                                <a id="removeAllT" class="button" href="#">remove All ««</a>--%>
                            </div>
                        </div>

                        <!-- Input para capturar String de obreiros preparada para o servlet -->
                        <input name="obreiros" id="obreiros" value="" type="hidden"/>

                        <!-- Input para auxiliar o javascript "optionTransfer"-->
                        <input name="movepattern1" value="" type="hidden"/>
                        
                         <div class="submit_reuniao">
                                <input type="submit" id="submit_reuniao" name="botao_action" value="Agendar Reuniao"/>
                        </div>
                    </form>
                </div>
            </div>
            <div id="rodape">
                    <%@ include file = "Rodape.html" %>
            </div>
        </div>
    </body>
</html>