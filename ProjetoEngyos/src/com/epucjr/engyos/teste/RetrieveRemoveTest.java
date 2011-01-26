package com.epucjr.engyos.teste;

import java.util.List;

import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Presenca;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class RetrieveRemoveTest {
	
	public static void main(String[] args){
		//RetrieveRemoveTest.removerDadosPorUpdate();
		RetrieveRemoveTest.removerDadosPresencaPorUpdate();
		System.exit(0);
	}
	
	public static void removerDadosPorUpdate(){
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		Reuniao reuniao = dataAccessObjectManager.obterReuniao(1);
		reuniao.setListaDePresenca(new Presenca());
		
		dataAccessObjectManager.mergeDataObjeto(reuniao);
		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			System.out.println("MSGSucesso = " + dataAccessObjectManager.getMensagemStatus());
		}
		else{
			System.out.println("MSGFracasso = " + dataAccessObjectManager.getMensagemStatus());
			
		}
	}
	
	public static void removerDadosPresencaPorUpdate(){
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		Presenca presenca = (Presenca) dataAccessObjectManager.obterPresenca(1);
		List<Obreiro> listaDeObreiro = presenca.getObreirosDaListaDePresenca();
		
		for(Obreiro obreiro : listaDeObreiro){
			System.out.println("Nome = " + obreiro.getNome());
		}
		
		//presenca.setListaDePresenca(new Presenca());
		
		dataAccessObjectManager.deletarObjeto(presenca);
		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			System.out.println("MSGSucesso = " + dataAccessObjectManager.getMensagemStatus());
		}
		else{
			System.out.println("MSGFracasso = " + dataAccessObjectManager.getMensagemStatus());
			
		}
	}

}
