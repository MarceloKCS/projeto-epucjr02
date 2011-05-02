package com.epucjr.engyos.teste;

import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Identificacao;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;


public class TesteInsert {
	public static void main(String[] args) {
		
		//DataAccessObjectManager gerenciador = new DataAccessObjectManager();
		
		/*Congregacao congregacao = new Congregacao("IgrejinhaDoCalebe","Kizaemon");
		Obreiro obreiro = new Obreiro("Ricardo Akio", "Servente de Chá","379478","IgrejinhaDoCalebe");
		Reuniao reuniao = new Reuniao("12/03/2010","10:50");
		Presenca presenca = new Presenca();
		*/
		
		
		/*Congregacao congregacao2 = new Congregacao("Matriz de Cotia","Cotia");
		Obreiro obreiro2 = new Obreiro("Ze", "Pintor de lavanderia","3344","Matriz de Cotia");
		Reuniao reuniao2 = new Reuniao("14/05/2010","10:25");
		Presenca presenca2 = new Presenca();
		
		gerenciador.persistirObjeto(congregacao2);
		gerenciador.persistirObjeto(reuniao2);
		gerenciador.persistirObjeto(obreiro2);*/

		//finaliza o programa
		
		TesteInsert.testeInsertAdmin();
		
	}
	
	public static void inserirReuniao(){
		
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		Reuniao reuniao = new Reuniao("Na casa do Calebe", "31/01/2010", "00:30");
		dataAccessObjectManager.persistirObjeto(reuniao);
		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			System.out.println("SucessoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
		else{
			System.out.println("FracassoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
		
	}
	
	public static void testeCongregaInsert(){
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		Congregacao congregacao = new Congregacao("Congregação de Taboão", "Taboão da Serra");
		dataAccessObjectManager.persistirObjeto(congregacao);
		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			System.out.println("SucessoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
		else{
			System.out.println("FracassoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
	}

        public static void testeInsertAdmin(){
            DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
            Identificacao identificacao = new Identificacao();
            identificacao.setSenha("alaska");
            Administrador administrador = new Administrador("Rogério", "Maegaki", "31273800893", "rmaega@gmail.com", identificacao);

            dataAccessObjectManager.persistirObjeto(administrador);

            if (dataAccessObjectManager.isOperacaoEfetuada()) {
                System.out.println("SucessoMSG = " + dataAccessObjectManager.getMensagemStatus());
            } else {
                System.out.println("FracassoMSG = " + dataAccessObjectManager.getMensagemStatus());
            }
        }
}
