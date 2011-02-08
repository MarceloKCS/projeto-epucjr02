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
import javax.persistence.ManyToMany;


@Entity
public class Presenca {
	
	/******************************
	 *	ATRIBUTOS
	 ******************************/	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPresenca;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn
	private List<Obreiro> obreirosDaListaDePresenca;
	
	
	
	/******************************
	 *	CONSTRUTOR
	 ******************************/
	public Presenca(){
		obreirosDaListaDePresenca = new ArrayList<Obreiro>();
	}
	
	/******************************
	 *	METODOS
	 ******************************/
	
	public void adicionarObreiroNaLista(Obreiro obreiro){
		this.obreirosDaListaDePresenca.add(obreiro);
	}
	
	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/
	/*public String getPresencaMensal(int mes){
		return this.presencaMensal.get(mes);
	}
	public void setPresencaMensal(int mes, String qdtPresenca){
		this.presencaMensal.add(mes, qdtPresenca);
	}*/
	
	
	public long getIdPresenca() {
		return idPresenca;
	}

	public List<Obreiro> getObreirosDaListaDePresenca() {
		return obreirosDaListaDePresenca;
	}

	public void setObreirosDaListaDePresenca(List<Obreiro> obreirosDaListaDePresenca) {
		this.obreirosDaListaDePresenca = obreirosDaListaDePresenca;
	}	
	
}
