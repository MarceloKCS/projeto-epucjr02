package com.epucjr.engyos.teste;

import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import com.epucjr.engyos.tecnologia.utilitarios.HoraUtil;

public class DateUtilTest {
	
	public static void main(String[] args){
		//DateUtilTest.obterDataAtual();
                DateUtilTest.schedulerDateUtilTest();
	}
	
	public static void obterDataAtual(){
		
		System.out.println(DateTimeUtils.dataCorrente());
		
		System.out.println(DateTimeUtils.obterTempoCorrente());
		
		System.out.println(DateTimeUtils.dataCorrenteSemTempo());
		
		System.out.println(DateTimeUtils.obterDataCorrenteBr());
		
		int compare = "12/10/2007".compareTo(DateTimeUtils.obterDataCorrenteBr());
		
		System.out.println("ValorMenor = " + compare);
		
		compare = "12/10/2015".compareToIgnoreCase(DateTimeUtils.obterDataCorrenteBr());
		
		System.out.println("ValorMenor = " + compare);
		
		String dataObtida = "12/10/2007";
		//for validator
		
		String diaCorrente = DateTimeUtils.obterDiaDeDataBrasileira(DateTimeUtils.obterDataCorrenteBr());
		String mesCorrente = DateTimeUtils.obterMesDeDataBrasileira(DateTimeUtils.obterDataCorrenteBr());
		String anoCorrente = DateTimeUtils.obterAnoDeDataBrasileira(DateTimeUtils.obterDataCorrenteBr());
		
		String diaObtido = DateTimeUtils.obterDiaDeDataBrasileira(dataObtida);
		String mesObtido = DateTimeUtils.obterMesDeDataBrasileira(dataObtida);
		String anoObtido = DateTimeUtils.obterAnoDeDataBrasileira(dataObtida);
		
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
		String horaCorrente = DateTimeUtils.obterHora(DateTimeUtils.obterTempoCorrente());
		String minutoCorrente = DateTimeUtils.obterMinuto(DateTimeUtils.obterTempoCorrente());
		String segundoCorrente = DateTimeUtils.obterSegundo(DateTimeUtils.obterTempoCorrente());
		
		String horaObtido = DateTimeUtils.obterHora(horaObtida);
		String minutoObtido = DateTimeUtils.obterMinuto(horaObtida);
		String segundoObtido = DateTimeUtils.obterSegundo(horaObtida);
		
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

        public static void schedulerDateUtilTest(){
            HoraUtil horaUtil = new HoraUtil();

            System.out.println("Hora Marca Iniciada");
            horaUtil.ReminderBeep(10);
            System.out.println("Hora Marca Encerrada");
        }

}
