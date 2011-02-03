package com.epucjr.engyos.dominio.modelo;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Embeddable
public class PresencaObreiroRel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPresencaObreiroRel;
	
	private long idPresencaFk;
	private String cpfObreiroFk;	
	private boolean obreiroPresente;
	private String momentoPresenca;
	private String dataPresenca;
	
	
	public PresencaObreiroRel() {
		this.idPresencaFk = 0;
		this.cpfObreiroFk = "";
		this.obreiroPresente = false;
		this.momentoPresenca = "";
		this.dataPresenca = "";
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

	public long getIdPresencaFk() {
		return idPresencaFk;
	}


	public void setIdPresencaFk(long idPresencaFk) {
		this.idPresencaFk = idPresencaFk;
	}


	public String getCpfObreiroFk() {
		return cpfObreiroFk;
	}


	public void setCpfObreiroFk(String cpfObreiroFk) {
		this.cpfObreiroFk = cpfObreiroFk;
	}


	public long getIdPresencaObreiroRel() {
		return idPresencaObreiroRel;
	}

	
	

}
