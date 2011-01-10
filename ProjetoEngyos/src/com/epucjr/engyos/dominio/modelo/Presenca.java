package com.epucjr.engyos.dominio.modelo;

import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;




@Entity

public class Presenca {
	
	@Transient
	private HashMap<String, String>  presencaMensal;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPresenca;
	
	
	
    
	
    
	
	
	
		
	////////////////
	// CONSTRUTOR //
	////////////////
	public Presenca(){
		
	}
	
	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
	/*public String getPresencaMensal(int mes){
		return this.presencaMensal.get(mes);
	}
	public void setPresencaMensal(int mes, String qdtPresenca){
		this.presencaMensal.add(mes, qdtPresenca);
	}*/
	
	
	public long getIdPresenca() {
		return idPresenca;
	}

	public void setIdPresenca(long idPresenca) {
		this.idPresenca = idPresenca;
	}

	public HashMap<String, String> getPresencaMensal() {
		return presencaMensal;
	}

	public void setPresencaMensal(HashMap<String, String> presencaMensal) {
		this.presencaMensal = presencaMensal;
	}
   
    
	/////////////
	// METODOS //
	/////////////
	
}
