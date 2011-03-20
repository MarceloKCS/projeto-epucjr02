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
			long idReuniao = objectInputStream.readLong();
			String impressaoDigital = objectInputStream.readUTF();

			System.out.println("idReuniao="+idReuniao);
			System.out.println("impressaoDigital="+impressaoDigital);

			objectOutputStream.writeBoolean(true);
			objectOutputStream.writeUTF("Operação feita com sucesso");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }

}
