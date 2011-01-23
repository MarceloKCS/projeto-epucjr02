package com.epucjr.engyos.dominio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Identificacao {
	
	/************************
	 * ATRIBUTOS
	 ***********************/
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idIdentificacao;
	private String impressaoDigital;
	private String senha;
	
	/************************
	 * CONSTRUTOR
	 ***********************/
	
	public Identificacao() {
		this.impressaoDigital = "";
		this.senha = "";
	}
	
	
	
	public Identificacao(String impressaoDigital, String senha) {		
		this.impressaoDigital = impressaoDigital;
		this.senha = senha;
	}
	
	public Identificacao(String senha) {	
		this.senha = senha;
	}



	/************************
	 * METODOS
	 ***********************/
	
	/************************
	 * GETTERS/SETTERS
	 ***********************/

	public String getImpressaoDigital() {
		return impressaoDigital;
	}

	public void setImpressaoDigital(String impressaoDigital) {
		this.impressaoDigital = impressaoDigital;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getIdIdentificacao() {
		return idIdentificacao;
	}

	public void setIdIdentificacao(long idIdentificacao) {
		this.idIdentificacao = idIdentificacao;
	}

}
