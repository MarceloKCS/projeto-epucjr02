package com.epucjr.engyos.dominio.visualizacao;

import java.util.HashMap;

import com.epucjr.engyos.dominio.modelo.Reuniao;

public class FormularioDeReuniao {

	///////////////
	// ATRIBUTOS //
	///////////////
	private String hora;
	private String minuto;
	private String dia;
	private String mes;
	private String ano;
	private HashMap<String, String> camposPreenchidos;
	private String status;

	////////////////
	// CONSTRUTOR //
	////////////////
	public FormularioDeReuniao(Reuniao reuniao){
		this.camposPreenchidos = new HashMap<String, String>();
		if(reuniao == null){
			this.dia = "";
			this.mes = "";
			this.ano = "";
			this.hora = "";
			this.minuto = "";
		}
		else{
			this.carregarDadosDoFormulario(reuniao);
		}
	}
	
	/////////////
	// METODOS //
	/////////////
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
	
	private void carregarDadosDoFormulario(Reuniao reuniao){
		this.dia = reuniao.getDia();
		this.mes = reuniao.getMes();
		this.ano = reuniao.getAno();
		this.hora = reuniao.getHora();
		this.minuto = reuniao.getMinuto();
		
		if (!this.dia.equals("00")){
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
		}
	}
	
	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
	public String getStatus(){
		return this.status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
}
