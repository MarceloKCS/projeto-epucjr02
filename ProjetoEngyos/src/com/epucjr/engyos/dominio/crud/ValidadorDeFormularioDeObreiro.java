package com.epucjr.engyos.dominio.crud;

import java.util.HashMap;

public class ValidadorDeFormularioDeObreiro {
	
	///////////////
	// ATRIBUTOS //
	///////////////
	private boolean formularioValido;
	private HashMap <String, String> errors;
	
	
	////////////////
	// CONSTRUTOR //
	////////////////
	public ValidadorDeFormularioDeObreiro(){
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
		return errors;
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
	
	public void verificarCamposValidos(String nome, String cpf, String cargo, String congregacao){
		ValidatorCpf validadorCpf = new ValidatorCpf();
		this.setFormularioValido(true);
		
		if (nome == null || nome.trim().length() == 0){
			this.definirCampoComErro("Nome", "Nome Obrigat�rio");
			this.setFormularioValido(false);
		}
		if (!validadorCpf.valido(cpf)){
			this.definirCampoComErro("Cpf", "CPF Inv�lido");
			this.setFormularioValido(false);
		}
		if (cargo == null || cargo.trim().length() == 0){
			this.definirCampoComErro("Cargo", "Cargo Inv�lido");
			this.setFormularioValido(false);
		}
		if (congregacao == null || congregacao.trim().length() == 0){
			this.errors.put("Congregacao", "Congrega��o Inv�lida");
			this.setFormularioValido(false);
		}
		
		
/*		//Verificando validade dos dados de obreiro
		if (obreiro.getNome() == null || obreiro.getNome().trim().length() == 0){
			this.definirCampoComErro("Nome", "Nome Obrigat�rio");
		}
		if(!this.validadorCpf.valido(obreiro.getCpf())){
			this.definirCampoComErro("Cpf", "CPF Inv�lido");
		}
		if (obreiro.getCargo() == null || obreiro.getCargo().trim().length() == 0){
			this.definirCampoComErro("Cargo", "Cargo Inv�lido");
		}
		
		//Verificando a congrega��o associada
		if (obreiro.getCongregacao() == null || obreiro.getCongregacao().trim().length() == 0){
			this.errors.put("Congregacao", "Congrega��o Inv�lida");
		}
		else{
			if((obreiro.getNome() != null && obreiro.getNome().trim().length() != 0) && (obreiro.getCargo() != null && obreiro.getCargo().trim().length() != 0) && (this.validadorCpf.valido(obreiro.getCpf())) && (obreiro.getCongregacao() != null && obreiro.getCongregacao().trim().length() != 0)){
				this.setFormularioValido(true);
			}
		}*/
	}

}
