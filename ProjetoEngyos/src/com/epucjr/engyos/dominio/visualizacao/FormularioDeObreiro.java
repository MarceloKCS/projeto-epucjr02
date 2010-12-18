package com.epucjr.engyos.dominio.visualizacao;

import java.util.HashMap;

import com.epucjr.engyos.dominio.crud.ValidatorCpf;
import com.epucjr.engyos.dominio.modelo.Obreiro;

public class FormularioDeObreiro {

	///////////////
	// ATRIBUTOS //
	///////////////
	private HashMap<String, String> camposPreenchidos;
	private ValidatorCpf validadorCpf;
	private String status;

	////////////////
	// CONSTRUTOR //
	////////////////
	public FormularioDeObreiro(Obreiro obreiro){
		this.camposPreenchidos = new HashMap<String, String>();
		this.validadorCpf = new ValidatorCpf();
		status = "";
		if(obreiro == null){
		}
		else{
			this.carregarDadosDoFormulario(obreiro);
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
	
	private void carregarDadosDoFormulario(Obreiro obreiro){
		if (obreiro.getNome() != null && obreiro.getNome().trim().length() != 0){
			this.definirCampoPreenchido("Nome", obreiro.getNome());
		}
		if (this.validadorCpf.valido(obreiro.getCpf())){
			this.definirCampoPreenchido("Cpf", obreiro.getCpf());
		}
		if (obreiro.getCargo() != null && obreiro.getCargo().trim().length() != 0){
			this.definirCampoPreenchido("Cargo", obreiro.getCargo());
		}
		if (obreiro.getCongregacao() != null && obreiro.getCongregacao().trim().length() != 0){
			this.definirCampoPreenchido("Congregacao", obreiro.getCongregacao());
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
