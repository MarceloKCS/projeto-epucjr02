package com.epucjr.engyos.dominio.visualizacao;

import java.util.HashMap;

import com.epucjr.engyos.dominio.modelo.Congregacao;

public class FormularioDeCongregacao {
	
	///////////////
	// ATRIBUTOS //
	///////////////
	private HashMap<String, String> camposPreenchidos;
	private String status;

	////////////////
	// CONSTRUTOR //
	////////////////
	public FormularioDeCongregacao(Congregacao congregacao){
		status = "";
		this.camposPreenchidos = new HashMap<String, String>();
		if(congregacao == null){
		}
		else{
			this.carregarDadosDoFormulario(congregacao);
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
	
	private void carregarDadosDoFormulario(Congregacao congregacao){
		this.definirCampoPreenchido("idCongregacao", ""+congregacao.getIdCongregacao());
		if (congregacao.getNome() != null && congregacao.getNome().trim().length() != 0){
			this.definirCampoPreenchido("Nome", congregacao.getNome());
		}
		if (congregacao.getEndereco() != null && congregacao.getEndereco().trim().length() != 0){
			this.definirCampoPreenchido("Endereco", congregacao.getEndereco());
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
