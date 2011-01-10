package com.epucjr.engyos.tecnologia.utilitarios;

public class DataUtil {

	public String obterDiaDeData(String data){
		String dia = "";
		if(data != null){
			int posicao = 0;
			while(data.charAt(posicao) != '/'){
				dia = dia + data.charAt(posicao);
				posicao++;
			}
		}
		return dia;
	}
	
	public String obterDiaDeMes(String data){
		String mes = "";
		if(data != null){
			int posicao = 0;
			while(data.charAt(posicao) != '/'){
				posicao++;
			}
			while(data.charAt(posicao) != '/'){
				mes = mes + data.charAt(posicao);
				posicao++;
			}
		}
		return mes;
	}
	
	public String obterDiaDeAno(String data){
		String ano = "";
		if(data != null){
			int posicao = 0;
			while(data.charAt(posicao) != '/'){
				posicao++;
			}
			while(data.charAt(posicao) != '/'){
				posicao++;
			}
			while(data.length() > posicao){
				ano = ano + data.charAt(posicao);
				posicao++;
			}
		}
		return ano;
	}
	
}
