package com.epucjr.engyos.dominio.crud;

import java.util.HashMap;

import com.epucjr.engyos.tecnologia.utilitarios.DateUtils;

public class ValidadorDeFormularioDeReuniao {

	/******************************
	 *	ATRIBUTOS
	 ******************************/
	private boolean formularioValido;
	private HashMap <String, String> errors;

	/******************************
	 *	CONSTRUTOR
	 ******************************/
	public ValidadorDeFormularioDeReuniao(){
		this.formularioValido = false;
		this.errors = new HashMap<String, String>();
	}

	/******************************
	 *	METODOS
	 ******************************/	

	public void definirCampoComErro(String nomeDoCampo, String valorDoCampo ) {
		this.errors.put(nomeDoCampo, valorDoCampo);
	}

	public String obterCampoComErro(String nomeDoCampo) {
		if(this.errors.containsKey(nomeDoCampo)){
			return this.errors.get(nomeDoCampo);
		}
		else{
			return "";
		}
	}

	public boolean verificarCampoComErro(String nomeDoCampo){
		if (this.errors.containsKey(nomeDoCampo)){
			return true;
		}
		else{
			return false;
		}
	}

	public void verificarCamposValidos(String local, String dia, String mes, String ano, String hora, String minuto){
		this.setFormularioValido(true);
		String avisoDataDia = "";
		String avisoDataMes = "";
		String avisoDataAno = "";
		String avisoDataHora = "";
		String avisoDataMinuto = "";		
		
		if (local == null || local.trim().length() == 0){
			this.definirCampoComErro("local", "Nome Obrigatório");
			this.setFormularioValido(false);
		}
		if(dia == null || dia.equals("00")){
			this.definirCampoComErro("Dia", "Insira o dia");
			avisoDataDia = "Dia";
			this.setFormularioValido(false);
		}
		if(mes == null || mes.equals("00")){
			this.definirCampoComErro("Mes", "Insira o mês");
			avisoDataMes = "Mes";
			this.setFormularioValido(false);
		}
		if(ano == null || ano.equals("0000")){
			this.definirCampoComErro("Ano", "Insira o ano");
			avisoDataAno = "Ano";
			this.setFormularioValido(false);
		}
		if(hora == null || hora.equals("24")){
			this.definirCampoComErro("Hora", "Insira a hora");
			avisoDataHora = "Hora";
			this.setFormularioValido(false);
		}
		if(minuto == null || minuto.equals("60")){
			this.definirCampoComErro("Minuto", "Insira os minutos");
			avisoDataMinuto = "Minuto";
			this.setFormularioValido(false);
		}
		
		if(!avisoDataDia.equals("") && avisoDataMes.equals("") && avisoDataAno.equals("") ){
			this.definirCampoComErro("Data", "Insira o " +  avisoDataDia);
		}
		else if(!avisoDataDia.equals("") && !avisoDataMes.equals("") && avisoDataAno.equals("") ){
			this.definirCampoComErro("Data", "Insira o " +  avisoDataDia + " e o " +  avisoDataMes);
		}
		else if(!avisoDataDia.equals("") && avisoDataMes.equals("") && !avisoDataAno.equals("") ){
			this.definirCampoComErro("Data", "Insira o " +  avisoDataDia + " e o " +  avisoDataAno);
		}
		else if(avisoDataDia.equals("") && !avisoDataMes.equals("") && !avisoDataAno.equals("") ){
			this.definirCampoComErro("Data", "Insira o " +  avisoDataMes + " e o " +  avisoDataAno);
		}
		else if(!avisoDataDia.equals("") && !avisoDataMes.equals("") && !avisoDataAno.equals("") ){
			this.definirCampoComErro("Data", "Insira o " +  avisoDataDia + ", " + avisoDataMes + " e o " +  avisoDataAno);
		}
		
		if(!avisoDataHora.equals("") && avisoDataMinuto.equals("")){
			this.definirCampoComErro("Horario", "Insira a " +  avisoDataHora);
		}
		else if(avisoDataHora.equals("") && !avisoDataMinuto.equals("")){
			this.definirCampoComErro("Horario", "Insira o " +  avisoDataMinuto);
		}
		else if(!avisoDataHora.equals("") && !avisoDataMinuto.equals("")){
			this.definirCampoComErro("Horario", "Insira a " +  avisoDataHora + " e o " + avisoDataMinuto);
		}
		
		if((dia != null && !dia.equals("00")) && (mes != null && !mes.equals("00"))
		&& (ano != null && !ano.equals("00"))){
			this.validarDateFornecido(dia, mes, ano);
		}
		
		if((dia != null && !dia.equals("00")) && (mes != null && !mes.equals("00"))
		&& (ano != null && !ano.equals("00")) && (hora != null && !hora.equals("24"))
		&& (minuto != null && !minuto.equals("60"))		
		){
			this.validarDateTimeFornecido(dia, mes, ano, hora, minuto);
		}
		
	}
	/**
	 * 
	 * @param dia
	 * @param mes
	 * @param ano
	 * 
	 * Validador de data, que a partir dos parametros inseridos verifica se 
	 * o evento verificado ocorre em uma data que não seja menor que a data corrente
	 */
	
