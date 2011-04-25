package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.ReuniaoSessionControl;
import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Projeto Engyos Team
 */
public class ActionReuniaoSessionStartCommand implements Command{

    @Override
    public Object execute(Object... arg) {

        HttpServletRequest request = (HttpServletRequest) arg[0];
        String resposta = "";

        //Inicia uma sess�o para a referida reuni�o com o id da reuni�o em quest�o
        ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl(request.getSession());

        String idReuniaoPagina = request.getParameter("idReuniao");
        long idReuniao = 0;

        if (idReuniaoPagina != null && !idReuniaoPagina.equals("")) {
            idReuniao = Long.parseLong(idReuniaoPagina.trim());
        }

        if (!reuniaoSessionControl.verificarSessionStatusAtiva()){
            reuniaoSessionControl.criarEDefinirDadosSessaoDeReuniao(idReuniao);
            System.out.println("CLOSED SESSION: STARTING");
            //Verifica se a sess�o foi pr�viamente iniciada, por�m apenas fechada/pausada
            //dentro do tempo l�mite
            if(reuniaoSessionControl.verificarSessaoJaPreviamenteIniciada()){
                reuniaoSessionControl.reiniciarReuniao();
                request.setAttribute("reuniaoPreviamenteIniciada", true);
                //Tipo de dados definidos: Date
                //GH Process - Nivel m�dio
                request.setAttribute("reuniaoElapsedTime", DateTimeUtils.calcularTempoDecorrido(reuniaoSessionControl.obtemTempoParcialReuniaoIniciada().getTime()));
                resposta = "Reuni�o Reiniciada";
                //Calcular o tempo decorrente do inicio da sess�o anterior para
                //inicializar do tempo de onde parou
                //Plano: dataCOrrente - dataRegistradaComoInicio (fazer a subtra��o no datetime com long)
                //e obter os tempos atuais de hora, minuto e segundo decorridos desta diferen�a, e come�ar a partir
                //deste tempo... creio que dever� dar certo, acho eu, ver um exemplo de tempo decorrido
                //Planejamento: fazer implementa��es de testes antes de por no projeto final.
            }
            else{
                 reuniaoSessionControl.iniciarReuniao();
                 resposta = "Reuni�o Iniciada";
            }
            
            

        } else {
            //Aqui tamb�m pode ser constatado que a sess�o j� foi ativada por algu�m e est� em curso
            //resta pegar o seu tempo de in�cio e trabalhar em cima...
            //Id�ias de implementa��o - apesar das se��es de reuni�o serem compartilhadas,
            //separar por usu�rios de modo a ser possivel identificar o conjunto de administradores
            //que est�o monitorando a reuni�o no dado momento
            //Verificando se foi ele que iniciou a reuniao.
            if(reuniaoSessionControl.isSessaoDeReuniaoMinha()){
                //Nada a fazer, tentei iniciar de novo o que j� era meu, foi mal :-(
                System.out.println("SESSION ALREADY OPENED");
                resposta = "Reuni�o j� iniciada";
            }
            else{
                //reuni�o j� ativa e iniciada por um colega de profiss�o, vamos pegar os seus dados
                //iniciar tudo para a minha sess�o
                reuniaoSessionControl.criarEDefinirDadosSessaoDeReuniao(idReuniao);
                reuniaoSessionControl.inicializarSessaoUsuarioCorrenteReuniaoJaIniciada();
                request.setAttribute("reuniaoPreviamenteIniciada", true);
                //Tipo de dados definidos: Date
                //GH Process - Nivel m�dio
                request.setAttribute("reuniaoElapsedTime", DateTimeUtils.calcularTempoDecorrido(reuniaoSessionControl.obtemTempoParcialReuniaoIniciada().getTime()));
                System.out.println("SESSION ALREADY OPENED");
                resposta = "Reuni�o pr�viamente iniciada";
            }

            
        }       

        return resposta;

    }
}
