package com.epucjr.engyos.teste;

import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class TesteEditar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataAccessObjectManager gerenciador = new DataAccessObjectManager();
		Obreiro obreiro = new Obreiro();
		obreiro = gerenciador.obterObreiro("3344");
		obreiro.setNome("Caio");
		obreiro.setCargo("KOBAYASHIDUMB");
		gerenciador.persistirObjeto(obreiro);

	}

}
