package com.epucjr.engyos.dominio.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@Field
	private String senha;
	
	@Field
	private String digital;

	@Id
	@DocumentId
	@Field
	private String cpf;

	@Field
	private String congregacao;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	@IndexedEmbedded
	private Presenca presenca;


	////////////////
	// CONSTRUTOR //
	////////////////
	public Obreiro(){
		
	}
	
	public Obreiro(String nome, String cargo, String cpf, String congregacao) {
		setNome(nome);
		setCargo(cargo);
		setCpf(cpf);
		setCongregacao(congregacao);
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
	public String getCongregacao() {
		return this.congregacao;
	}
	public void setCongregacao(String congregacao) {
		this.congregacao = congregacao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDigital() {
		return digital;
	}
	public void setDigital(String digital) {
		this.digital = digital;
	}


}
