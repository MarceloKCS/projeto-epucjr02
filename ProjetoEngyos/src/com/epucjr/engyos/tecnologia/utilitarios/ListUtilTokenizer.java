package com.epucjr.engyos.tecnologia.utilitarios;

import java.util.Arrays;
import java.util.List;

public class ListUtilTokenizer {
	
	private static final String regex =  "\t|\n|\r|\f|%|;";
	/**
	 * Obtém uma lista de nomes através de uma string passada como uma lista separada por tokens
	 * @param tokenizedString
	 * @return List<String>
	 */
	public static List<String> obterListaString(String tokenizedString){
		return Arrays.asList(obterArrayString(tokenizedString));		
	}
	
	/**
	 * Obtém uma lista de nomes através de uma string passada como um Array separada por tokens
	 * @param texto
	 * @return String[]
	 */
	public static String[] obterArrayString(String texto) {
		return texto.split(regex);
	}
	
	public static List<String> obterListaString(String tokenizedString, String regex){
		return Arrays.asList(obterArrayString(tokenizedString, regex));		
	}
	
	public static String[] obterArrayString(String texto, String regex) {
		return texto.split(regex);
	}
}
