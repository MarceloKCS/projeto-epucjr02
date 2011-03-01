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
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ActionMarcarPresencaDigital implements Command {

	public Object execute(Object... arg) {
		return executeTeste((ObjectInputStream)arg[2], (ObjectOutputStream)arg[3]);
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
            String digital = "";
            try {
                digital = (String) objectInputStream.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ActionMarcarPresencaDigital.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("EXCEPTION!!!!!!!!!!!!!");
                digital = "for ass!!!";
            }
            System.out.println("Digital = " + digital);
            //System.out.println("idReuniao = "+idReuniao+"; digital = "+digital);
            //response.getWriter().write("GET SOME");
            objectOutputStream.writeUTF("sucesso%Digital de Marcada");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "sucesso";
    }

}
