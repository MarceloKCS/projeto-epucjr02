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

        //Inicia uma sessão para a referida reunião com o id da reunião em questão
        ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl(request.getSession());

        String idReuniaoPagina = request.getParameter("idReuniao");
        long idReuniao = 0;

        if (idReuniaoPagina != null && !idReuniaoPagina.equals("")) {
            idReuniao = Long.parseLong(idReuniaoPagina.trim());
        }

        if (!reuniaoSessionControl.verificarSessionStatusAtiva()){
            reuniaoSessionControl.criarEDefinirDadosSessaoDeReuniao(idReuniao);
            System.out.println("CLOSED SESSION: STARTING");
            //Verifica se a sessão foi préviamente iniciada, porém apenas fechada/pausada
            //dentro do tempo límite
            if(reuniaoSessionControl.verificarSessaoJaPreviamenteIniciada()){
                reuniaoSessionControl.reiniciarReuniao();
                request.setAttribute("reuniaoPreviamenteIniciada", true);
                //Tipo de dados definidos: Date
                //GH Process - Nivel médio
                request.setAttribute("reuniaoElapsedTime", DateTimeUtils.calcularTempoDecorrido(reuniaoSessionControl.obtemTempoParcialReuniaoIniciada().getTime()));
                resposta = "Reunião Reiniciada";
                //Calcular o tempo decorrente do inicio da sessão anterior para
                //inicializar do tempo de onde parou
                //Plano: dataCOrrente - dataRegistradaComoInicio (fazer a subtração no datetime com long)
                //e obter os tempos atuais de hora, minuto e segundo decorridos desta diferença, e começar a partir
                //deste tempo... creio que deverá dar certo, acho eu, ver um exemplo de tempo decorrido
                //Planejamento: fazer implementações de testes antes de por no projeto final.
            }
            else{
                 reuniaoSessionControl.iniciarReuniao();
                 resposta = "Reunião Iniciada";
            }
            
            

        } else {
            //Aqui também pode ser constatado que a sessão já foi ativada por alguém e está em curso
            //resta pegar o seu tempo de início e trabalhar em cima...
            //Idéias de implementação - apesar das seções de reunião serem compartilhadas,
            //separar por usuários de modo a ser possivel identificar o conjunto de administradores
            //que estão monitorando a reunião no dado momento
            //Verificando se foi ele que iniciou a reuniao.
            if(reuniaoSessionControl.isSessaoDeReuniaoMinha()){
                //Nada a fazer, tentei iniciar de novo o que já era meu, foi mal :-(
                System.out.println("SESSION ALREADY OPENED");
                resposta = "Reunião já iniciada";
            }
            else{
                //reunião já ativa e iniciada por um colega de profissão, vamos pegar os seus dados
                //iniciar tudo para a minha sessão
                reuniaoSessionControl.criarEDefinirDadosSessaoDeReuniao(idReuniao);
                reuniaoSessionControl.inicializarSessaoUsuarioCorrenteReuniaoJaIniciada();
                request.setAttribute("reuniaoPreviamenteIniciada", true);
                //Tipo de dados definidos: Date
                //GH Process - Nivel médio
                request.setAttribute("reuniaoElapsedTime", DateTimeUtils.calcularTempoDecorrido(reuniaoSessionControl.obtemTempoParcialReuniaoIniciada().getTime()));
                System.out.println("SESSION ALREADY OPENED");
                resposta = "Reunião préviamente iniciada";
            }

            
        }       

        return resposta;

    }
}
