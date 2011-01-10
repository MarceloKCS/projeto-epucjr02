package com.epucjr.engyos.tecnologia.utilitarios;

public class HoraUtil {
	
	public String obterHora(String horaReuniao){
		String hora = "";
		if(horaReuniao != null){
			int posicao = 0;
			while(horaReuniao.charAt(posicao) != ':'){
				hora = hora + horaReuniao.charAt(posicao);
				posicao++;
			}
		}
		return hora;
	}
	
	public String obterMinuto(String horaReuniao){
		String minuto = "";
		if(horaReuniao != null){
			int posicao = 0;
			while(horaReuniao.charAt(posicao) != ':'){
				posicao++;
			}
			while(horaReuniao.length() > posicao){
				minuto = minuto + horaReuniao.charAt(posicao);
				posicao++;
			}
		}
		return minuto;
	}

}
