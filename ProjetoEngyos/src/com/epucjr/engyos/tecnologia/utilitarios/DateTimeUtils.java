package com.epucjr.engyos.tecnologia.utilitarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
        public static final int DIA = 1;
        public static final int ANO = 2;
        public static final int HORA = 3;
        public static final int MINUTO = 4;
        public static final int SEGUNDO = 5;

	public static final String 	DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String dataCorrente (){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT);
		return sdf.format(cal.getTime());
	}

	public static String obterTempoCorrente(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	public static String dataCorrenteSemTempo (){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	public static String obterDataCorrenteBr(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(cal.getTime());
	}

	public static String converterData(String dia, String mes, String ano){
		String data = dia + "-" + mes + "-" + ano; 
		return data;
	}
	
	public static String converterHorario(String hora, String minuto, String segundo){
		String horario = hora + ":" + minuto + ":" + segundo;
		return horario;
	}
	
	public static String converterHorarioHHMM(String hora, String minuto){
		String horario = hora + ":" + minuto + ":" + "00";
		return horario;
	}

	public static String converterDataBr(String dia, String mes, String ano){
		String data = dia + "/" + mes + "/" + ano; 
		return data;
	}

	/** Transformar uma data (String) do formato americano para o formato brasileiro.
	 * 
	 * @param dataAmericana, no formato "YYYY-MM-DD"
	 * @return a data brasileira correspondente no formato "DD/MM/YYYY"
	 */
	public static String transformarDataAmericanaParaBrasileira( String dataAmericana ){
		/*String ano = ""; 
		String mes = "";
		String dia = "";
		String dataBrasileira = "";
		int posicao = 0;
		int tamanho = dataAmericana.length();
		while(dataAmericana.charAt(posicao) != '-'){
			ano = ano + dataAmericana.charAt(posicao);
			posicao++;
		}
		posicao = posicao + 1;

		while(dataAmericana.charAt(posicao) != '-'){
			mes = mes + dataAmericana.charAt(posicao);
			posicao++;
		}
		posicao = posicao + 1;

		while( posicao < tamanho ){
			dia = dia + dataAmericana.charAt(posicao);
			posicao++;
		}
		dataBrasileira = dia + "/" + mes + "/" + ano;
		return dataBrasileira;*/
		String[] dataArray = ListUtilTokenizer.obterArrayString(dataAmericana, "-"); 
		return dataArray[2]+'/'+dataArray[1]+'/'+dataArray[0];
	}
	/** Transformar uma data (String) do formato brasileiroo para o formato americano.
	 * 
	 * @param dataBrasileira, no formato "DD/MM/YYYY"
	 * @return a data americana correspondente no formato "YYYY/MM/DD"
	 */
	public static String transformarDataBrasileiraParaAmericana( String dataBrasileira ){
		/*String dataAmericana = "";
		String ano = "";
		String mes = "";
		String dia = "";
		int posicao = 0;
		int tamanho = dataBrasileira.length();
		while(dataBrasileira.charAt(posicao) != '/'){
			dia = dia + dataBrasileira.charAt(posicao);
			posicao++;
		}
		posicao = posicao + 1;

		while(dataBrasileira.charAt(posicao) != '/'){
			mes = mes + dataBrasileira.charAt(posicao);
			posicao++;
		}
		posicao = posicao + 1;

		while( posicao < tamanho ){
			ano = ano + dataBrasileira.charAt(posicao);
			posicao++; 
		}
		dataAmericana = ano + "-" + mes + "-" + dia;

		return dataAmericana;*/
		
		String[] dataArray = ListUtilTokenizer.obterArrayString(dataBrasileira, "/"); 
		return dataArray[2]+'-'+dataArray[1]+'-'+dataArray[0];
	}    

	/** Capturar o valor de Real (String) de uma string contendo um valor total
	 * EXEMPLO: 1000,20 ---> Capturar somente 1000
	 * @param valor, 1000.00
	 * @return real, 1000"
	 */
	public static String pegarValorReal( String valor ){
		String real = "";
		//int posicao = 0;
		if( !valor.equals("")) {

			/*while(valor.charAt(posicao) != ','){
				real = real + valor.charAt(posicao);
				posicao++;
			}*/
			real = ListUtilTokenizer.obterArrayString(valor, ".,")[0]; 
		}
		return real;
	}
	public static String pegarValorCentavo( String valor ){
		String centavo = "";
		/*int posicao = 0;
		int fim = valor.length();*/
		if(valor.length() > 0) {
			/*while(valor.charAt(posicao) != ','){
				posicao++;
			}
			for(int k = posicao + 1; k < fim; k++ ){
				centavo = centavo + valor.charAt(k);
			}*/
			String[] valorArray = ListUtilTokenizer.obterArrayString(valor, ".,");
			if (valor.charAt(0) == '.' || valor.charAt(0) == ',') {
				centavo = valorArray[0];
			} else if (valorArray.length > 1) {
				centavo = valorArray[1]; 
			} else centavo = "0";
		}
		return centavo;
	}

	public static String obterDiaDeDataBrasileira(String dataBrasileira){
		/*String dia = "";
		if(!dataBrasileira.equals("")){
			int posicao = 0;			
			while(dataBrasileira.charAt(posicao) != '/'){
				dia = dia + dataBrasileira.charAt(posicao);
				posicao++;
			}
		}
		return dia;*/
		return DataUtil.obterDia(dataBrasileira);
	}

	public static String obterMesDeDataBrasileira(String dataBrasileira){
		/*String dia = "";
		String mes = "";
		if(!dataBrasileira.equals("")){
			int posicao = 0;			
			while(dataBrasileira.charAt(posicao) != '/'){
				dia = dia + dataBrasileira.charAt(posicao);
				posicao++;
			}
			posicao = posicao + 1;

			while(dataBrasileira.charAt(posicao) != '/'){
				mes = mes + dataBrasileira.charAt(posicao);
				posicao++;
			}
		}*/
		return DataUtil.obterMes(dataBrasileira);
	}


	public static String obterAnoDeDataBrasileira(String dataBrasileira){
		/*String dia = "";
		String mes = "";
		String ano = "";
		if(!dataBrasileira.equals("")){
			int posicao = 0;
			int tamanho = dataBrasileira.length();
			while(dataBrasileira.charAt(posicao) != '/'){
				dia = dia + dataBrasileira.charAt(posicao);
				posicao++;
			}
			posicao = posicao + 1;

			while(dataBrasileira.charAt(posicao) != '/'){
				mes = mes + dataBrasileira.charAt(posicao);
				posicao++;
			}
			posicao = posicao + 1;

			while( posicao < tamanho ){
				ano = ano + dataBrasileira.charAt(posicao);
				posicao++; 
			}
		}
		return ano;*/
		return DataUtil.obterAno(dataBrasileira);
	}

	public static String obterHora(String horario){
		/*String hora = "";
		if(!horario.equals("")){
			int posicao = 0;			
			while(horario.charAt(posicao) != ':'){
				hora = hora + horario.charAt(posicao);
				posicao++;
			}
		}

		return hora;*/
		return HoraUtil.obterHora(horario);
	}

	public static String obterMinuto(String horario){
		/*String hora = "";
		String minuto = "";
		if(!horario.equals("")){
			int posicao = 0;			
			while(horario.charAt(posicao) != ':'){
				hora = hora + horario.charAt(posicao);
				posicao++;
			}

			posicao = posicao + 1;

			while(horario.charAt(posicao) != ':'){
				minuto = minuto + horario.charAt(posicao);
				posicao++;
			}
		}

		return minuto;*/
		return HoraUtil.obterMinuto(horario);
	}

	public static String obterSegundo(String horario){
		/*String hora = "";
		String minuto = "";
		String segundo = "";
		if(!horario.equals("")){
			int posicao = 0;
			int tamanho = horario.length();		
			
			while(horario.charAt(posicao) != ':'){
				hora = hora + horario.charAt(posicao);
				posicao++;
			}

			posicao = posicao + 1;

			while(horario.charAt(posicao) != ':'){
				minuto = minuto + horario.charAt(posicao);
				posicao++;
			}
			
			posicao = posicao + 1;

			while( posicao < tamanho ){
				segundo = segundo + horario.charAt(posicao);
				posicao++; 
			}
		}

		return segundo;*/
		return HoraUtil.obterSegundo(horario);
	}

	public static int calcularIdade(String dataDeNascimento){
		String dataAtual = DateTimeUtils.dataCorrenteSemTempo();
		dataAtual = DateTimeUtils.transformarDataAmericanaParaBrasileira(dataAtual);

		int diaAtual = Integer.parseInt(DateTimeUtils.obterDiaDeDataBrasileira(dataAtual));
		int mesAtual = Integer.parseInt(DateTimeUtils.obterMesDeDataBrasileira(dataAtual));
		int anoAtual = Integer.parseInt(DateTimeUtils.obterAnoDeDataBrasileira(dataAtual));


		int diaDeNascimento = Integer.parseInt(DateTimeUtils.obterDiaDeDataBrasileira(dataDeNascimento));
		int mesDeNascimento = Integer.parseInt(DateTimeUtils.obterMesDeDataBrasileira(dataDeNascimento));
		int anoDeNascimento = Integer.parseInt(DateTimeUtils.obterAnoDeDataBrasileira(dataDeNascimento));

		int idade = anoAtual - anoDeNascimento;

		if(mesAtual < mesDeNascimento){
			idade = idade - 1;
		}
		else if(diaAtual < diaDeNascimento){
			idade = idade - 1;
		}

		if(idade < 0){
			idade = 0;
		}

		return idade;
	}


        /**
	 * Validador de data, que a partir dos parametros inseridos verifica se
	 * o evento verificado ocorre em uma data que n�o seja menor que a data corrente
         *
	 * @param dia O dia da data fornecida a ser verificada
	 * @param mes O mes da data fornecida a ser verificada
	 * @param ano O ano da data fornecida a ser verificada
	 */
	public static boolean verificarDataMaiorQueDataCorrente(String dia, String mes, String ano){
		String diaCorrente = DateTimeUtils.obterDiaDeDataBrasileira(DateTimeUtils.obterDataCorrenteBr());
		String mesCorrente = DateTimeUtils.obterMesDeDataBrasileira(DateTimeUtils.obterDataCorrenteBr());
		String anoCorrente = DateTimeUtils.obterAnoDeDataBrasileira(DateTimeUtils.obterDataCorrenteBr());

		int diaCorrenteInt = Integer.parseInt(diaCorrente);
		int mesCorrenteInt = Integer.parseInt(mesCorrente);
		int anoCorrenteInt = Integer.parseInt(anoCorrente);

		int diaObtidInto = Integer.parseInt(dia);
		int mesObtidoInt = Integer.parseInt(mes);
		int anoObtidoInt = Integer.parseInt(ano);

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
		else if((anoObtidoInt == anoCorrenteInt) && (mesObtidoInt == mesCorrenteInt) && (diaObtidInto == diaCorrenteInt)){
			dataValida = true;
		}

		return dataValida;
	}

        /**
	 * Validador de data, que a partir dos parametros inseridos verifica se
	 * o evento verificado ocorre em uma data que n�o seja menor que a data corrente
         *
	 * @param data A data no formato DD/MM/AAAA a ser verificada
	 */
	public static boolean verificarDataMaiorQueDataCorrente(String data){

                String dia = DateTimeUtils.obterDiaDeDataBrasileira(data);
                String mes = DateTimeUtils.obterMesDeDataBrasileira(data);
		String ano = DateTimeUtils.obterAnoDeDataBrasileira(data);

		String diaCorrente = DateTimeUtils.obterDiaDeDataBrasileira(DateTimeUtils.obterDataCorrenteBr());
		String mesCorrente = DateTimeUtils.obterMesDeDataBrasileira(DateTimeUtils.obterDataCorrenteBr());
		String anoCorrente = DateTimeUtils.obterAnoDeDataBrasileira(DateTimeUtils.obterDataCorrenteBr());

		int diaCorrenteInt = Integer.parseInt(diaCorrente);
		int mesCorrenteInt = Integer.parseInt(mesCorrente);
		int anoCorrenteInt = Integer.parseInt(anoCorrente);

		int diaObtidInto = Integer.parseInt(dia);
		int mesObtidoInt = Integer.parseInt(mes);
		int anoObtidoInt = Integer.parseInt(ano);

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
		else if((anoObtidoInt == anoCorrenteInt) && (mesObtidoInt == mesCorrenteInt) && (diaObtidInto == diaCorrenteInt)){
			dataValida = true;
		}

		return dataValida;
	}

        /**
	 *Dada uma data no formato DD/MM/AAAA e um hor�rio no formato HH:MM:SS,
         *realiza a convers�o para epoch para que possa ser manipulada em compara��es
         *
	 *@param data , A data atual no formato DD/MM/AAAA
         *@param time O hor�rio atual no formato HH:MM:SS
         *
         *@return O tempo atual em milissegundos
	 */
	public static long converterDateTimeToMilissegundos(String data, String time){
            String dia = DateTimeUtils.obterDiaDeDataBrasileira(data);
            String mes = DateTimeUtils.obterMesDeDataBrasileira(data);
            String ano = DateTimeUtils.obterAnoDeDataBrasileira(data);
            String hora = DateTimeUtils.obterHora(time);
            String minuto = DateTimeUtils.obterMinuto(time);
            String segundo = DateTimeUtils.obterSegundo(time);

            Calendar calendar = Calendar.getInstance();
            
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
            calendar.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
            calendar.set(Calendar.YEAR, Integer.parseInt(ano));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
            calendar.set(Calendar.MINUTE, Integer.parseInt(minuto));
            calendar.set(Calendar.SECOND, Integer.parseInt(segundo));


            System.out.println("Dia : " + calendar.get(Calendar.DAY_OF_MONTH));
            System.out.println("Mes : " + calendar.get(Calendar.MONTH));
            System.out.println("Ano : " + calendar.get(Calendar.YEAR));
            System.out.println("Hora : " + calendar.get(Calendar.HOUR_OF_DAY));
            System.out.println("Minuto : " + calendar.get(Calendar.MINUTE));
            System.out.println("Segundo : " + calendar.get(Calendar.SECOND));

            return calendar.getTime().getTime();
        }

        public static Date calcularTempoDecorrido(long tempoDeReferenciaDecorrido){
            Date dataCorrente = new Date();

            //Calcula a diferen�a entre a data atual e a data passada:
            long dataCorrenteEmMili = dataCorrente.getTime();

            long diferenca = dataCorrenteEmMili - tempoDeReferenciaDecorrido;

            if(diferenca > 0){
                Date diferencaEntreDatas = new Date();
                diferencaEntreDatas.setTime(diferenca);
                return diferencaEntreDatas;
            }
            else{
                return new Date();
            }

        }

        public static long getElapsed(long diference, int opcaoEscolhida){
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

            long valorOpcaoEscolhida = 0;

            switch(opcaoEscolhida){
                case DateTimeUtils.DIA:
                    valorOpcaoEscolhida = elapsedDays;
                    break;
                case DateTimeUtils.ANO:
                    valorOpcaoEscolhida = elapsedYears;
                    break;
                case DateTimeUtils.HORA:
                    valorOpcaoEscolhida = elapsedHours;
                    break;
                case DateTimeUtils.MINUTO:
                    valorOpcaoEscolhida = elapsedMinutes;
                    break;
                case DateTimeUtils.SEGUNDO:
                    valorOpcaoEscolhida = elapsedSeconds;
                    break;
                default:
                    break;
            }

            return valorOpcaoEscolhida;

        }

}

