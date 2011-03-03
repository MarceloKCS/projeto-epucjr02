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
        try {
            //long idReuniao =
            String requestData = "";
            try {
                requestData = (String) objectInputStream.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ActionMarcarPresencaDigital.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("EXCEPTION!!!!!!!!!!!!!");
                requestData = "for ass!!!";
            }

            AppletServerMessenger appletServerMessenger = new AppletServerMessenger(requestData);

            String idReuniaoString = appletServerMessenger.obterValorCampo("idReuniao");
            String impressaoDigital = appletServerMessenger.obterValorCampo("digital");
            long idReuniao = 0;
            if(idReuniaoString != null && !idReuniaoString.equals("")){
                idReuniao = Long.parseLong(idReuniaoString.trim());
            }

            System.out.println("idReuniaoString = " + idReuniaoString);
            System.out.println("impressaoDigital = " + impressaoDigital);
            System.out.println("idReuniao = " + idReuniao);

            System.out.println("Digital = " + requestData);
            
            ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl(request.getSession());

            if (reuniaoSessionControl.verificarSessaoReuniaoAberta()) {
            //IReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(idReuniao);
            //marca a presenca da reuniao pelo CPF
            //reuniaoMonitor.marcarPresencaPeloCPF(cpfObreiro);

            //Manda a resposta da operação para a página
           // resposta = reuniaoMonitor.getMensagemStatus();
                System.out.println("Sessao de reuniao aberta");

        } else {
                System.out.println("Reunião não iniciada");
            //resposta = "Reunião não iniciada";
        }
            //System.out.println("idReuniao = "+idReuniao+"; requestData = "+requestData);
            //response.getWriter().write("GET SOME");
            objectOutputStream.writeUTF("sucesso%Digital de Marcada");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "sucesso";
    }

}
