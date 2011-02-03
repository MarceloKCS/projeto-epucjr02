package com.epucjr.engyos.dominio.modelo;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.reuniao", joinColumns = @JoinColumn(name = "reuniao_id")),
	@AssociationOverride(name = "primaryKey.obreiro", joinColumns = @JoinColumn(name = "obreiro_id"))
	  })

public class ReuniaoObreiro {
	
	@EmbeddedId
    private ReuniaoObreiroPK primaryKey = new ReuniaoObreiroPK();
	
    private boolean obreiroPresente;
    
    //bidirectional association! Needed to trick hibernate ;P
   /* @SuppressWarnings("unused")
    @Column(name="item_id", nullable=false, updatable=false, insertable=false)
    private Long reuniao;

    @SuppressWarnings("unused")
    @Column(name="product_id", nullable=false, updatable=false, insertable=false)
    private Long obreiro;*/
    
    @Transient
	public Reuniao getReuniao(){
		return this.primaryKey.getReuniao();
	}
	
	public void setReuniao(Reuniao reuniao){
		this.primaryKey.setReuniao(reuniao);
	}
	
	@Transient
	public Obreiro getObreiro(){
		return this.primaryKey.getObreiro();
	}
	
	public void setObreiro(Obreiro obreiro){
		this.primaryKey.setObreiro(obreiro);
	}

	public boolean isObreiroPresente() {
		return obreiroPresente;
	}

	public void setObreiroPresente(boolean obreiroPresente) {
		this.obreiroPresente = obreiroPresente;
	}
	
	
	
	 public ReuniaoObreiroPK getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(ReuniaoObreiroPK primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        ReuniaoObreiro that = (ReuniaoObreiro) o;

	        if (this.primaryKey != null ? !this.primaryKey.equals(that.getPrimaryKey()) : that.getPrimaryKey() != null) return false;

	        return true;
	    }

	    public int hashCode() {
	        return (this.getPrimaryKey() != null ? this.getPrimaryKey().hashCode() : 0);
	    }


}
