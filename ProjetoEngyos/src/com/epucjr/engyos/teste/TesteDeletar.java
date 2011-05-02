package com.epucjr.engyos.teste;

import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class TesteDeletar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DataAccessObjectManager gerenciador = new DataAccessObjectManager();
		Obreiro obreiro = new Obreiro();
		obreiro = gerenciador.obterObreiro("3344");
		gerenciador.deletarObjeto(obreiro);
	}
}
