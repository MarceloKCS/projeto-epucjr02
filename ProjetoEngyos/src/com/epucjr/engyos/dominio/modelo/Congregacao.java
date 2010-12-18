package com.epucjr.engyos.dominio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;


@Entity
@Indexed
public class Congregacao {

	@Field
	private String nome;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCongregacao;
	
	@Field
	private String endereco;
	@OneToMany (fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn
	private List<Obreiro> obreiros;
	
	////////////////
	// CONSTRUTOR //
	////////////////
	public Congregacao(){
		
	}
	
	public Congregacao(String nome, String endereco) {
		setNome(nome);
		setEndereco(endereco);
		this.obreiros = new ArrayList<Obreiro>();
	}
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return this.endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public List<Obreiro> getObreiros() {
		return this.obreiros;
	}
	public void setObreiros(List<Obreiro> obreiros) {
		this.obreiros = obreiros;
	}
	public void addObreiro(Obreiro obreiro) {
		this.obreiros.add(obreiro);
	}
	public void removeObreiro(Obreiro obreiro) {
		this.obreiros.remove(obreiro);
	}
	public void removeObreiro(int indice) {
		this.obreiros.remove(indice);
	}
	public Obreiro getObreiro(int indice) {
		return this.obreiros.get(indice);
	}
	public int getQtdObreiros() {
		return this.obreiros.size();
	}
	public long getIdCongregacao(){
		return this.idCongregacao;
	}
	public void setIdCongregacao(long idCongregacao){
		this.idCongregacao = idCongregacao;
	}
}