	private void validarDateFornecido(String dia, String mes, String ano){
		String diaCorrente = DateUtils.obterDiaDeDataBrasileira(DateUtils.obterDataCorrenteBr());
		String mesCorrente = DateUtils.obterMesDeDataBrasileira(DateUtils.obterDataCorrenteBr());
		String anoCorrente = DateUtils.obterAnoDeDataBrasileira(DateUtils.obterDataCorrenteBr());
		
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
		
		if(!dataValida){
			this.definirCampoComErro("Data", "Data Inválida, é menor que a data corrente");
			this.setFormularioValido(false);
		}
		
				
	}
	
	/**
	 * 
	 * @param dia
	 * @param mes
	 * @param ano
	 * @param hora
	 * @param minuto
	 * 
	 * Método que verifica a hora registrada caso o evento marcado seja na data corrente,
	 * e marca um aviso ao relatório caso o evento seja marcado em um horário menor que o horário corrente
	 */
	
	private void validarDateTimeFornecido(String dia, String mes, String ano, String hora, String minuto){
		String diaCorrente = DateUtils.obterDiaDeDataBrasileira(DateUtils.obterDataCorrenteBr());
		String mesCorrente = DateUtils.obterMesDeDataBrasileira(DateUtils.obterDataCorrenteBr());
		String anoCorrente = DateUtils.obterAnoDeDataBrasileira(DateUtils.obterDataCorrenteBr());
		
		int diaCorrenteInt = Integer.parseInt(diaCorrente);
		int mesCorrenteInt = Integer.parseInt(mesCorrente);
		int anoCorrenteInt = Integer.parseInt(anoCorrente);
		
		int diaObtidInto = Integer.parseInt(dia);
		int mesObtidoInt = Integer.parseInt(mes);
		int anoObtidoInt = Integer.parseInt(ano);
		
		String horaCorrente = DateUtils.obterHora(DateUtils.obterTempoCorrente());
		String minutoCorrente = DateUtils.obterMinuto(DateUtils.obterTempoCorrente());
		//String segundoCorrente = DateUtils.obterSegundo(DateUtils.obterTempoCorrente());
		
		int horaCorrenteInt = Integer.parseInt(horaCorrente);
		int minutoCorrenteInt = Integer.parseInt(minutoCorrente);
		//int segundoCorrenteInt = Integer.parseInt(segundoCorrente);
		
		int horaObtidaInt = Integer.parseInt(hora);
		int minutoObtidaInt = Integer.parseInt(minuto);
		//int segundoObtidaInt = Integer.parseInt(segundoObtido);		
				
		/*boolean dataValida = false;
		
		if(anoObtidoInt > anoCorrenteInt){
			dataValida = true;
		}
		else if((anoObtidoInt == anoCorrenteInt) && (mesObtidoInt > mesCorrenteInt)){
			dataValida = true;
		}
		else if((anoObtidoInt == anoCorrenteInt) && (mesObtidoInt == mesCorrenteInt) && (diaObtidInto > diaCorrenteInt)){
			dataValida = true;
		}*/
		
		/*if(!dataValida){
			this.definirCampoComErro("Data", "Data Inválida, é menor que a data corrente");
			this.setFormularioValido(false);
		}*/
		
		if((anoObtidoInt == anoCorrenteInt) && (mesObtidoInt == mesCorrenteInt) && (diaObtidInto == diaCorrenteInt)){
			if(horaObtidaInt > horaCorrenteInt){
				//dataValida = true;
			}
			else if((horaObtidaInt == horaCorrenteInt) && minutoObtidaInt > minutoCorrenteInt){
				//dataValida = true;
			}
			else{
				this.definirCampoComErro("Horario", "Horário Inválido, horário para este dia já passou");
				this.setFormularioValido(false);
			}
		}
		
	}

	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/
	public boolean isFormularioValido() {
		return formularioValido;
	}
	public void setFormularioValido(boolean formularioValido) {
		this.formularioValido = formularioValido;
	}
	public HashMap<String, String> getErrors() {
		return this.errors;
	}
	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}
}
