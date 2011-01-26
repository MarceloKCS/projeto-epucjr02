package com.epucjr.engyos.tecnologia.utilitarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

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
		String ano = ""; 
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
		return dataBrasileira;
	}
	/** Transformar uma data (String) do formato brasileiroo para o formato americano.
	 * 
	 * @param dataBrasileira, no formato "DD/MM/YYYY"
	 * @return a data americana correspondente no formato "YYYY/MM/DD"
	 */
	public static String transformarDataBrasileiraParaAmericana( String dataBrasileira ){
		String dataAmericana = "";
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

		return dataAmericana;
	}    

	/** Capturar o valor de Real (String) de uma string contendo um valor total
	 * EXEMPLO: 1000,20 ---> Capturar somente 1000
	 * @param valor, 1000.00
	 * @return real, 1000"
	 */
	public static String pegarValorReal( String valor ){
		String real = "";
		int posicao = 0;
		if( !valor.equals("")) {

			while(valor.charAt(posicao) != ','){
				real = real + valor.charAt(posicao);
				posicao++;
			}
		}
		return real;
	}
	public static String pegarValorCentavo( String valor ){
		String centavo = "";
		int posicao = 0;
		int fim = valor.length();
		if( !valor.equals("")) {
			while(valor.charAt(posicao) != ','){
				posicao++;
			}
			for(int k = posicao + 1; k < fim; k++ ){
				centavo = centavo + valor.charAt(k);
			}
		}
		return centavo;
	}

	public static String obterDiaDeDataBrasileira(String dataBrasileira){
		String dia = "";
		if(!dataBrasileira.equals("")){
			int posicao = 0;			
			while(dataBrasileira.charAt(posicao) != '/'){
				dia = dia + dataBrasileira.charAt(posicao);
				posicao++;
			}
		}
		return dia;
	}

	public static String obterMesDeDataBrasileira(String dataBrasileira){
		String dia = "";
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
		}
		return mes;
	}


	public static String obterAnoDeDataBrasileira(String dataBrasileira){
		String dia = "";
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
		return ano;
	}

	public static String obterHora(String horario){
		String hora = "";
		if(!horario.equals("")){
			int posicao = 0;			
			while(horario.charAt(posicao) != ':'){
				hora = hora + horario.charAt(posicao);
				posicao++;
			}
		}

		return hora;
	}

	public static String obterMinuto(String horario){
		String hora = "";
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

		return minuto;
	}

	public static String obterSegundo(String horario){
		String hora = "";
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

		return segundo;
	}

	public static int calcularIdade(String dataDeNascimento){
		String dataAtual = DateUtils.dataCorrenteSemTempo();		
		dataAtual = DateUtils.transformarDataAmericanaParaBrasileira(dataAtual);

		int diaAtual = Integer.parseInt(DateUtils.obterDiaDeDataBrasileira(dataAtual));
		int mesAtual = Integer.parseInt(DateUtils.obterMesDeDataBrasileira(dataAtual));
		int anoAtual = Integer.parseInt(DateUtils.obterAnoDeDataBrasileira(dataAtual));


		int diaDeNascimento = Integer.parseInt(DateUtils.obterDiaDeDataBrasileira(dataDeNascimento));
		int mesDeNascimento = Integer.parseInt(DateUtils.obterMesDeDataBrasileira(dataDeNascimento));
		int anoDeNascimento = Integer.parseInt(DateUtils.obterAnoDeDataBrasileira(dataDeNascimento));

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

}

