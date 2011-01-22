package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.aplicacao.controle.Command;

public class ActionCommandCapturarDigital implements Command{

	public Object execute(Object... arg) {
		HttpServletRequest request = (HttpServletRequest) arg[0];
		
		String mensagemResposta = "";

		ObjectInputStream objectInputStream = (ObjectInputStream) arg[2];
		ObjectOutputStream objectOutputStream = (ObjectOutputStream) arg[3];
		
		
		try {

            try {
                String digitalCapturada =  (String) objectInputStream.readObject();
              //Obtém uma session
    			HttpSession session = request.getSession();			 
    			//Armazena temporariamente na session para ser lida pelo cadastro
    			session.setAttribute("DigitalObtida", digitalCapturada);
    			//TODO teste, remover
    			System.out.println("ValorConfere = " + digitalCapturada);
    			mensagemResposta = "Digital capturada com sucesso";
    			
            } catch (IOException ex) {
                ex.printStackTrace();
                mensagemResposta = "Erro na Operacao de Leitura do Cliente";
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                mensagemResposta = "Erro de Instanciação no Objeto Cliente";
            }

            objectOutputStream.writeUTF(mensagemResposta);
            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            mensagemResposta = "Erro de IO no retorno ao cliente";
        }
		
		

		/*try {
			
			String digitalCapturada =  (String) objectInputStream.readObject();

			//Obtém uma session
			HttpSession session = request.getSession();			 
			//Armazena temporariamente na session para ser lida pelo cadastro
			session.setAttribute("DigitalObtida", digitalCapturada);
			//TODO teste, remover
			System.out.println("ValorConfere = " + digitalCapturada);
			mensagemResposta = "Digital capturada com sucesso";
		} catch (IOException e) {
			mensagemResposta = "Erro na Operacao de Leitura do Cliente";
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			mensagemResposta = "Erro de Instanciação no Objeto Cliente";
			e.printStackTrace();
		}

		try {
			objectOutputStream.writeUTF(mensagemResposta);
		} catch (IOException e) {
			mensagemResposta = "Erro de IO no retorno ao cliente";
			e.printStackTrace();
		}*/


		return mensagemResposta;
	}

}
