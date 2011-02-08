package com.epucjr.engyos.dominio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;


@Entity
@Indexed
public class Congregacao {

	//Essas marca��o s�o necess�rias ao hibernate search e cumprem duas fun��es:
	// - O primeiro � para indexar para tornar possivel a realiza��o de buscas
	//no campo referido
	//-A segunda, a qual foi atribuido um nome nomexxx_sort � utilizado pelo 
	//hibernate search para realizar uma ordena��o com base no campo nome
	//Veja a classe BuscaAvancada no m�todo de sort para verificar a implementa��o
	@Fields(
			{@Field(index=Index.TOKENIZED, store=Store.YES),
			@Field(name="nomecong_sort",
			index=Index.UN_TOKENIZED)
			})
	private String nome;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCongregacao;
	
	@Field
	private String endereco;
	
	@OneToMany (mappedBy = "congregacao", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	//@JoinColumn (name="congrefacao_id")
	private List<Obreiro> obreiros;
	
	////////////////
	// CONSTRUTOR //
	////////////////
	public Congregacao(){
		this.nome = "";
		this.endereco = "";
		this.obreiros = new ArrayList<Obreiro>();
	}
	
	public Congregacao(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
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
