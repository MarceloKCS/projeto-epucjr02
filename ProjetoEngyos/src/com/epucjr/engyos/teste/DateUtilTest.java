package com.epucjr.engyos.teste;

import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import com.epucjr.engyos.tecnologia.utilitarios.HoraUtil;
import java.util.Calendar;
import java.util.Date;

public class DateUtilTest {
	
	public static void main(String[] args){
		//DateUtilTest.obterDataAtual();
                //DateUtilTest.schedulerDateUtilTest();
                DateUtilTest.calculaDifirencaTempo(329000);
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

        public static void timeTest(){
            String data = "19/04/2011";
            String horario = "22:53:32";
            System.out.println("data = " + data);
            System.out.println("horario = " + horario);

            long timeInMili = DateTimeUtils.converterDateTimeToMilissegundos(data, horario);

            System.out.println("timeInMili = " + timeInMili);

            Calendar calendarOld = Calendar.getInstance();
            calendarOld.setTimeInMillis(timeInMili);

            Date dateOld = calendarOld.getTime();

            Date dateToday = new Date();

            long timeInMIliToday = dateToday.getTime();

            System.out.println("timeInMIliTodaty = " + timeInMIliToday);

            int result = dateToday.compareTo(dateOld);

            System.out.println("result = " + result);

            long diference = timeInMIliToday - timeInMili;

            System.out.println("diference = " + diference);

            if(diference > 86400000){
                System.out.println("message = maior que 24 horas");
            }
            else {
                System.out.println("OK!!!!!!!!!!!!!");
            }

            System.out.println("Vamos ver a difirença!");

            Date dateDiference = new Date();
            dateDiference.setTime(diference);

            Calendar calendarDiference = Calendar.getInstance();
            calendarDiference.setTime(dateDiference);

            System.out.println("Dias decorridas? : " + calendarDiference.get(Calendar.DAY_OF_MONTH));
            System.out.println("Horas decorridas? : " + calendarDiference.get(Calendar.HOUR_OF_DAY));

            long secondInMillis = 1000;
            long minuteInMillis = secondInMillis * 60;
            long hourInMillis = minuteInMillis * 60;
            long dayInMillis = hourInMillis * 24;
            long yearInMillis = dayInMillis * 365;

            long elapsedYears = diference / yearInMillis;
            diference = diference % yearInMillis;
            long elapsedDays = diference / dayInMillis;
            diference = diference % dayInMillis;
            long elapsedHours = diference / hourInMillis;
            diference = diference % hourInMillis;
            long elapsedMinutes = diference / minuteInMillis;
            diference = diference % minuteInMillis;
            long elapsedSeconds = diference / secondInMillis;

            System.out.println("elapsedYears = " + elapsedYears);
            System.out.println("elapsedDays = " + elapsedDays);
            System.out.println("elapsedHours = " + elapsedHours);
            System.out.println("elapsedMinutes = " + elapsedMinutes);
            System.out.println("elapsedSeconds = " + elapsedSeconds);
            



        }

        public static void calculaDifirencaTempo(long diference){
            long secondInMillis = 1000;
            long minuteInMillis = secondInMillis * 60;
            long hourInMillis = minuteInMillis * 60;
            long dayInMillis = hourInMillis * 24;
            long yearInMillis = dayInMillis * 365;

            long elapsedYears = diference / yearInMillis;
            diference = diference % yearInMillis;
            long elapsedDays = diference / dayInMillis;
            diference = diference % dayInMillis;
            long elapsedHours = diference / hourInMillis;
            diference = diference % hourInMillis;
            long elapsedMinutes = diference / minuteInMillis;
            diference = diference % minuteInMillis;
            long elapsedSeconds = diference / secondInMillis;

            System.out.println("elapsedYears = " + elapsedYears);
            System.out.println("elapsedDays = " + elapsedDays);
            System.out.println("elapsedHours = " + elapsedHours);
            System.out.println("elapsedMinutes = " + elapsedMinutes);
            System.out.println("elapsedSeconds = " + elapsedSeconds);
        }

        public static void testGetElapsed(){
            String data = "22/04/2011";
            String horario = "13:00:00";
            System.out.println("data = " + data);
            System.out.println("horario = " + horario);

            long timeInMili = DateTimeUtils.converterDateTimeToMilissegundos(data, horario);
            //Calendar calendarOld = Calendar.getInstance();
            //calendarOld.setTimeInMillis(timeInMili);

            //Date dateOld = calendarOld.getTime();

            //Date dateToday = new Date();

            //long timeInMIliToday = dateToday.getTime();

            //long diference = timeInMIliToday - timeInMili;

             Date dateDiference = DateTimeUtils.calcularTempoDecorrido(timeInMili);


            System.out.println("elapsedYears = " + DateTimeUtils.getElapsed(dateDiference.getTime(), DateTimeUtils.ANO));
            System.out.println("elapsedDays = " + DateTimeUtils.getElapsed(dateDiference.getTime(), DateTimeUtils.DIA));
            System.out.println("elapsedHours = " + DateTimeUtils.getElapsed(dateDiference.getTime(), DateTimeUtils.HORA));
            System.out.println("elapsedMinutes = " + DateTimeUtils.getElapsed(dateDiference.getTime(), DateTimeUtils.MINUTO));
            System.out.println("elapsedSeconds = " + DateTimeUtils.getElapsed(dateDiference.getTime(), DateTimeUtils.SEGUNDO));
        }

}
