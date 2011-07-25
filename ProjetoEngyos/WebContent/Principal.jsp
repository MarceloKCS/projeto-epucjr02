<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<title>Projeto Engyos - Controle de Presença</title>
	<link href="css/screen.css" rel="stylesheet" type="text/css" />
        <link href="css/jqueryui/jquery-ui.css" type="text/css" rel="stylesheet"/>
        <script type='text/javascript' src="javascript/jquery-1.5.2.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery-ui.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery.ui.button.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery.ui.draggable.js"></script>
        <script type='text/javascript' src="javascript/jqueryui/jquery.ui.resizable.js"></script>
        <script src="javascript/jquery.alerts.js" type="text/javascript"></script>
        <link href="css/jqueryalerts/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />
        <script src="javascript/fullcalendar/fullcalendar.js" type="text/javascript"></script>
        <link href="css/fullcalendar/fullcalendar.css" type="text/css" rel="stylesheet"/>

        <style type="text/css">
            div#reuniao-dialog-form {font-size: 62.5%;}
            span#ui-dialog-title-dialog-congregacao-form{font-size: 62.5%;}
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
        </style>

        <script type="text/javascript">
            var idReuniao = "";
            $(document).ready(function() {

                var date = new Date();
                var m = date.getMonth();
                var y = date.getFullYear(); 

                // page is now ready, initialize the calendar...
                $.fullCalendar.monthNames
                $('#calendar').fullCalendar({
                    eventClick: function(calEvent, jsEvent, view) {
                        idReuniao = calEvent.id;
                        $( "#reuniao-dialog-form" ).dialog( "open" );
                        //alert('Event: ' + calEvent.title);
                        //alert('ID: ' + calEvent.id);
                        //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
                        //alert('View: ' + view.name);

                        // change the border color just for fun
                        $(this).css('border-color', 'red');

                    },
                    columnFormat: {
                            month: 'ddd',
                            week: 'ddd d/M',
                            day: 'dddd d/M'
                    },
                    timeFormat: 'H(:mm)',
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    buttonText: {
                        prev: '&nbsp;&#9668;&nbsp;',
                        next: '&nbsp;&#9658;&nbsp;',
                        prevYear: '&nbsp;&lt;&lt;&nbsp;',
                        nextYear: '&nbsp;&gt;&gt;&nbsp;',
                        today: 'hoje',
                        month: 'mes',
                        week: 'semana',
                        day: 'dia'
                    },
                    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
                    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
                    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sab'],
                    editable: true,
                    events:{
                        url: 'frontcontrollerajax?acao=ajax_get_reuniaocalendar',
                        allDay: false
                    },
                   
                    draggable: true
                    // put your options and callbacks here
                })

            });


            $(function() {
                $( "#obreiro:ui-dialog" ).dialog( "destroy" );

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
                                    updateTips( "Length of " + n + " must be between " +
                                            min + " and " + max + "." );
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

                    //Formulario de seleção de reunião em calendário
                    $( "#reuniao-dialog-form" ).dialog({
                            autoOpen: false,
                            height: 240,
                            width: 350,
                            modal: true,
                            buttons: {
                                    "Executar opção": function() {
                                        jConfirm('Confirmar operação?', 'Confirmation Dialog', function(confirmed) {
                                            if(confirmed){
                                                executarOpcao(idReuniao, $("input[name='radio']:checked").val());
                                            }
                                        });
                                         $( this ).dialog( "close" );
                                    },
                                    Cancela: function() {
                                            $( this ).dialog( "close" );
                                    }
                            },
                            close: function() {
                                    allFields.val( "" ).removeClass( "ui-state-error" );
                            }
                    });

                    function openFormulario(){
                        $( "#reuniao-dialog-form" ).dialog( "open" );
                    }

                    function executarOpcao(id, opcaoDesejada){
                        jAlert('Opcao: ' + opcaoDesejada + '\nID' + id);
                        if(opcaoDesejada == 'REUNIAO_START'){
                            location.replace("Controller?acao=iniciar_reuniao&idReuniao=" + id);
                        }
                        else if(opcaoDesejada == 'REUNIAO_EDIT'){
                            location.replace("Controller?acao=reuniao_editformload&idReuniao=" + id);
                        }
                        else if(opcaoDesejada == 'REUNIAO_DELETE'){
                            removeReuniao(id);
                        }
                    }


                    function removeReuniao(idReuniao){

                        jConfirm('Deseja realmente apagar? \nEsta operação não é reversivel após confirmação.', 'Confirmation Dialog', function(okResponse) {
                            if(okResponse){
                                $.get('frontcontrollerajax?acao=ajax_reuniao_remover&idReuniao=' + idReuniao, function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                                   // $('#status').text(responseText);
                                   jAlert(responseText , 'Confirmation Results', function(){
                                       $('#calendar').fullCalendar( 'refetchEvents' );
                                       //locate.replace("Controller?acao=page_loader&page_corrente=pagina_principal");
                                   });
                                });

                                //jAlert('Removido: ' + parametroBusca + "\ncpf: " + cpf + "\npaginaCorrente: " + paginaCorrente + "\nResultados: " + qtdResultados + "\nPaginas: " + paginas , 'Confirmation Results');
                            }
                            else{
                                jAlert('Operação Cancelada', 'Alert Dialog');
                            }
                        });
                    }

                    $("#radio-reuniao" ).buttonset();
            });
        </script>
</head>
	<body id="principal"> 
		<div id="all">
			<div id="topo">
				<%@ include file = "Topo.jsp" %>
			</div>
			<div id="menu">
				<ul id="menu_principal">
					<%@ include file = "Menu.html" %>
				</ul>
			</div>
			<div id="calendar">

                            
				<%--<div id="colunaE">(Calendário)</div>
				<div id="colunaD">
					<h2 class="h2_subtitulo proximasReunioes">Próximas Reuniões</h2>
					<img src="imagens/calendar.png" />
				</div>--%>
			</div>

                        <div id="reuniao-dialog-form" title="Reuniao">
                                    <p class="validateTips">Selecione as opções desejadas.</p>

                                    <form method="post" id="reuniao_sel_form" name="" action="#">
                                        <fieldset>
                                                <label for="radio_button">Opções: </label>
                                                <div id="radio-reuniao">
                                                    <input type="radio" id="opcao-order-nome" name="radio" checked="checked"  value="REUNIAO_START"/><label for="opcao-order-nome">Iniciar</label>
                                                    <input type="radio" id="opcao-order-congregacao" name="radio" value="REUNIAO_EDIT"/><label for="opcao-order-congregacao">Editar dados</label>
                                                    <input type="radio" id="opcao-order-cargo" name="radio" value="REUNIAO_DELETE"/><label for="opcao-order-cargo">Remover</label>
                                                </div>
                                        </fieldset>
                                    </form>
                                </div>
			<div id="rodape">
				<%@ include file = "Rodape.html" %>
			</div>
		</div>
	</body>
</html>