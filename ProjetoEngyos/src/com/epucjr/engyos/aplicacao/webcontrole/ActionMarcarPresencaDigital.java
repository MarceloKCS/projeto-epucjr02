package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ActionMarcarPresencaDigital implements Command {

	public Object execute(Object... arg) {
		return executeTeste((HttpServletRequest)arg[0], (HttpServletResponse)arg[1], (ObjectInputStream)arg[2], (ObjectOutputStream)arg[3]);
	}
	
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
		try {
			long idReuniao = objectInputStream.readLong();
			String digital = objectInputStream.readUTF();
			
			DataAccessObjectManager dao = new DataAccessObjectManager();
			Reuniao reuniao = dao.obterReuniao(idReuniao);
			
			PresencaObreiro presencaObreiro = reuniao.obterPresencaDeObreiroDaLista(digital);
			if (presencaObreiro != null) {
				//TODO Marcar presenca
				objectOutputStream.writeUTF(presencaObreiro.getObreiro().getNome());
			} else {
				objectOutputStream.writeUTF("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO apagar
	public String executeTeste(HttpServletRequest request, HttpServletResponse response, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
		try {
			long idReuniao = objectInputStream.readLong();
			String digital = objectInputStream.readUTF();
			
			System.out.println("idReuniao = "+idReuniao+"; digital = "+digital);

			objectOutputStream.writeUTF("sucesso");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
