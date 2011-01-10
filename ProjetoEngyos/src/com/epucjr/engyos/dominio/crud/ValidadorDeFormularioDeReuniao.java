package com.epucjr.engyos.dominio.crud;

import java.util.HashMap;

import com.epucjr.engyos.dominio.modelo.Reuniao;

public class ValidadorDeFormularioDeReuniao {

	///////////////
	// ATRIBUTOS //
	///////////////
	private boolean formularioValido;
	private HashMap <String, String> errors;
	
	////////////////
	// CONSTRUTOR //
	////////////////
	public ValidadorDeFormularioDeReuniao(){
		this.formularioValido = false;
		this.errors = new HashMap<String, String>();
	}
	
	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
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
	
	/////////////
	// METODOS //
	/////////////
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
	
	public void verificarCamposValidos(Reuniao reuniao){
		String dia = reuniao.getDia();
		String mes = reuniao.getMes();
		String ano = reuniao.getAno();
		String hora = reuniao.getHora();
		String minuto = reuniao.getMinuto();
		
		if ((dia.equals("00")) || (mes.equals("00")) || (ano.equals("0000"))){
			this.definirCampoComErro("Data", "Data Obrigatória");
		}
		if ((hora.equals("24")) || (minuto.equals("60"))){
			this.definirCampoComErro("Hora", "Hora Obrigatória");
		}
		else{
			if(!this.verificarCampoComErro("Data")){
				this.setFormularioValido(true);
			}
		}
	}
}
