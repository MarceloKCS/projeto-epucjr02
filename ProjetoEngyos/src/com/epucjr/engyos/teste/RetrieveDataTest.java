package com.epucjr.engyos.teste;

import java.util.List;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class RetrieveDataTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RetrieveDataTest.obterListaDeCongregacoesTest();

	}
	
	public static void obterListaDeCongregacoesTest(){
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		
		List<Congregacao> listaDeCongregacao = dataAccessObjectManager.obterListaDeCongregacoes();
		
		System.out.println("Tamanho = " + listaDeCongregacao.size());
		
		for(Congregacao congregacao : listaDeCongregacao){
			System.out.println("Nome Congre = " + congregacao.getNome());
		}
		
	}

}
