package com.epucjr.engyos.dominio.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable 
public class ReuniaoObreiroPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6421512671322902226L;

	@ManyToOne
	private Reuniao reuniao;
	
	@ManyToOne
	private Obreiro obreiro;
	
	public ReuniaoObreiroPK() {
		// TODO Auto-generated constructor stub
	}

	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}

	public Obreiro getObreiro() {
		return obreiro;
	}

	public void setObreiro(Obreiro obreiro) {
		this.obreiro = obreiro;
	}
	
	 public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        ReuniaoObreiroPK that = (ReuniaoObreiroPK) o;

	        if (reuniao != null ? !reuniao.equals(that.reuniao) : that.reuniao != null) return false;
	        if (obreiro != null ? !obreiro.equals(that.obreiro) : that.obreiro != null)
	            return false;

	        return true;
	    }

	    public int hashCode() {
	        int result;
	        result = (reuniao != null ? reuniao.hashCode() : 0);
	        result = 31 * result + (obreiro != null ? obreiro.hashCode() : 0);
	        return result;
	    }


}
