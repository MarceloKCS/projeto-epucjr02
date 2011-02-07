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
import javax.persistence.OneToOne;

@Entity
public class PresencaObreiro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long IdPresencaObreiro;
	
	private boolean obreiroPresente;
	private String momentoPresenca;
	private String dataPresenca;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )	
	@JoinColumn
	private Obreiro obreiro;
	
	@ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn
	private List<Reuniao> reuniao;
	
	public PresencaObreiro() {
		this.obreiroPresente = false;
		this.momentoPresenca = "";
		this.dataPresenca = "";
		this.obreiro = new Obreiro();
		this.reuniao = new ArrayList<Reuniao>();
	}
	
	public PresencaObreiro(Obreiro obreiro) {
		this.obreiroPresente = false;
		this.momentoPresenca = "";
		this.dataPresenca = "";
		this.obreiro = obreiro;
		this.reuniao = new ArrayList<Reuniao>();
	}
	

	public boolean isObreiroPresente() {
		return obreiroPresente;
	}

	public void setObreiroPresente(boolean obreiroPresente) {
		this.obreiroPresente = obreiroPresente;
	}

	public String getMomentoPresenca() {
		return momentoPresenca;
	}

	public void setMomentoPresenca(String momentoPresenca) {
		this.momentoPresenca = momentoPresenca;
	}

	public String getDataPresenca() {
		return dataPresenca;
	}

	public void setDataPresenca(String dataPresenca) {
		this.dataPresenca = dataPresenca;
	}

	public Obreiro getObreiro() {
		return obreiro;
	}

	public void setObreiro(Obreiro obreiro) {
		this.obreiro = obreiro;
	}

	public List<Reuniao> getReuniao() {
		return reuniao;
	}

	public void setReuniao(List<Reuniao> reuniao) {
		this.reuniao = reuniao;
	}

	public long getIdPresencaObreiro() {
		return IdPresencaObreiro;
	}

}
