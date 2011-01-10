package com.epucjr.engyos.dominio.crud;

import java.util.HashMap;

import com.epucjr.engyos.dominio.modelo.Congregacao;

public class ValidadorDeFormularioDeCongregacao {
	
	///////////////
	// ATRIBUTOS //
	///////////////
	private boolean formularioValido;
	private HashMap<String, String> errors;
	
	////////////////
	// CONSTRUTOR //
	////////////////
	public ValidadorDeFormularioDeCongregacao(){
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
	
	public void verificarCamposValidos(Congregacao congregacao){
		if (congregacao.getNome() == null || congregacao.getNome().trim().length() == 0){
			this.definirCampoComErro("Nome", "Nome Obrigátorio");
		}
		if (congregacao.getEndereco() == null || congregacao.getEndereco().trim().length() == 0){
			this.definirCampoComErro("Endereco", "Endereço Obrigatório");
		}
		else{
			if(congregacao.getNome() != null && congregacao.getNome().trim().length() != 0){
				this.setFormularioValido(true);
			}
		}
	}

}
