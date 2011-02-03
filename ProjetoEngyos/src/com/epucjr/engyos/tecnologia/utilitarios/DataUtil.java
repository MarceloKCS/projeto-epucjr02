package com.epucjr.engyos.tecnologia.utilitarios;

public class DataUtil {

	public static String obterDia(String data){
		String dia = "";
		if(data != null){
			/*int posicao = 0;
			while(data.charAt(posicao) != '/'){
				dia = dia + data.charAt(posicao);
				posicao++;
			}*/
			dia = ListUtilTokenizer.obterArrayString(data,"/")[0];
		}
		return dia;
	}
	
	public static String obterMes(String data){
		String mes = "";
		if(data != null){
			/*int posicao = 0;
			while(data.charAt(posicao) != '/'){
				posicao++;
			}
			while(data.charAt(posicao) != '/'){
				mes = mes + data.charAt(posicao);
				posicao++;
			}*/
			mes = ListUtilTokenizer.obterArrayString(data,"/")[1];
		}
		return mes;
	}
	
	public static String obterAno(String data){
		String ano = "";
		if(data != null){
			/*int posicao = 0;
			while(data.charAt(posicao) != '/'){
				posicao++;
			}
			while(data.charAt(posicao) != '/'){
				posicao++;
			}
			while(data.length() > posicao){
				ano = ano + data.charAt(posicao);
				posicao++;
			}*/
			ano = ListUtilTokenizer.obterArrayString(data,"/")[2];
		}
		return ano;
	}
	
}
