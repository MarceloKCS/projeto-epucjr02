package com.epucjr.engyos.dominio.crud;

public class ValidatorData {
	public boolean valido(String data) {
		String dt = data;
		if (dt.length() < 8) return false;
		if (dt.contains("/")) {
			dt = formatData(data);
		}
		if (dt.length() > 8) {
			return false;
		}
		int dia,mes,ano;
		try {
			dia = Integer.parseInt(data.substring(0,2));
			mes = Integer.parseInt(data.substring(2,4));
			ano = Integer.parseInt(data.substring(4));
		} catch (NumberFormatException ex) {
			return false;
		}
		if(mes < 1 || mes > 12)	return false;
		if(ano < 1970) return false;
		if((dia < 1) || (dia > diasMes(mes, ano))) return false;

		return true;
	}

	private static String formatData(String data) {
		String r = data;
		String d = data;
		if (data.contains("/")) {
			String[] n = d.split("/");
			if (n[0].length() < 2) {
				r = "0"; 
			}
			r = r + n[0];
			if (n[1].length() < 2) {
				r = "0"; 
			}
			r = r + n[1];
			if (n[2].length() < 4) {
				r = "0"; 
			}
			r = r + n[2];
		}
		return r;
	}

	private static int diasMes(int mes, int ano) {
		int[] dias_meses = {31, 28, 31, 30, 31, 30, 31,
				31, 30, 31, 30, 31};

		int quant_dias = dias_meses[mes - 1];

		// verifica se o ano é bissexto
		if(anoBissexto(ano) && (mes == 2)) quant_dias = 29;

		return quant_dias;
	}

	private static boolean anoBissexto(int ano) {
		return ((ano % 4 == 0) && ((!(ano % 100 == 0)) || 
				(ano % 400 == 0)));
	}
}
