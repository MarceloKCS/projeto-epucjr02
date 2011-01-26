package com.epucjr.engyos.tecnologia.utilitarios;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ListUtilTokenizer {
	
	/**
	 * Obtém uma lista de nomes através de uma string passada como uma lista separada por tokens
	 * @param tokenizedString
	 * @return
	 */
	public static List<String> obterListaString(String tokenizedString){
		String separadores;
		separadores = "\t\n\r\f"+ "|" + "%" + ";";
		List<String> listaString = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(tokenizedString, separadores);
		
		while(st.hasMoreTokens()){
			listaString.add(st.nextToken());
		}
		
		return listaString;		
	}

}
