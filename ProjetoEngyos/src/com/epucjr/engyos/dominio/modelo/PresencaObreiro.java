package com.epucjr.engyos.dominio.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PresencaObreiro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long IdPresencaObreiro;
	
	private boolean obreiroPresente;
	private String momentoPresenca;
	private String dataPresenca;
        
        @Temporal(TemporalType.TIMESTAMP)
        private Date momentoRegistroPresenca;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn
	private Obreiro obreiro;
	
	@ManyToMany(mappedBy="listaDePresencaObreiro", fetch = FetchType.LAZY , cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	//@JoinColumn
	private List<Reuniao> reuniao;
	
	public PresencaObreiro() {
		this.obreiroPresente = false;
		this.momentoPresenca = "";
		this.dataPresenca = "";
                this.momentoRegistroPresenca = null;
		this.obreiro = null;
		this.reuniao = new ArrayList<Reuniao>();
	}
	
	public PresencaObreiro(Obreiro obreiro) {
		this.obreiroPresente = false;
		this.momentoPresenca = "";
		this.dataPresenca = "";
                this.momentoRegistroPresenca = null;
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

        public Date getMomentoRegistroPresenca() {
            return momentoRegistroPresenca;
        }

        public void setMomentoRegistroPresenca(Date momentoRegistroPresenca) {
            this.momentoRegistroPresenca = momentoRegistroPresenca;
        }



}
