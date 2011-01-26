package com.epucjr.engyos.dominio.visualizacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import com.epucjr.engyos.tecnologia.utilitarios.ListUtilTokenizer;

public class FormularioDeReuniao {

	/******************************
	 *	ATRIBUTOS
	 ******************************/
	private String hora;
	private String minuto;
	private String dia;
	private String mes;
	private String ano;
	private HashMap<String, String> camposPreenchidos;
	private HashMap < String,List<String> > campoListaPreenchido;
	private List<Obreiro> listaDeObreiros;
	private String mensagemStatus;
	private ValidadorDeFormularioDeReuniao validadorDeFormularioDeReuniao;

	/******************************
	 *	CONSTRUTOR
	 ******************************/
	public FormularioDeReuniao(){
		this.camposPreenchidos = new HashMap<String, String>();
		this.campoListaPreenchido = new HashMap<String, List<String>>();
		this.dia = "";
		this.mes = "";
		this.ano = "";
		this.hora = "";
		this.minuto = "";
		this.listaDeObreiros = new ArrayList<Obreiro>();
		this.mensagemStatus = "";
		this.carregarDadosDoFormulario();
		this.validadorDeFormularioDeReuniao = new ValidadorDeFormularioDeReuniao();

	}

	/******************************
	 *	METODOS
	 ******************************/
	public void definirCampoPreenchido(String nomeDoCampo, String valorDoCampo ) {
		this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
	}

	public String obterCampoPreenchido(String nomeDoCampo) {
		if(this.camposPreenchidos.containsKey(nomeDoCampo)){
			return this.camposPreenchidos.get(nomeDoCampo);
		}
		else{
			return "";
		}
	}

	public boolean verificarCampoPreenchido(String nomeDoCampo){
		if (this.camposPreenchidos.containsKey(nomeDoCampo)){
			return true;
		}
		else{
			return false;
		}
	}

	public void definirCampoListaPreenchido( String nomeDoCampo, List<String> valorDoCampo ) {
		this.campoListaPreenchido.put(nomeDoCampo, valorDoCampo);
	}

	public List<String> obterCampoListaPreenchido(String nomeDoCampo) {
		if(this.campoListaPreenchido.containsKey(nomeDoCampo)){
			return this.campoListaPreenchido.get(nomeDoCampo);
		}
		else{
			return null;
		}
	}

	public boolean campoListaEstaPreenchido(String nomeDoCampo){
		if (this.campoListaPreenchido.containsKey(nomeDoCampo)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void definirDadosDeConfirmacao(String nomeDoCampo, String valorDoCampo ) {
		this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
    }
    
    public String obterDadoDeConfirmacao(String nomeDoCampo) {
    	if(this.camposPreenchidos.containsKey(nomeDoCampo)){
    		return this.camposPreenchidos.get(nomeDoCampo);
    	}
    	else{
    		return "";
    	}
    }
    
    public boolean verificarDadoDeConfirmacao(String nomeDoCampo){
    	if (this.camposPreenchidos.containsKey(nomeDoCampo)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

	private void carregarDadosDoFormulario(){
		//Carregando a lista de Obreiros utilizada para agendamento da reunião
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

		List<Obreiro> listaDeObreiro = dataAccessObjectManager.obterListaDeObreiros();

		this.setListaDeObreiros(listaDeObreiro);
		this.setMensagemStatus("Formulario Carregado");

		/*this.dia = reuniao.getDia();
		this.mes = reuniao.getMes();
		this.ano = reuniao.getAno();
		this.hora = reuniao.getHora();
		this.minuto = reuniao.getMinuto();*/

		/*if (!this.dia.equals("00")){
			this.definirCampoPreenchido("Dia", this.dia);
		}
		if (!this.mes.equals("00")){
			this.definirCampoPreenchido("Mes", this.mes);
		}
		if (!this.ano.equals("0000")){
			this.definirCampoPreenchido("Ano", this.ano);
		}
		if (!this.hora.equals("24")){
			this.definirCampoPreenchido("Hora", this.hora);
		}
		if (!this.minuto.equals("60")){
			this.definirCampoPreenchido("Minuto", this.minuto);
		}*/
	}

	public void definirCamposPreenchidosPeloUsuario(HttpServletRequest httpServletRequest){
		//Obtenção dos campos preenchidos pelo usuário
		String local = httpServletRequest.getParameter("local");
		String dia = httpServletRequest.getParameter("dataReuniaoDia");
		String mes = httpServletRequest.getParameter("dataReuniaoMes");
		String ano = httpServletRequest.getParameter("dataReuniaoAno");
		String hora = httpServletRequest.getParameter("horaReuniao");
		String minuto = httpServletRequest.getParameter("minutoReuniao");
		//Os Ids de obreiros obtidos são tokenizados pelo Javascript com %
		String obreiroIdsTokenized = httpServletRequest.getParameter("obreiros");

		if(local != null && local != ""){
			this.definirCampoPreenchido("local", local);
		}

		if(dia != null && !dia.equals("00")){
			this.definirCampoPreenchido("dataReuniaoDia", dia);
		}

		if(mes != null && !mes.equals("00")){
			this.definirCampoPreenchido("dataReuniaoMes", mes);
		}

		if(ano != null && !ano.equals("00")){
			this.definirCampoPreenchido("dataReuniaoAno", ano);
		}
		
		if(hora != null && !hora.equals("24")){
			this.definirCampoPreenchido("horaReuniao", hora);
		}
		
		if(minuto != null && !minuto.equals("60")){
			this.definirCampoPreenchido("minutoReuniao", minuto);
		}

		if(obreiroIdsTokenized != null && obreiroIdsTokenized != ""){			
			List<String> listaDeIds = ListUtilTokenizer.obterListaString(obreiroIdsTokenized);			
			this.definirCampoListaPreenchido("obreiros", listaDeIds);			
		}

	}

	public void definirDadosDeConfirmacaoDeCadastroReuniao(String confirmacaoCadastro, String local, String data, String hora){

		this.definirDadosDeConfirmacao("confirmacao_cadastro", confirmacaoCadastro);
		this.definirDadosDeConfirmacao("confirmacao_local", local);
		this.definirDadosDeConfirmacao("confirmacao_data", data);
		this.definirDadosDeConfirmacao("confirmacao_hora", hora);
	}

	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMinuto() {
		return minuto;
	}

	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public List<Obreiro> getListaDeObreiros() {
		return listaDeObreiros;
	}

	public void setListaDeObreiros(List<Obreiro> listaDeObreiros) {
		this.listaDeObreiros = listaDeObreiros;
	}

	public String getMensagemStatus() {
		return mensagemStatus;
	}

	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}

	public ValidadorDeFormularioDeReuniao getValidadorDeFormularioDeReuniao() {
		return validadorDeFormularioDeReuniao;
	}

	public void setValidadorDeFormularioDeReuniao(
			ValidadorDeFormularioDeReuniao validadorDeFormularioDeReuniao) {
		this.validadorDeFormularioDeReuniao = validadorDeFormularioDeReuniao;
	}
	
	

}
