package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.IReuniaoMonitor;
import com.epucjr.engyos.aplicacao.controle.ReuniaoMonitor;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ActionMarcarPresencaSenha implements Command {

	public Object execute(Object... arg) {
		return executeTeste((ObjectInputStream)arg[2], (ObjectOutputStream)arg[3]);
	}
	
	public String executeCommand(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
		try {
			long idReuniao = objectInputStream.readLong();
			String senha = objectInputStream.readUTF();
			
			IReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(idReuniao);

	        //marca a presenca da reuniao pelo CPF
	        reuniaoMonitor.marcarPresencaPeloCPF(senha);

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
	public String executeTeste(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
		try {
			long idReuniao = objectInputStream.readLong();
			String senha = objectInputStream.readUTF();
			
			System.out.println("idReuniao = "+idReuniao+"; senha = "+senha);

			objectOutputStream.writeBoolean(true);
			objectOutputStream.writeUTF("sucesso");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
