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
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
public class Obreiro {
	
	/******************************
	 *	ATRIBUTOS
	 ******************************/

	//Essas marcação são necessárias ao hibernate search e cumprem duas funções:
	// - O primeiro é para indexar para tornar possivel a realização de buscas
	//no campo referido
	//-A segunda, a qual foi atribuido um nome nomexxx_sort é utilizado pelo 
	//hibernate search para realizar uma ordenação com base no campo nome
	//Veja a classe BuscaAvancada no método de sort para verificar a implementação
	@Fields(
			{@Field(index=Index.TOKENIZED, store=Store.YES),
			@Field(name="nomeobr_sort",
			index=Index.UN_TOKENIZED)
			})
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
	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@IndexedEmbedded
	@JoinColumn (name="congregacao_fk")
	//@ContainedIn
	private Congregacao congregacao;	

	/******************************
	 *	CONSTRUTOR
	 ******************************/
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
	
	/******************************
	 *	METODOS
	 ******************************/
	

	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
