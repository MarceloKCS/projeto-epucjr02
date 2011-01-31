package com.epucjr.engyos.teste;

import com.epucjr.engyos.tecnologia.utilitarios.DateUtils;

public class DateUtilTest {
	
	public static void main(String[] args){
		DateUtilTest.obterDataAtual();
	}
	
	public static void obterDataAtual(){
		
		System.out.println(DateUtils.dataCorrente());
		
		System.out.println(DateUtils.obterTempoCorrente());
		
		System.out.println(DateUtils.dataCorrenteSemTempo());
		
		System.out.println(DateUtils.obterDataCorrenteBr());
		
		int compare = "12/10/2007".compareTo(DateUtils.obterDataCorrenteBr());
		
		System.out.println("ValorMenor = " + compare);
		
		compare = "12/10/2015".compareToIgnoreCase(DateUtils.obterDataCorrenteBr());
		
		System.out.println("ValorMenor = " + compare);
		
		String dataObtida = "12/10/2007";
		//for validator
		
		String diaCorrente = DateUtils.obterDiaDeDataBrasileira(DateUtils.obterDataCorrenteBr());
		String mesCorrente = DateUtils.obterMesDeDataBrasileira(DateUtils.obterDataCorrenteBr());
		String anoCorrente = DateUtils.obterAnoDeDataBrasileira(DateUtils.obterDataCorrenteBr());
		
		String diaObtido = DateUtils.obterDiaDeDataBrasileira(dataObtida);
		String mesObtido = DateUtils.obterMesDeDataBrasileira(dataObtida);
		String anoObtido = DateUtils.obterAnoDeDataBrasileira(dataObtida);
		
		int diaCorrenteInt = Integer.parseInt(diaCorrente);
		int mesCorrenteInt = Integer.parseInt(mesCorrente);
		int anoCorrenteInt = Integer.parseInt(anoCorrente);
		
		int diaObtidInto = Integer.parseInt(diaObtido);
		int mesObtidoInt = Integer.parseInt(mesObtido);
		int anoObtidoInt = Integer.parseInt(anoObtido);
		
		boolean dataValida = false;
		if(anoObtidoInt > anoCorrenteInt){
			dataValida = true;
		}
		else if((anoObtidoInt == anoCorrenteInt) && (mesObtidoInt > mesCorrenteInt)){
			dataValida = true;
		}
		else if((anoObtidoInt == anoCorrenteInt) && (mesObtidoInt == mesCorrenteInt) && (diaObtidInto > diaCorrenteInt)){
			dataValida = true;
		}
		
		if(dataValida){
			System.out.println("Tudo certo com a data inserida");
		}
		else{
			System.out.println("Data Inválida, é menor que a data corrente");
		}
		
		String horaObtida = "22:15:00";
		String horaCorrente = DateUtils.obterHora(DateUtils.obterTempoCorrente());
		String minutoCorrente = DateUtils.obterMinuto(DateUtils.obterTempoCorrente());
		String segundoCorrente = DateUtils.obterSegundo(DateUtils.obterTempoCorrente());
		
		String horaObtido = DateUtils.obterHora(horaObtida);
		String minutoObtido = DateUtils.obterMinuto(horaObtida);
		String segundoObtido = DateUtils.obterSegundo(horaObtida);
		
		int horaCorrenteInt = Integer.parseInt(horaCorrente);
		int minutoCorrenteInt = Integer.parseInt(minutoCorrente);
		int segundoCorrenteInt = Integer.parseInt(segundoCorrente);
		
		int horaObtidaInt = Integer.parseInt(horaObtido);
		int minutoObtidaInt = Integer.parseInt(minutoObtido);
		int segundoObtidaInt = Integer.parseInt(segundoObtido);
		
		System.out.println(horaCorrenteInt);
		System.out.println(minutoCorrenteInt);
		System.out.println(segundoCorrenteInt);
		System.out.println(horaObtidaInt);
		System.out.println(minutoObtidaInt);
		System.out.println(segundoObtidaInt);
		dataValida = false;
		if((anoObtidoInt == anoCorrenteInt) && (mesObtidoInt == mesCorrenteInt) && (diaObtidInto == diaCorrenteInt)){
			if(horaObtidaInt > horaCorrenteInt){
				dataValida = true;
			}
			else if((horaObtidaInt == horaCorrenteInt) && minutoObtidaInt > minutoCorrenteInt){
				dataValida = true;
			}
		}
		
		if(dataValida){
			System.out.println("Teste2 data igual e hora verificada = DATA_VALIDA");
		}
		else{
			System.out.println("Teste1 data igual e hora verificada = DATA_INVALIDA");
		}
		
		if(horaObtidaInt > horaCorrenteInt){
			dataValida = true;
		}
		else if((horaObtidaInt == horaCorrenteInt) && (minutoObtidaInt > minutoCorrenteInt)){
			dataValida = true;
		}
		
		if(dataValida){
			System.out.println("Teste3 hora verificada = DATA_VALIDA");
		}
		else{
			System.out.println("Teste1 hora verificada = DATA_INVALIDA");
		}
		
		
		
	}

}
