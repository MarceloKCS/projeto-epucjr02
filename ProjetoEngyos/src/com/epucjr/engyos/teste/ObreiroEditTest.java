package com.epucjr.engyos.teste;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ObreiroEditTest {
	
	public static void main(String[] args){
		//ObreiroEditTest.carregatDadosObeiro();
		ObreiroEditTest.carregaEditaDadosObeiro();
		
	}
	
	public static void carregatDadosObeiro(){
		String cpfOreiro = "31273800893";
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		Obreiro obreiro = dataAccessObjectManager.obterObreiro(cpfOreiro);
		Congregacao congregacaoObreiro = obreiro.getCongregacao();
		
		System.out.println("Dados de Obreiro...");
		System.out.println("Nome: " + obreiro.getNome());
		System.out.println("Congregacao: " + congregacaoObreiro.getNome());
		
		System.out.println("Dados carregados....");
		
		System.exit(0);		
	}
	
	public static void carregaEditaDadosObeiro(){
		String cpfOreiro = "31273800893";
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		Obreiro obreiro = dataAccessObjectManager.obterObreiro(cpfOreiro);
		Congregacao congregacaoObreiro = obreiro.getCongregacao();
		//Identificacao identificacao = obreiro.getIdentificacao();
		//obreiro.setCargo("Programador Delphi");
		
		dataAccessObjectManager.mergeDataObjeto(obreiro);
		
		System.out.println("Dados de Obreiro...");
		System.out.println("Nome: " + obreiro.getNome());
		System.out.println("Congregacao: " + congregacaoObreiro.getEndereco());
		//System.out.println("Identificacao = " + identificacao.getSenha());
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			System.out.println("Dados editados....");
			System.out.println("MSGStatus = " + dataAccessObjectManager.getMensagemStatus());
		}
		else{
			System.out.println("MSGStatus = " + dataAccessObjectManager.getMensagemStatus());
		}
		
		
		System.exit(0);		
	}

}
