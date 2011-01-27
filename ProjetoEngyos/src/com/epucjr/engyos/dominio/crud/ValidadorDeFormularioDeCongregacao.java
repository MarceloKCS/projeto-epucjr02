package com.epucjr.engyos.dominio.crud;

import java.util.HashMap;

public class ValidadorDeFormularioDeCongregacao {

	/******************************
	 *	ATRIBUTOS
	 ******************************/
	private boolean formularioValido;
	private HashMap<String, String> errors;

	/******************************
	 *	CONSTRUTOR
	 ******************************/
	public ValidadorDeFormularioDeCongregacao(){
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

	public void verificarCamposValidos(String nomeDaCongregacao, String endereco){		
		this.setFormularioValido(true);
		
		if (nomeDaCongregacao == null || nomeDaCongregacao.trim().length() == 0){
			this.definirCampoComErro("Nome", "Nome Obrigátorio");
			this.setFormularioValido(false);
		}
		if (endereco == null || endereco.trim().length() == 0){
			this.definirCampoComErro("Endereco", "Endereço Obrigatório");
			this.setFormularioValido(false);
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
