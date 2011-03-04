package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.IReuniaoMonitor;
import com.epucjr.engyos.aplicacao.controle.ReuniaoMonitor;
import com.epucjr.engyos.aplicacao.controle.ReuniaoSessionControl;
import com.epucjr.engyos.tecnologia.utilitarios.AppletServerMessenger;

public class ActionMarcarPresencaDigital implements Command {

	public Object execute(Object... arg) {
		return executeTeste((HttpServletRequest) arg[0], (HttpServletResponse) arg[1], (ObjectInputStream)arg[2], (ObjectOutputStream)arg[3]);
	}

	public String executeCommand(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
		try {
			long idReuniao = objectInputStream.readLong();
			String impressaoDigital = objectInputStream.readUTF();

			IReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(idReuniao);

	        //marca a presenca da reuniao
	        reuniaoMonitor.marcarPresencaPelaDigital(impressaoDigital);

	        //Manda a resposta da operação para a página
	        String resposta = reuniaoMonitor.getMensagemStatus();

			objectOutputStream.writeBoolean(reuniaoMonitor.isOperacaoExecutada());
			objectOutputStream.writeUTF(resposta);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	   //TODO apagar
    public String executeTeste(HttpServletRequest request, HttpServletResponse response, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        String resposta = "";
        String requestData = "";
        try {
            requestData = (String) objectInputStream.readObject();
            //Mensageiro com a função de reconhecer as mensagens no formato
            //semelhante a uma requisição GET
            AppletServerMessenger appletServerMessenger = new AppletServerMessenger(requestData);

            //Controlador da sessão de reunião
            ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl(request.getSession());


            //O applet envia 2 campos necessário para o registro de uma digital
            String idReuniaoString = appletServerMessenger.obterValorCampo("idReuniao");
            String impressaoDigital = appletServerMessenger.obterValorCampo("digital");
            long idReuniao = 0;
            if (idReuniaoString != null && !idReuniaoString.equals("")) {
                System.out.println("idReuniao = " + idReuniao);
                idReuniao = Long.parseLong(idReuniaoString.trim());
            }

            System.out.println("idReuniaoString = " + idReuniaoString);
            System.out.println("impressaoDigital = " + impressaoDigital);
            System.out.println("idReuniao = " + idReuniao);

            System.out.println("Digital = " + requestData);


            if (reuniaoSessionControl.verificarSessaoReuniaoAberta()) {
                IReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(idReuniao);
                //marca a presenca da reuniao pela digital
                reuniaoMonitor.marcarPresencaPelaDigital(impressaoDigital);

                //Manda a resposta da operação para a página
                resposta = reuniaoMonitor.getMensagemStatus();

            //Armazena a mensagem no formato adequando no envio para applet
            appletServerMessenger.setParameterGET("mensagemStatus", (reuniaoMonitor != null && reuniaoMonitor.isOperacaoExecutada()) ? "sucesso" : "fracasso");

            } else {
                resposta = "Reunião não iniciada";
            }


            appletServerMessenger.setParameterGET("resposta", resposta);

            System.out.println("MESSAGE = " + appletServerMessenger.obterRequestMessageParameters());

             objectOutputStream.writeUTF(appletServerMessenger.obterRequestMessageParameters());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
             ex.printStackTrace();
            Logger.getLogger(ActionMarcarPresencaDigital.class.getName()).log(Level.SEVERE, null, ex);
        }



        return resposta;
    }

}
