<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epucjr.engyos.dominio.modelo.Obreiro" %>
<%@ page import="com.epucjr.engyos.dominio.modelo.Congregacao" %>
<%@ page import="com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro" %>
<%@ page import="com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro" %>
<%
	FormularioDeObreiro formularioDeObreiro;
	ValidadorDeFormularioDeObreiro validadorDeObreiro;
	formularioDeObreiro = (FormularioDeObreiro) request.getAttribute("formularioDeObreiro");
	//validadorDeObreiro = (ValidadorDeFormularioDeObreiro) request.getAttribute("errorObreiro");
	validadorDeObreiro = formularioDeObreiro.getValidadorDeFormularioDeObreiro();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
            <title>Obreiros - Projeto Engyos - Controle de Presença</title>
            <%--<link href="screen.css" rel="stylesheet" type="text/css" />--%>
            <link href="css/screen.css" rel="stylesheet" type="text/css" />
            <link href="css/jqueryui/jquery-ui.css" type="text/css" rel="stylesheet"/>


            <script type='text/javascript' src="javascript/jquery-1.5.2.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery-ui.js"></script>
            <script type='text/javascript' src="javascript/jqueryui/jquery.ui.button.js"></script>
            <script type='text/javascript' src="javascript/jquery.form.js"></script>
            <script src="javascript/jquery.alerts.js" type="text/javascript"></script>
            <link href="css/jqueryalerts/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />

            <style type="text/css">
                div#dialog-congregacao-form {font-size: 62.5%;}
                span#ui-dialog-title-dialog-congregacao-form{font-size: 62.5%;}
                input.text { margin-bottom:12px; width:95%; padding: .4em; }
                fieldset { padding:0; border:0; margin-top:25px; }
                h1 { font-size: 1.2em; margin: .6em 0; }
                div#users-contain { width: 350px; margin: 20px 0; }
                div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
                div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
                .ui-dialog .ui-state-error { padding: .3em; }
                .validateTips { border: 1px solid transparent; padding: 0.3em; }
                button#create-user { font-size: 62.5%; }
                .digital_trigger{font-size: 62.5%;}
                .ui-dialog-buttonset{font-size: 62.5%;}
                .submit_obreiro {padding-top: 10px; font-size: 62.5%; }
            </style>

            <script type="text/javascript">
                    // Função para esconder a Div após alguns segundos
                    function esconderDiv() {
                            var div = document.getElementById('mensagemRetorno');
                            div.style.display = 'none';
                    }
                    // esconderá a div "mensagemRetorno" após 9 segundos.
                    window.setTimeout(esconderDiv, 9000);

                    // Função para mostrarTooltip
                    function mostrarTooltip(elemento, mensagem) {
                            var el = document.getElementById(elemento);
                            el.innerHTML = mensagem;
                    }

                    //Form JQuery
                    $(function() {

                        // a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
                        $( "#dialog:ui-dialog" ).dialog( "destroy" );

                        var name = $( "#name" ),
                                address = $( "#address" ),
                                allFields = $( [] ).add( name ).add( address ),
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
                                        updateTips( "Tamanho de " + n + " deve estar entre " +
                                                min + " e " + max + "." );
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

                        $( "#dialog-congregacao-form" ).dialog({
                                autoOpen: false,
                                height: 300,
                                width: 350,
                                modal: true,
                                buttons: {
                                        "Cadastrar Congragação": function() {
                                                var bValid = true;
                                                allFields.removeClass( "ui-state-error" );

                                                bValid = bValid && checkLength( name, "Nome", 3, 100 );
                                                bValid = bValid && checkLength( address, "Endereço", 3, 100 );

                                                bValid = bValid && checkRegexp( name, /^[a-zA-Z0-9*\s,'-]*$/i, "O nome deve consistir de a-z, 0-9, underscores, iniciar com letra." );
                                                // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
                                                bValid = bValid && checkRegexp( address, /^[a-zA-Z0-9*\s,'-]*$/i, "O nome deve consistir de a-z, 0-9, underscores, iniciar com letra." );

                                                if ( bValid ) {

                                                        submitCOngregacaoForm(name.val(), address.val());

                                                        $( this ).dialog( "close" );
                                                }
                                        },
                                        Cancelar: function() {
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
                                        $( "#dialog-congregacao-form" ).dialog( "open" );
                                });


                        $( "input:submit", ".submit_obreiro" ).button();
                        $( "input:submit", ".submit_obreiro" ).click(function() {
                            $('#formularioObreiro').submit();
                            //return true;
                        });

                        $( "input:button", ".digital_trigger" ).button();
                        $( "input:button", ".digital_trigger" ).click(function() {
                            alert("OK");
                            return true;
                        });

                        $("#Congregacao").change(function(){

                          })

                          $(document).ready(function() {
                                loadSelectCongregacao();
                          });

                    });

                    function normalRequest(){
                        var responseText = "";
                        $.get('frontcontrollerajax?acao=ajax_get_listacongregacao', function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                               // $('#status').text(responseText);
                               alert("reposta: " + responseText);
                         });
                    }

                    function loadSelectCongregacao(){
                        var congregacaoSelecionada = "";
                        <%
                        if(formularioDeObreiro != null && formularioDeObreiro.verificarCampoPreenchido("Congregacao")){
                            String congregacao = formularioDeObreiro.obterCampoPreenchido("Congregacao").trim();
                        %>
                                congregacaoSelecionada="<% out.print(congregacao); %>";
                        <% } %>
                        $.getJSON("frontcontrollerajax?acao=ajax_get_listacongregacao", {ajax: 'true'}, function(data){
                           var options = '<option value="">SELECIONE</option>';
                           for (var i = 0; i < data.length; i++) {
                               if(congregacaoSelecionada == data[i].nome){
                                   options += '<option selected="selected" value="' + data[i].idCongregacao + '">' + data[i].nome + '</option>';
                               }
                               else{
                                   options += '<option value="' + data[i].idCongregacao + '">' + data[i].nome + '</option>';
                               }

                           }
                           if(data.length > 0 ){
                               var hidden_field = '<input type="hidden" value="' + data[0].nome +  '" name="' + data[0].idCongregacao + '">';

                               for (var i = 1; i < data.length; i++) {
                                   hidden_field += '<input type="hidden" value="' + data[i].nome +  '" name="' + data[i].idCongregacao + '">';
                               }
                               $("div#Congregacao_data").html(hidden_field);
                           }

                           $("select#Congregacao").html(options);

                        })
                    }

                    function submitCOngregacaoForm(nome, endereco){
                       var responseText = "";
                        $.get('frontcontrollerajax?acao=congregacao_register&Nome=' + nome + "&Endereco=" + endereco, function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                               // $('#status').text(responseText);
                               if(responseText == "Operação Realizada com Sucesso!"){
                                   alert(responseText);
                               }

                               loadSelectCongregacao();

                         });
                    }


                  function inserirDigital(){
                      document.digital_applet.triggerDigitalInsert();
                  }

                  function emitirAvisoApplet(mensagem){
                      jAlert(mensagem);
                  }

            </script>

            <script type="text/javascript">

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

    </head>
    <body id="obreiros">
        <div id="all">
                <div id="topo">
                        <%@ include file = "Topo.jsp" %>
                </div>
                <div id="menu">
                        <%@ include file = "Menu.html" %>
                </div>
                <div id="conteudo">
                        <ul id="tabmenu">
                                <li><a href="#">Edição</a></li>
                        </ul>
                        <div id="conteudo_tabmenu">
                            <div id="mensagemRetorno">
                                    <%
                                        if (formularioDeObreiro.getMensagemStatus() != null && !formularioDeObreiro.getMensagemStatus().equals("")){
                                                out.println(formularioDeObreiro.getMensagemStatus() + "<br />");
                                                out.println("<script type=\"text/javascript\">document.getElementById('mensagemRetorno').style.display = 'block';</script>");
                                                if(formularioDeObreiro.verificarDadoDeConfirmacao("confirmacao_edicao")){
                                                        out.println("Foram alterados no banco de dados <br />");
                                                        out.println("Nome: " + formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_nome") + "<br />");
                                                        out.println("Cargo: " + formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_cargo") + "<br />");
                                                        out.println("CPF: "+ formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_cpf") + "<br />");
                                                        out.println("Congregação: " + formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_congregacao") + "<br />");
                                                        out.println("Condição Senha: " + formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_senha") + "<br />");
                                                        out.println("Condição Impressão Digital: " + formularioDeObreiro.obterDadoDeConfirmacao("confirmacao_digital"));
                                                }
                                        }
                                    %>
                            </div>


                        <form name="formularioObreiro" method="post" action="Controller?acao=obreiro_editer" id="formularioObreiro">
                                <p>
                                    <label for="Nome">Nome: </label>
                                    <input type="text" name="Nome" value="<%
                                        if (formularioDeObreiro != null
                                                && formularioDeObreiro.verificarCampoPreenchido("Nome")) {
                                        out.print(formularioDeObreiro.obterCampoPreenchido("Nome").trim());
                                        }
                                    %>" id="Nome" />
                                    <span class="erroCampoFormulario">
                                            <%
                                                    if (validadorDeObreiro != null
                                                                    && validadorDeObreiro.verificarCampoComErro("Nome")) {
                                                            out.print(validadorDeObreiro.obterCampoComErro("Nome"));
                                                    }
                                            %>
                                    </span>
                                </p>
                                <p>
                                    <label for="Cargo">Cargo: </label>
                                    <input type="text" name="Cargo" value="<%
                                        if (formularioDeObreiro != null
                                                && formularioDeObreiro.verificarCampoPreenchido("Cargo")) {
                                        out.print(formularioDeObreiro.obterCampoPreenchido("Cargo").trim());
                                        }
                                    %>"id="Cargo" />
                                    <span class="erroCampoFormulario">
                                            <%
                                                    if (validadorDeObreiro != null
                                                                    && validadorDeObreiro.verificarCampoComErro("Cargo")) {
                                                            out.print(validadorDeObreiro.obterCampoComErro("Cargo"));
                                                    }
                                            %>
                                    </span>
                                </p>
                                <p>
                                    <label for="Cpf">CPF: </label>
                                    <input type="text" name="CpfVisual" maxlength="11" disabled="disabled" readonly value="<%
                                        if (formularioDeObreiro != null
                                                && formularioDeObreiro.verificarCampoPreenchido("Cpf")) {
                                        out.print(formularioDeObreiro.obterCampoPreenchido("Cpf").trim());
                                        }
                                    %>" id="Cpf" />
                                    <input type="hidden" name="Cpf" value="<%if (formularioDeObreiro != null && formularioDeObreiro.verificarCampoPreenchido("Cpf")){ out.print(formularioDeObreiro.obterCampoPreenchido("Cpf")); }%>">

                                    <!-- onfocus="mostrarTooltip('toolTip_cpf', 'Somente números.');" onblur="mostrarTooltip('toolTip_cpf', '');"/> -->

                                    <span class="erroCampoFormulario">
                                    <span id="toolTip_cpf"></span>
                                    <%
                                        if (validadorDeObreiro != null
                                                        && validadorDeObreiro.verificarCampoComErro("Cpf")) {
                                                out.print(validadorDeObreiro.obterCampoComErro("Cpf"));
                                        }
                                    %>
                                    </span>
                                </p>


                                <p>
                                        <label for="Senha">Senha: </label>
                                        <input type="text" name="Senha" id="Senha" />
                                        <span class="erroCampoFormulario">
                                                <%
                                                        if (validadorDeObreiro != null
                                                                        && validadorDeObreiro.verificarCampoComErro("Senha")) {
                                                                out.print(validadorDeObreiro.obterCampoComErro("Senha"));
                                                        }

                                                %>
                                        </span>
                                        <span class="erroCampoFormulario">
                                                <%
                                                        if (validadorDeObreiro != null
                                                                        && validadorDeObreiro.verificarCampoComErro("SenhaMatch")) {
                                                                out.print(validadorDeObreiro.obterCampoComErro("SenhaMatch"));
                                                        }

                                                %>
                                        </span>
                                </p>

                                <p>
                                        <label for="SenhaConfirmacao">Confirmação da Senha: </label>
                                        <input type="text" name="SenhaConfirmacao" id="SenhaConfirmacao" />
                                        <span class="erroCampoFormulario">
                                        <%
                                            if (validadorDeObreiro != null
                                                            && validadorDeObreiro.verificarCampoComErro("SenhaConfirmacao")) {
                                                    out.print(validadorDeObreiro.obterCampoComErro("SenhaConfirmacao"));
                                            }

                                        %>
                                        </span>
                                </p>
                                <p>
                                    <label for="Digital">Digital: </label>
                                    <%--<input type="button" name="digital" onClick="AbreAppletPopUp()" id="digital" value="InserirDigital"/>--%>

                                    <div class="digital_trigger">
                                        <input type="button" name="digital" id="digital_trigger" value="InserirDigital"/>
                                    </div>

                                    <object id="digital_applet" height="1" width="1" code="epucjr.engyos.applet.CadastroDigitalApplet" mayscript="true" java_version="1.4+" type="application/x-java-applet" name="jsap">
                                        <param value="CadastroDigitalObreiroApplet.jar, lib/NBioBSPJNI.jar" name="archive">
                                        <param value="application/x-java-applet" name="type">
                                        <param value="1.4+" name="java_version">
                                        <param value="epucjr.engyos.applet.CadastroDigitalApplet" name="code">
                                        <param value="-Djava.security.policy=applet.policy" name="JVM_PARAM">
                                        <param value="true" name="mayscript">
                                        <param value="true" name="scriptable">
                                        <param value="digital_applet" name="name">
                                        <param value="SWPCQHXC" name="idCookie">
                                    </object>

                                </p>

                                <p>
                                    <label for="Congregacao">Congregação</label>
                                    <select name="Congregacao" id="Congregacao">

                                            <option value=""></option>

                                    </select>

                                    <!-- Exibe erros relacionado ao preenchimento da congregação -->
                                    <span class="erroCampoFormulario">
                                    <%
                                            if (validadorDeObreiro != null
                                                            && validadorDeObreiro.verificarCampoComErro("Congregacao")) {
                                                    out.print(validadorDeObreiro.obterCampoComErro("Congregacao"));
                                            }
                                    %>
                                    </span>
                                </p>

                                <div id="Congregacao_data">

                                </div>

                                <div id="dialog-congregacao-form" title="Adicionar nova congregação">
                                    <p class="validateTips">Todos os campos tem preenchimento obrigatório.</p>

                                    <form method="post" name="congregacao_addin_obreiro" id="congregacao_addin_obreiro" action="frontcontrollerajax">
                                        <fieldset>
                                                <label for="name">Nome</label>
                                                <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
                                                <label for="address">Endereço</label>
                                                <input type="text" name="address" id="address" value="" class="text ui-widget-content ui-corner-all" />
                                        </fieldset>
                                        <input type="hidden" id="action_value" name="acao" value="congregacao_register"/>
                                    </form>
                                </div>

                                <button id="create-user">Adicionar congregação</button>

                                <div class="submit_obreiro">
                                    <input type="submit" id="submit_obreiro" name="botao_action" value="Editar Obreiro"/>
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