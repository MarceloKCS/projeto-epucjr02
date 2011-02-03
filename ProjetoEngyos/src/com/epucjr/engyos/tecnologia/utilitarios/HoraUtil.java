package com.epucjr.engyos.tecnologia.utilitarios;

public class HoraUtil {
	
	public static String obterHora(String horaReuniao){
		String hora = "";
		if(horaReuniao != null){
			/*int posicao = 0;
			while(horaReuniao.charAt(posicao) != ':'){
				hora = hora + horaReuniao.charAt(posicao);
				posicao++;
			}*/
			hora = ListUtilTokenizer.obterArrayString(horaReuniao,":")[0];
		}
		return hora;
	}
	
	public static String obterMinuto(String horaReuniao){
		String minuto = "";
		if(horaReuniao != null){
			/*int posicao = 0;
			while(horaReuniao.charAt(posicao) != ':'){
				posicao++;
			}
			while(horaReuniao.length() > posicao){
				minuto = minuto + horaReuniao.charAt(posicao);
				posicao++;
			}*/
			minuto = ListUtilTokenizer.obterArrayString(horaReuniao,":")[1];
		}
		return minuto;
	}
	
	public static String obterSegundo(String horaReuniao){
		String segundo = "";
		if(horaReuniao != null){
			segundo = ListUtilTokenizer.obterArrayString(horaReuniao,":")[2];
		}
		return segundo;
	}
	//Teste
	/*public static void main(String[] args) {
		String horaReuniao = "5:34";
		System.out.println("Hora:"+HoraUtil.obterHora(horaReuniao));
		System.out.println("Minuto:"+HoraUtil.obterMinuto(horaReuniao));
	}*/

}
