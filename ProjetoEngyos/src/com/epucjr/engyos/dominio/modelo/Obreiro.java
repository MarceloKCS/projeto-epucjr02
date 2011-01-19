package com.epucjr.engyos.dominio.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity
@Indexed
public class Obreiro {

	@Field
	private String nome;

	@Field
	private String cargo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn
	private Identificacao identificacao;
	
	@Id
	@DocumentId
	@Field
	private String cpf;
	
	//@Field
	@ManyToOne//(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn (name="congregacao_fk")
	private Congregacao congregacao;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	@IndexedEmbedded
	private Presenca presenca;


	////////////////
	// CONSTRUTOR //
	////////////////
	public Obreiro(){
		this.nome = "";
		this.cargo = "";
		this.identificacao = new Identificacao();		
	}
	
	public Obreiro(String nome, String cargo, String cpf, Congregacao congregacao, String imppressaoDigital, String senha) {
		this.nome = nome;
		this.cargo = cargo;
		this.cpf = cpf;
		this.congregacao = congregacao;		
		this.identificacao = new Identificacao(imppressaoDigital, senha);
	}
	
	public Obreiro(String nome, String cargo, String cpf, Congregacao congregacao, Identificacao identificacao) {
		setNome(nome);
		setCargo(cargo);
		setCpf(cpf);
		setCongregacao(congregacao);
		this.identificacao = identificacao;
	}
	

	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Presenca getPresenca() {
		return presenca;
	}

	public void setPresenca(Presenca presenca) {
		this.presenca = presenca;
	}

	public String getCargo() {
		return this.cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCpf() {
		return this.cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Congregacao getCongregacao() {
		return congregacao;
	}

	public void setCongregacao(Congregacao congregacao) {
		this.congregacao = congregacao;
	}

	public Identificacao getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(Identificacao identificacao) {
		this.identificacao = identificacao;
	}

	
}
